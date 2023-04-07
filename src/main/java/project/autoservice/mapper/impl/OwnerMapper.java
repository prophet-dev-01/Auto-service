package project.autoservice.mapper.impl;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import project.autoservice.mapper.ModelMapper;
import project.autoservice.model.Car;
import project.autoservice.model.Order;
import project.autoservice.model.Owner;
import project.autoservice.model.dto.request.OwnerRequestDto;
import project.autoservice.model.dto.response.OwnerResponseDto;
import project.autoservice.service.CarService;
import project.autoservice.service.OrderService;

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
        owner.setCars(carService.findAllByIds(request.getCarIds()));
        owner.setOrders(orderService.findAllByIds(request.getOrderIds()));
        return owner;
    }

    @Override
    public OwnerResponseDto toDto(Owner owner) {
        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
        ownerResponseDto.setId(owner.getId());
        ownerResponseDto.setCarIds(owner.getCars()
                .stream()
                .map(Car::getId)
                .collect(Collectors.toList()));
        ownerResponseDto.setOrderIds(owner.getOrders()
                .stream()
                .map(Order::getId)
                .collect(Collectors.toList()));
        return ownerResponseDto;
    }
}
