package project.autoservice.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.autoservice.model.Car;
import project.autoservice.model.Order;
import project.autoservice.model.Owner;
import project.autoservice.model.dto.request.CarRequestDto;
import project.autoservice.model.dto.request.OrderRequestDto;
import project.autoservice.model.dto.request.OwnerRequestDto;
import project.autoservice.model.dto.response.CarResponseDto;
import project.autoservice.model.dto.response.OrderResponseDto;
import project.autoservice.model.dto.response.OwnerResponseDto;
import project.autoservice.service.OwnerService;
import project.autoservice.service.mapper.ModelMapper;

@RestController
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;
    private final ModelMapper<Owner, OwnerResponseDto, OwnerRequestDto> ownerMapper;
    private final ModelMapper<Order, OrderResponseDto, OrderRequestDto> orderMapper;
    private final ModelMapper<Car, CarResponseDto, CarRequestDto> carMapper;

    public OwnerController(OwnerService ownerService,
                           ModelMapper<Owner, OwnerResponseDto, OwnerRequestDto> ownerMapper,
                           ModelMapper<Order, OrderResponseDto, OrderRequestDto> orderMapper,
                           ModelMapper<Car, CarResponseDto, CarRequestDto> carMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
        this.orderMapper = orderMapper;
        this.carMapper = carMapper;
    }

    @PostMapping
    public OwnerResponseDto create(@RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerService.save(ownerMapper.toModel(requestDto));
        return ownerMapper.toDto(owner);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody OwnerRequestDto requestDto) {
        Owner owner = ownerMapper.toModel(requestDto);
        owner.setId(id);
        ownerService.save(owner);
    }

    @GetMapping("/{id}/orders")
    public List<OrderResponseDto> getAllOrders(@PathVariable Long id) {
        return ownerService.findOrdersById(id).stream()
                .map(orderMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/cars")
    public List<CarResponseDto> getAllCars(@PathVariable Long id) {
        return ownerService.findById(id).getCars()
                .stream()
                .map(carMapper::toDto)
                .collect(Collectors.toList());
    }
}
