package project.autoservice.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.autoservice.model.Car;
import project.autoservice.repository.CarRepository;
import project.autoservice.service.CarService;

@RequiredArgsConstructor
@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> findAllByIds(List<Long> ids) {
        return carRepository.findAllByIdIn(ids);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find car by id: " + id));
    }
}
