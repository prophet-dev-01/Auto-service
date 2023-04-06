package project.autoservice.service;

import java.math.BigDecimal;
import project.autoservice.model.Order;
import project.autoservice.model.OrderStatus;

public interface OrderService {
    Order save(Order order);

    Order update(Order order);

    Order findById(Long id);

    void addProduct(Long id, Long productId);

    void updateStatus(Long id, OrderStatus orderStatus);

    BigDecimal getTotalPrice(Long id);
}
