package project.autoservice.service;

import project.autoservice.model.PaymentStatus;
import project.autoservice.model.ServiceOperation;

public interface ServiceOperationService {
    ServiceOperation save(ServiceOperation serviceOperation);

    ServiceOperation findById(Long id);

    void updateStatus(Long id, PaymentStatus status);
}
