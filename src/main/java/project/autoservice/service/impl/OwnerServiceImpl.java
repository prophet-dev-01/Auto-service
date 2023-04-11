package project.autoservice.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.autoservice.model.Order;
import project.autoservice.model.Owner;
import project.autoservice.repository.OwnerRepository;
import project.autoservice.service.OwnerService;

@RequiredArgsConstructor
@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    @Override
    public Owner save(Owner owner) {
        owner.getCars().forEach(car -> car.setOwner(owner));
        return ownerRepository.save(owner);
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find Owner by id: " + id));
    }

    @Override
    public List<Order> findOrdersByOwnerId(Long id) {
        return ownerRepository.findOrdersByOwnerId(id);
    }
}
