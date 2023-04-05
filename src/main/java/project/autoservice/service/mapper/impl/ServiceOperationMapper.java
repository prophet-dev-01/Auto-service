package project.autoservice.service.mapper.impl;

import java.util.Optional;
import org.springframework.stereotype.Component;
import project.autoservice.model.Master;
import project.autoservice.model.Order;
import project.autoservice.model.ServiceOperation;
import project.autoservice.model.dto.request.ServiceOperationRequest;
import project.autoservice.model.dto.response.ServiceOperationResponse;
import project.autoservice.service.MasterService;
import project.autoservice.service.OrderService;
import project.autoservice.service.mapper.ModelMapper;

@Component
public class ServiceOperationMapper implements ModelMapper<ServiceOperation,
        ServiceOperationResponse,
        ServiceOperationRequest> {
    private final OrderService orderService;
    private final MasterService masterService;

    public ServiceOperationMapper(OrderService orderService, MasterService masterService) {
        this.orderService = orderService;
        this.masterService = masterService;
    }

    @Override
    public ServiceOperation toModel(ServiceOperationRequest request) {
        ServiceOperation serviceOperation = new ServiceOperation();
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
    public ServiceOperationResponse toDto(ServiceOperation model) {
        ServiceOperationResponse operationResponse =
                new ServiceOperationResponse();
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
