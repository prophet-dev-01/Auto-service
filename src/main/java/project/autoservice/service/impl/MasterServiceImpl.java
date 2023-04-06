package project.autoservice.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import project.autoservice.model.Master;
import project.autoservice.model.PaymentStatus;
import project.autoservice.model.ServiceOperation;
import project.autoservice.repository.MasterRepository;
import project.autoservice.service.MasterService;
import project.autoservice.service.ServiceOperationService;

@Service
public class MasterServiceImpl implements MasterService {
    private final ServiceOperationService operationService;
    private final MasterRepository masterRepository;

    public MasterServiceImpl(ServiceOperationService operationService,
                             MasterRepository masterRepository) {
        this.operationService = operationService;
        this.masterRepository = masterRepository;
    }

    @Override
    public Master save(Master master) {
        return masterRepository.save(master);
    }

    @Override
    public Master findById(Long id) {
        return masterRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find Master by id: " + id));
    }

    @Transactional
    @Override
    public BigDecimal issuanceOfSalary(Long id) {
        List<ServiceOperation> serviceOperations
                = operationService.findAllByMasterId(id)
                .stream()
                .filter(serviceOperation -> serviceOperation
                        .getStatus().equals(PaymentStatus.UNPAID))
                .toList();
        serviceOperations.forEach(
                serviceOperation -> operationService
                        .updateStatus(serviceOperation.getId(), PaymentStatus.PAID));
        return serviceOperations.stream()
                .map(ServiceOperation::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(BigDecimal.valueOf(40))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}
