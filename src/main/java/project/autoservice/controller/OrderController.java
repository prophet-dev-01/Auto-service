package project.autoservice.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.autoservice.mapper.ModelMapper;
import project.autoservice.model.Order;
import project.autoservice.model.dto.request.OrderRequestDto;
import project.autoservice.model.dto.response.OrderResponseDto;
import project.autoservice.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ModelMapper<Order, OrderResponseDto, OrderRequestDto> orderMapper;
    private final OrderService orderService;

    public OrderController(ModelMapper<Order, OrderResponseDto, OrderRequestDto> orderMapper,
                           OrderService orderService) {
        this.orderMapper = orderMapper;
        this.orderService = orderService;
    }

    @PostMapping
    public OrderResponseDto create(@RequestBody OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        order.setAcceptanceDate(LocalDate.now());
        return orderMapper.toDto(orderService.save(order));
    }

    @PutMapping("/{id}/products")
    public void addProduct(@PathVariable Long id,
                            @RequestParam Long productId) {
        orderService.addProduct(id, productId);
    }

    @PutMapping("/{id}")
    public OrderResponseDto update(@PathVariable Long id, @RequestBody OrderRequestDto requestDto) {
        Order order = orderMapper.toModel(requestDto);
        order.setId(id);
        return orderMapper.toDto(orderService.update(order));
    }

    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id,
                                  @RequestParam Order.OrderStatus status) {
        orderService.updateStatus(id, status);
    }

    @GetMapping("/{id}/price")
    public BigDecimal getTotalPrice(@PathVariable Long id) {
        return orderService.getTotalPrice(id);
    }
}
