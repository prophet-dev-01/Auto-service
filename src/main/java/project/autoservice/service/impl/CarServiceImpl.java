package project.autoservice.service.impl;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import project.autoservice.model.Car;
import project.autoservice.repository.CarRepository;
import project.autoservice.service.CarService;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find car by id: " + id));
    }
}