package project.autoservice.model.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterRequestDto {
    private String firstName;
    private String lastName;
    private String middleName;
    private List<Long> completedOrderId;
}
