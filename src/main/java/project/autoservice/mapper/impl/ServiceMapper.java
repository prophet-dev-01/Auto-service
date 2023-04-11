package project.autoservice.mapper.impl;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.autoservice.mapper.ModelMapper;
import project.autoservice.model.Master;
import project.autoservice.model.Order;
import project.autoservice.model.Service;
import project.autoservice.model.dto.request.ServiceRequest;
import project.autoservice.model.dto.response.ServiceResponse;
import project.autoservice.service.MasterService;
import project.autoservice.service.OrderService;

@RequiredArgsConstructor
@Component
public class ServiceMapper implements ModelMapper<Service,
        ServiceResponse,
        ServiceRequest> {
    private final OrderService orderService;
    private final MasterService masterService;

    @Override
    public Service toModel(ServiceRequest request) {
        Service service = new Service();
        service.setStatus(request.getStatus());
        service.setPrice(request.getPrice());
        service.setOrder(request.getOrderId() == null
                ? null
                : orderService.findById(request.getOrderId()));
        service.setMaster(request.getMasterId() == null
                ? null
                : masterService.findById(request.getMasterId()));
        service.setTypeOperation(request.getTypeOperation());
        return service;
    }

    @Override
    public ServiceResponse toDto(Service model) {
        ServiceResponse serviceResponse =
                new ServiceResponse();
        serviceResponse.setId(model.getId());
        serviceResponse.setPrice(model.getPrice());
        serviceResponse.setStatus(model.getStatus());
        serviceResponse.setOrderId(
                Optional.ofNullable(model.getOrder())
                        .map(Order::getId).orElse(null));
        serviceResponse.setMasterId(
                Optional.ofNullable(model.getMaster())
                        .map(Master::getId).orElse(null));
        serviceResponse.setTypeOperation(model.getTypeOperation());
        return serviceResponse;
    }
}
