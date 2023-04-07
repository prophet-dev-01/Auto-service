package project.autoservice.model.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import project.autoservice.model.Service;

@Getter
@Setter
public class ServiceResponse {
    private Long id;
    private Service.TypeOperation typeOperation;
    private Long orderId;
    private Long masterId;
    private BigDecimal price;
    private Service.PaymentStatus status;
}
