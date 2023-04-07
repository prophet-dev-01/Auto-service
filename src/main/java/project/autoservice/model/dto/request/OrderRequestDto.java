package project.autoservice.model.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import project.autoservice.model.Order;

@Getter
@Setter
public class OrderRequestDto {
    private Long carId;
    private String problemDescription;
    private List<Long> serviceIds;
    private List<Long> productIds;
    private Order.OrderStatus orderStatus;
}
