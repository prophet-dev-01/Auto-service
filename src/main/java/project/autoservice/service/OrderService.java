package project.autoservice.service;

import project.autoservice.model.Order;
import project.autoservice.model.OrderStatus;

import java.math.BigDecimal;

public interface OrderService {
    Order save(Order order);

    Order findById(Long id);

    void addProduct(Long id, Long productId);

    void updateStatus(Long id, OrderStatus orderStatus);

    BigDecimal getTotalPrice(Long id);
}
