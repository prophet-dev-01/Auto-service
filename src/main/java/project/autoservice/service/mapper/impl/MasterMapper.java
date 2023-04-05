package project.autoservice.service.mapper.impl;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import project.autoservice.model.Master;
import project.autoservice.model.ServiceOperation;
import project.autoservice.model.dto.request.MasterRequestDto;
import project.autoservice.model.dto.response.MasterResponseDto;
import project.autoservice.service.ServiceOperationService;
import project.autoservice.service.mapper.ModelMapper;

@Component
public class MasterMapper
        implements ModelMapper<Master, MasterResponseDto, MasterRequestDto> {
    private final ServiceOperationService operationService;

    public MasterMapper(ServiceOperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public Master toModel(MasterRequestDto request) {
        Master master = new Master();
        master.setFirstName(request.getFirstName());
        master.setLastName(request.getLastName());
        master.setMiddleName(request.getMiddleName());
        master.setServiceOperations(request.getOperationsId()
                .stream()
                .map(operationService::findById)
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
        masterResponseDto.setServiceOperationsId(model.getServiceOperations()
                .stream()
                .map(ServiceOperation::getId)
                .toList());
        return masterResponseDto;
    }
}
