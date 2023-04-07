package project.autoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.autoservice.mapper.ModelMapper;
import project.autoservice.model.Car;
import project.autoservice.model.dto.request.CarRequestDto;
import project.autoservice.model.dto.response.CarResponseDto;
import project.autoservice.service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarService carService;
    private final ModelMapper<Car, CarResponseDto, CarRequestDto> carMapper;

    public CarController(CarService carService,
                         ModelMapper<Car, CarResponseDto, CarRequestDto> carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @PostMapping
    public CarResponseDto create(@RequestBody CarRequestDto carRequestDto) {
        Car car = carService.save(carMapper.toModel(carRequestDto));
        return carMapper.toDto(car);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody CarRequestDto carRequestDto) {
        Car car = carMapper.toModel(carRequestDto);
        car.setId(id);
        carService.save(car);
    }
}
