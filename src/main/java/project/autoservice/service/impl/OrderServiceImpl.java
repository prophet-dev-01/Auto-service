package project.autoservice.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import project.autoservice.model.Order;
import project.autoservice.model.Owner;
import project.autoservice.model.Product;
import project.autoservice.model.Service;
import project.autoservice.repository.OrderRepository;
import project.autoservice.repository.OwnerRepository;
import project.autoservice.repository.ProductRepository;
import project.autoservice.repository.ServiceRepository;
import project.autoservice.service.OrderService;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class OrderServiceImpl implements OrderService {
    private static final BigDecimal MAX_PERCENT = BigDecimal.valueOf(100);
    private static final int DECIMAL_SCALE = 2;
    private static final int PERSON_SERVICE_DISCOUNT = 2;
    private static final int PERSON_PRODUCT_DISCOUNT = 1;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OwnerRepository ownerRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public Order save(Order order) {
        Order save = orderRepository.save(order);
        if (order.getCar() != null) {
            Owner owner = order.getCar().getOwner();
            List<Order> orders = owner.getOrders();
            orders.add(save);
            owner.setOrders(orders);
            ownerRepository.save(owner);
        }
        List<Service> services = order.getServices();
        services.forEach(service -> service.setOrder(order));
        serviceRepository.saveAll(services);
        return save;
    }

    @Override
    public Order update(Order order) {
        Order save = orderRepository.save(order);
        List<Service> services = order.getServices();
        services.forEach(service -> service.setOrder(order));
        serviceRepository.saveAll(services);
        return save;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id: " + id));
    }

    @Override
    public List<Order> findAllByIds(List<Long> ids) {
        return orderRepository.findAllByIdIn(ids);
    }

    @Override
    public void addProduct(Long id, Long productId) {
        Order orderById = orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id: " + id));
        List<Product> products = orderById.getProducts();
        products.add(productRepository.findById(productId).orElseThrow(
                () -> new NoSuchElementException("Can't find product by id: " + productId)));
        orderById.setProducts(products);
        orderRepository.save(orderById);
    }

    @Override
    public void updateStatus(Long id, Order.OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id: " + id));
        if (orderStatus.equals(Order.OrderStatus.SUCCESSFUL)
                || orderStatus.equals(Order.OrderStatus.NOT_COMPLETED_SUCCESSFUL)) {
            order.setCompletionDate(LocalDate.now());
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Order orderById = orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id: " + id));
        int countOrderInOwner = orderById.getCar().getOwner().getOrders().size();
        List<Service> services = orderById.getServices();
        Service firstService = services.stream()
                .findFirst().orElseThrow(
                        () -> new NoSuchElementException("Could not find service in order by id: "
                                + orderById.getId()));
        if (services.size() == 1 && firstService
                .getTypeOperation()
                .equals(Service.TypeOperation.DIAGNOSTIC)) {
            return firstService.getPrice()
                    .add(orderById.getProducts()
                            .stream()
                            .map(Product::getPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        return calculateDiscountedPrice(getPriceOfAllProductsByOrder(orderById),
                countOrderInOwner * PERSON_PRODUCT_DISCOUNT)
                .add(calculateDiscountedPrice(
                        getPriceForServicesWithFreeDiagnostics(services),
                        countOrderInOwner * PERSON_SERVICE_DISCOUNT));
    }

    private BigDecimal calculateDiscountedPrice(BigDecimal price, int discount) {
        BigDecimal discountPercent = BigDecimal.valueOf(discount);
        BigDecimal discountMultiplier =
                discountPercent
                        .divide(MAX_PERCENT, DECIMAL_SCALE, RoundingMode.HALF_UP);
        BigDecimal discountedPrice = price.multiply(BigDecimal.ONE.subtract(discountMultiplier));
        return discountedPrice.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP);
    }

    private BigDecimal getPriceOfAllProductsByOrder(Order order) {
        return order.getProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getPriceForServicesWithFreeDiagnostics(List<Service> services) {
        return services.stream()
                .filter(service -> !service.getTypeOperation()
                        .equals(Service.TypeOperation.DIAGNOSTIC))
                .map(Service::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
