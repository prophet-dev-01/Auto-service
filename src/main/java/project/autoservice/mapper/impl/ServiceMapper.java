package project.autoservice.mapper.impl;

import java.util.Optional;
import org.springframework.stereotype.Component;
import project.autoservice.mapper.ModelMapper;
import project.autoservice.model.Master;
import project.autoservice.model.Order;
import project.autoservice.model.Service;
import project.autoservice.model.dto.request.ServiceRequest;
import project.autoservice.model.dto.response.ServiceResponse;
import project.autoservice.service.MasterService;
import project.autoservice.service.OrderService;

@Component
public class ServiceMapper implements ModelMapper<Service,
        ServiceResponse,
        ServiceRequest> {
    private final OrderService orderService;
    private final MasterService masterService;

    public ServiceMapper(OrderService orderService, MasterService masterService) {
        this.orderService = orderService;
        this.masterService = masterService;
    }

    @Override
    public Service toModel(ServiceRequest request) {
        Service serviceOperation = new Service();
        serviceOperation.setStatus(request.getStatus());
        serviceOperation.setPrice(request.getPrice());
        serviceOperation.setOrder(request.getOrderId() == null
                ? null
                : orderService.findById(request.getOrderId()));
        serviceOperation.setMaster(request.getMasterId() == null
                ? null
                : masterService.findById(request.getMasterId()));
        serviceOperation.setTypeOperation(request.getTypeOperation());
        return serviceOperation;
    }

    @Override
    public ServiceResponse toDto(Service model) {
        ServiceResponse operationResponse =
                new ServiceResponse();
        operationResponse.setId(model.getId());
        operationResponse.setPrice(model.getPrice());
        operationResponse.setStatus(model.getStatus());
        operationResponse.setOrderId(
                Optional.ofNullable(model.getOrder())
                        .map(Order::getId).orElse(null));
        operationResponse.setMasterId(
                Optional.ofNullable(model.getMaster())
                        .map(Master::getId).orElse(null));
        operationResponse.setTypeOperation(model.getTypeOperation());
        return operationResponse;
    }
}
