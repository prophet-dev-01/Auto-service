package project.autoservice.service;

import project.autoservice.model.Car;

public interface CarService {
    Car save(Car car);

    Car findById(Long id);
}
