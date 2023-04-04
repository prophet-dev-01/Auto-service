package project.autoservice.service.mapper.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import project.autoservice.model.Master;
import project.autoservice.model.Order;
import project.autoservice.model.dto.request.MasterRequestDto;
import project.autoservice.model.dto.response.MasterResponseDto;
import project.autoservice.service.impl.OrderServiceImpl;
import project.autoservice.service.mapper.ModelMapper;

@Component
public class MasterMapper
        implements ModelMapper<Master, MasterResponseDto, MasterRequestDto> {
    private final OrderServiceImpl orderService;

    public MasterMapper(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @Override
    public Master toModel(MasterRequestDto request) {
        Master master = new Master();
        master.setFirstName(request.getFirstName());
        master.setLastName(request.getLastName());
        master.setMiddleName(request.getMiddleName());
        master.setCompletedOrder(request.getCompletedOrderId()
                .stream().
                map(orderService::findById)
                .collect(Collectors.toList()));
        return master;
    }

    @Override
    public MasterResponseDto toDto(Master model) {
        MasterResponseDto masterResponseDto = new MasterResponseDto();
        masterResponseDto.setId(model.getId());
        masterResponseDto.setFirstName(model.getFirstName());
        masterResponseDto.setLastName(model.getLastName());
        masterResponseDto.setMiddleName(model.getMiddleName());
        masterResponseDto.setCompletedOrderId(model.getCompletedOrder()
                .stream()
                .map(Order::getId)
                .toList());
        return masterResponseDto;
    }
}
