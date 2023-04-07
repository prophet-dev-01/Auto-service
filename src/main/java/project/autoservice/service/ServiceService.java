package project.autoservice.service;

import java.util.List;
import project.autoservice.model.Service;

public interface ServiceService {
    Service save(Service service);

    List<Service> findAllByMasterId(Long masterId);

    List<Service> findAllByIds(List<Long> ids);

    Service findById(Long id);

    void updateStatus(Long id, Service.PaymentStatus status);
}
