package project.autoservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.autoservice.mapper.ModelMapper;
import project.autoservice.model.Service;
import project.autoservice.model.dto.request.ServiceRequest;
import project.autoservice.model.dto.response.ServiceResponse;
import project.autoservice.service.ServiceService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;
    private final ModelMapper<Service,
            ServiceResponse,
            ServiceRequest> operationMapper;

    @PostMapping
    public ServiceResponse create(@RequestBody ServiceRequest serviceRequest) {
        Service operation = serviceService
                .save(operationMapper.toModel(serviceRequest));
        return operationMapper.toDto(operation);
    }

    @PutMapping("/{id}")
    public ServiceResponse update(@PathVariable Long id,
                                  @RequestBody ServiceRequest serviceRequest) {
        Service service = operationMapper.toModel(serviceRequest);
        service.setId(id);
        return operationMapper.toDto(serviceService.save(service));
    }

    @PatchMapping("/{id}")
    public void updateStatus(@PathVariable Long id,
                                        @RequestParam Service.PaymentStatus status) {
        serviceService.updateStatus(id, status);
    }
}
