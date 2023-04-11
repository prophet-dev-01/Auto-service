package project.autoservice.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import project.autoservice.model.Service;
import project.autoservice.repository.ServiceRepository;
import project.autoservice.service.ServiceService;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public Service save(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public List<Service> findAllByMasterId(Long id) {
        return serviceRepository.findAllByMasterId(id);
    }

    @Override
    public List<Service> findAllByIds(List<Long> ids) {
        return serviceRepository.findAllByIdIn(ids);
    }

    @Override
    public Service findById(Long id) {
        return serviceRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find ServiceOperation by id: " + id));
    }

    @Override
    public void updateStatus(Long id, Service.PaymentStatus status) {
        serviceRepository.updateStatus(status, id);
    }
}
