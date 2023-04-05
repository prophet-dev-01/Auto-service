package project.autoservice.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import project.autoservice.exception.DataProcessingException;
import project.autoservice.model.Order;
import project.autoservice.model.OrderStatus;
import project.autoservice.model.Owner;
import project.autoservice.model.Product;
import project.autoservice.model.ServiceOperation;
import project.autoservice.model.TypeOperation;
import project.autoservice.repository.OrderRepository;
import project.autoservice.repository.OwnerRepository;
import project.autoservice.repository.ProductRepository;
import project.autoservice.repository.ServiceOperationRepository;
import project.autoservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OwnerRepository ownerRepository;
    private final ServiceOperationRepository operationRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductRepository productRepository,
                            OwnerRepository ownerRepository,
                            ServiceOperationRepository operationRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.ownerRepository = ownerRepository;
        this.operationRepository = operationRepository;
    }

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
        List<ServiceOperation> serviceOperations = order.getServiceOperations();
        serviceOperations.forEach(serviceOperation -> serviceOperation.setOrder(order));
        operationRepository.saveAll(serviceOperations);
        return save;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new DataProcessingException("Can't find order by id: " + id));
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
    public void updateStatus(Long id, OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find order by id: " + id));
        if (orderStatus.equals(OrderStatus.SUCCESSFUL)
                || orderStatus.equals(OrderStatus.NOT_COMPLETED_SUCCESSFUL)) {
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
        List<ServiceOperation> serviceOperations = orderById.getServiceOperations();
        if (serviceOperations.size() == 1
                && serviceOperations.get(0)
                .getTypeOperation()
                .equals(TypeOperation.DIAGNOSTIC)) {
            orderById.setTotalAmountDue(serviceOperations.get(0).getPrice());
            return serviceOperations.get(0).getPrice()
                    .add(orderById.getProducts()
                            .stream()
                            .map(Product::getPrice)
                            .reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        BigDecimal priceToProducts = orderById.getProducts()
                .stream()
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalPriceWithDiscount
                = calculateDiscountedPrice(priceToProducts, countOrderInOwner)
                .add(calculateDiscountedPrice(serviceOperations
                        .stream()
                        .filter(serviceOperation -> !serviceOperation.getTypeOperation()
                                .equals(TypeOperation.DIAGNOSTIC))
                        .map(ServiceOperation::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add), countOrderInOwner * 2));
        orderById.setTotalAmountDue(totalPriceWithDiscount);
        orderRepository.save(orderById);
        return totalPriceWithDiscount;
    }

    private BigDecimal calculateDiscountedPrice(BigDecimal price, int discount) {
        BigDecimal discountPercent = BigDecimal.valueOf(discount);
        BigDecimal discountMultiplier =
                discountPercent
                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        BigDecimal discountedPrice = price.multiply(BigDecimal.ONE.subtract(discountMultiplier));
        return discountedPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
