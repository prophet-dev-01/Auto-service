package project.autoservice.model.dto.request;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import project.autoservice.model.Service;

@Getter
@Setter
public class ServiceRequest {
    private Long orderId;
    private Service.TypeOperation typeOperation;
    private Long masterId;
    private BigDecimal price;
    private Service.PaymentStatus status;
}
