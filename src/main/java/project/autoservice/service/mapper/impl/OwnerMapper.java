package project.autoservice.service.mapper.impl;

import org.springframework.stereotype.Component;
import project.autoservice.model.Car;
import project.autoservice.model.Order;
import project.autoservice.model.Owner;
import project.autoservice.model.dto.request.OwnerRequestDto;
import project.autoservice.model.dto.response.OwnerResponseDto;
import project.autoservice.service.CarService;
import project.autoservice.service.OrderService;
import project.autoservice.service.mapper.ModelMapper;

import java.util.stream.Collectors;

@Component
public class OwnerMapper implements ModelMapper<Owner, OwnerResponseDto, OwnerRequestDto> {
    private final CarService carService;
    private final OrderService orderService;

    public OwnerMapper(CarService carService, OrderService orderService) {
        this.carService = carService;
        this.orderService = orderService;
    }

    @Override
    public Owner toModel(OwnerRequestDto request) {
        Owner owner = new Owner();
        owner.setCars(request.getCarsId()
                .stream()
                .map(carService::findById)
                .collect(Collectors.toList()));
        owner.setOrders(request.getOrdersId()
                .stream()
                .map(orderService::findById)
                .collect(Collectors.toList()));
        return owner;
    }

    @Override
    public OwnerResponseDto toDto(Owner owner) {
        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
        ownerResponseDto.setId(owner.getId());
        ownerResponseDto.setCarsId(owner.getCars()
                .stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        ownerResponseDto.setOrdersId(owner.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return ownerResponseDto;
    }
}
