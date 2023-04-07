package project.autoservice.service;

import java.util.List;
import project.autoservice.model.Car;

public interface CarService {
    Car save(Car car);

    List<Car> findAllByIds(List<Long> ids);

    Car findById(Long id);
}
