package project.autoservice.model.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerRequestDto {
    private List<Long> carsId;
    private List<Long> ordersId;
}
