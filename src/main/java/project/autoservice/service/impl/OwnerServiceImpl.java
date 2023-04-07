package project.autoservice.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import project.autoservice.model.Car;
import project.autoservice.model.Order;
import project.autoservice.model.Owner;
import project.autoservice.repository.CarRepository;
import project.autoservice.repository.OwnerRepository;
import project.autoservice.service.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final CarRepository carRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository, CarRepository carRepository) {
        this.ownerRepository = ownerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Owner save(Owner owner) {
        List<Car> cars = owner.getCars();
        cars.forEach(car -> car.setOwner(owner));
        carRepository.saveAll(cars);
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
