package project.autoservice.mapper.impl;

import org.springframework.stereotype.Component;
import project.autoservice.mapper.ModelMapper;
import project.autoservice.model.Master;
import project.autoservice.model.Service;
import project.autoservice.model.dto.request.MasterRequestDto;
import project.autoservice.model.dto.response.MasterResponseDto;
import project.autoservice.service.ServiceService;

@Component
public class MasterMapper
        implements ModelMapper<Master, MasterResponseDto, MasterRequestDto> {
    private final ServiceService serviceService;

    public MasterMapper(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @Override
    public Master toModel(MasterRequestDto request) {
        Master master = new Master();
        master.setFirstName(request.getFirstName());
        master.setLastName(request.getLastName());
        master.setMiddleName(request.getMiddleName());
        master.setServices(serviceService.findAllByIds(request.getServiceIds()));
        return master;
    }

    @Override
    public MasterResponseDto toDto(Master model) {
        MasterResponseDto masterResponseDto = new MasterResponseDto();
        masterResponseDto.setId(model.getId());
        masterResponseDto.setFirstName(model.getFirstName());
        masterResponseDto.setLastName(model.getLastName());
        masterResponseDto.setMiddleName(model.getMiddleName());
        masterResponseDto.setServiceOperationIds(model.getServices()
                .stream()
                .map(Service::getId)
                .toList());
        return masterResponseDto;
    }
}
