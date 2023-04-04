package project.autoservice.model.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {
    private String brand;
    private String model;
    private int yearOfManufacturer;
    private String licensePlate;
    private Long ownerId;
}
