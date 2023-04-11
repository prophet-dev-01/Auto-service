package project.autoservice.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import project.autoservice.model.Master;
import project.autoservice.model.Service;
import project.autoservice.repository.MasterRepository;
import project.autoservice.service.MasterService;
import project.autoservice.service.ServiceService;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class MasterServiceImpl implements MasterService {
    private static final int DECIMAL_SCALE = 2;
    private static final BigDecimal MAX_PERCENT = BigDecimal.valueOf(100);
    private static final BigDecimal MASTER_SALARY_PERCENT = BigDecimal.valueOf(40);
    private final ServiceService operationService;
    private final MasterRepository masterRepository;

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
    public BigDecimal getSalary(Long id) {
        List<Service> serviceOperations
                = operationService.findAllByMasterId(id)
                .stream()
                .filter(serviceOperation -> serviceOperation
                        .getStatus().equals(Service.PaymentStatus.UNPAID))
                .toList();
        serviceOperations.forEach(
                serviceOperation -> operationService
                        .updateStatus(serviceOperation.getId(), Service.PaymentStatus.PAID));
        return serviceOperations.stream()
                .map(Service::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .multiply(MASTER_SALARY_PERCENT)
                .divide(MAX_PERCENT, DECIMAL_SCALE, RoundingMode.HALF_UP);
    }
}
