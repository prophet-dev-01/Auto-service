package project.autoservice.service.impl;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import project.autoservice.model.PaymentStatus;
import project.autoservice.model.ServiceOperation;
import project.autoservice.repository.ServiceOperationRepository;
import project.autoservice.service.ServiceOperationService;

@Service
public class ServiceOperationServiceImpl implements ServiceOperationService {
    private final ServiceOperationRepository operationRepository;

    public ServiceOperationServiceImpl(ServiceOperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public ServiceOperation save(ServiceOperation serviceOperation) {
        return operationRepository.save(serviceOperation);
    }

    @Override
    public ServiceOperation findById(Long id) {
        return operationRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find ServiceOperation by id: " + id));
    }

    @Override
    public void updateStatus(Long id, PaymentStatus status) {
        ServiceOperation serviceOperation = operationRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find service with id: " + id));
        serviceOperation.setStatus(status);
        operationRepository.save(serviceOperation);
    }
}
