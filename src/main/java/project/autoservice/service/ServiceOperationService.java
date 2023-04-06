package project.autoservice.service;

import java.util.List;
import project.autoservice.model.PaymentStatus;
import project.autoservice.model.ServiceOperation;

public interface ServiceOperationService {
    ServiceOperation save(ServiceOperation serviceOperation);

    List<ServiceOperation> findAllByMasterId(Long id);

    ServiceOperation findById(Long id);

    void updateStatus(Long id, PaymentStatus status);
}
