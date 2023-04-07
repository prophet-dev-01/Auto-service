package project.autoservice.model.dto.response;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import project.autoservice.model.Order;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String problemDescription;
    private LocalDate acceptanceDate;
    private List<Long> serviceIds;
    private List<Long> productIds;
    private Order.OrderStatus orderStatus;
    private LocalDate completionDate;
}
