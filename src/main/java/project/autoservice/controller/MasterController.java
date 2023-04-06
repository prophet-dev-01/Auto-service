package project.autoservice.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.autoservice.model.Master;
import project.autoservice.model.ServiceOperation;
import project.autoservice.model.dto.request.MasterRequestDto;
import project.autoservice.model.dto.request.ServiceOperationRequest;
import project.autoservice.model.dto.response.MasterResponseDto;
import project.autoservice.model.dto.response.ServiceOperationResponse;
import project.autoservice.service.MasterService;
import project.autoservice.service.ServiceOperationService;
import project.autoservice.service.mapper.ModelMapper;

@RestController
@RequestMapping("/masters")
public class MasterController {
    private final ModelMapper<ServiceOperation,
            ServiceOperationResponse,
            ServiceOperationRequest> serviceMapper;
    private final ModelMapper<Master,
            MasterResponseDto,
            MasterRequestDto> masterMapper;
    private final MasterService masterService;
    private final ServiceOperationService operationService;

    public MasterController(ModelMapper<ServiceOperation,
            ServiceOperationResponse,
            ServiceOperationRequest> serviceMapper,
                            ModelMapper<Master,
                                    MasterResponseDto,
                                    MasterRequestDto> masterMapper,
                            MasterService masterService,
                            ServiceOperationService operationService) {
        this.serviceMapper = serviceMapper;
        this.masterMapper = masterMapper;
        this.masterService = masterService;
        this.operationService = operationService;
    }

    @PostMapping
    public MasterResponseDto create(@RequestBody MasterRequestDto requestDto) {
        Master master = masterService.save(masterMapper.toModel(requestDto));
        return masterMapper.toDto(master);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody MasterRequestDto requestDto) {
        Master master = masterMapper.toModel(requestDto);
        master.setId(id);
        masterService.save(master);
    }

    @GetMapping("/{id}/orders")
    public List<ServiceOperationResponse> findOperationsById(@PathVariable Long id) {
        return operationService.findAllByMasterId(id)
                .stream().map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/salary")
    public BigDecimal issuanceOfSalary(@PathVariable Long id) {
        return masterService.issuanceOfSalary(id);
    }
}

