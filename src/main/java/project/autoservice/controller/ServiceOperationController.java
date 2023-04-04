package project.autoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.autoservice.model.PaymentStatus;
import project.autoservice.model.ServiceOperation;
import project.autoservice.model.dto.request.ServiceOperationRequest;
import project.autoservice.model.dto.response.ServiceOperationResponse;
import project.autoservice.service.ServiceOperationService;
import project.autoservice.service.mapper.ModelMapper;

@RestController
@RequestMapping("services")
public class ServiceOperationController {
    private final ServiceOperationService operationService;
    private final ModelMapper<ServiceOperation, ServiceOperationResponse, ServiceOperationRequest> operationMapper;

    public ServiceOperationController(ServiceOperationService operationService,
                                      ModelMapper<ServiceOperation, ServiceOperationResponse, ServiceOperationRequest> operationMapper) {
        this.operationService = operationService;
        this.operationMapper = operationMapper;
    }

    @PostMapping
    public ServiceOperationResponse create(@RequestBody ServiceOperationRequest operationRequest) {
        ServiceOperation operation = operationService
                .save(operationMapper.toModel(operationRequest));
        return operationMapper.toDto(operation);
    }

    @PutMapping("/{id}")
    public ServiceOperationResponse update(@PathVariable Long id,
                                           @RequestBody ServiceOperationRequest operationRequest) {
        ServiceOperation serviceOperation = operationMapper.toModel(operationRequest);
        serviceOperation.setId(id);
        return operationMapper.toDto(operationService.save(serviceOperation));
    }

    @PutMapping("/{id}/status")
    public ServiceOperationResponse updateStatus(@PathVariable Long id,
                                                 @RequestParam PaymentStatus status) {
        ServiceOperation operationServiceById = operationService.findById(id);
        operationServiceById.setStatus(status);
        return operationMapper.toDto(operationService.save(operationServiceById));
    }
}
