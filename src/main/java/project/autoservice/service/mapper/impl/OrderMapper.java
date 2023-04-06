package project.autoservice.service.mapper.impl;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import project.autoservice.model.Order;
import project.autoservice.model.Product;
import project.autoservice.model.ServiceOperation;
import project.autoservice.model.dto.request.OrderRequestDto;
import project.autoservice.model.dto.response.OrderResponseDto;
import project.autoservice.service.CarService;
import project.autoservice.service.ProductService;
import project.autoservice.service.ServiceOperationService;
import project.autoservice.service.mapper.ModelMapper;

@Component
public class OrderMapper implements ModelMapper<Order, OrderResponseDto, OrderRequestDto> {
    private final CarService carService;
    private final ProductService productService;
    private final ServiceOperationService operationService;

    public OrderMapper(CarService carService, ProductService productService,
                       ServiceOperationService operationService) {
        this.carService = carService;
        this.productService = productService;
        this.operationService = operationService;
    }

    @Override
    public Order toModel(OrderRequestDto request) {
        Order order = new Order();
        order.setCar(carService.findById(request.getCarId()));
        order.setOrderStatus(request.getOrderStatus());
        order.setProducts(request.getProductsId()
                .stream()
                .map(productService::findById)
                .collect(Collectors.toList()));
        order.setProblemDescription(request.getProblemDescription());
        order.setServiceOperations(request.getServiceOperationsId()
                .stream()
                .map(operationService::findById)
                .collect(Collectors.toList()));
        order.setTotalAmountDue(request.getTotalAmountDue());
        order.setAcceptanceDate(request.getAcceptanceDate());
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
        responseDto.setServiceOperationsId(model.getServiceOperations()
                .stream()
                .map(ServiceOperation::getId)
                .collect(Collectors.toList()));
        responseDto.setTotalAmountDue(model.getTotalAmountDue());
        responseDto.setProductsId(model.getProducts()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList()));
        return responseDto;
    }
}
