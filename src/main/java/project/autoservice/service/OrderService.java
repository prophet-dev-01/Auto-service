package project.autoservice.service;

import java.math.BigDecimal;
import java.util.List;
import project.autoservice.model.Order;

public interface OrderService {
    Order save(Order order);

    Order update(Order order);

    Order findById(Long id);

    List<Order> findAllByIds(List<Long> ids);

    void addProduct(Long id, Long productId);

    void updateStatus(Long id, Order.OrderStatus orderStatus);

    BigDecimal getTotalPrice(Long id);
}
