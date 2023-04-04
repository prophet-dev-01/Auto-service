package project.autoservice.model.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerResponseDto {
    private Long id;
    private List<Long> carsId;
    private List<Long> ordersId;
}
