package project.autoservice.model.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerRequestDto {
    private String firstName;
    private String lastName;
    private List<Long> carIds;
    private List<Long> orderIds;
}
