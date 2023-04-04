package project.autoservice.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {
    private Long id;
    private String brand;
    private String model;
    private int yearOfManufacturer;
    private String licensePlate;
    private Long ownerId;
}
