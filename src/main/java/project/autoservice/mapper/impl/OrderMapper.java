package project.autoservice.mapper.impl;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.autoservice.mapper.ModelMapper;
import project.autoservice.model.Order;
import project.autoservice.model.Product;
import project.autoservice.model.Service;
import project.autoservice.model.dto.request.OrderRequestDto;
import project.autoservice.model.dto.response.OrderResponseDto;
import project.autoservice.service.CarService;
import project.autoservice.service.ProductService;
import project.autoservice.service.ServiceService;

@RequiredArgsConstructor
@Component
public class OrderMapper implements ModelMapper<Order, OrderResponseDto, OrderRequestDto> {
    private final CarService carService;
    private final ProductService productService;
    private final ServiceService serviceService;

    @Override
    public Order toModel(OrderRequestDto request) {
        Order order = new Order();
        order.setCar(carService.findById(request.getCarId()));
        order.setOrderStatus(request.getOrderStatus());
        order.setProducts(productService.findAllByIds(request.getProductIds()));
        order.setProblemDescription(request.getProblemDescription());
        order.setServices(serviceService.findAllByIds(request.getServiceIds()));
        return order;
    }

    @Override
    public OrderResponseDto toDto(Order model) {
        OrderResponseDto responseDto = new OrderResponseDto();
        responseDto.setId(model.getId());
        responseDto.setCarId(model.getId());
        responseDto.setOrderStatus(model.getOrderStatus());
        responseDto.setProblemDescription(model.getProblemDescription());
        responseDto.setAcceptanceDate(model.getAcceptanceDate());
        responseDto.setCompletionDate(model.getCompletionDate());
        responseDto.setServiceIds(model.getServices()
                .stream()
                .map(Service::getId)
                .collect(Collectors.toList()));
        responseDto.setProductIds(model.getProducts()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
