package project.autoservice.model.dto.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import project.autoservice.model.PaymentStatus;
import project.autoservice.model.TypeOperation;

@Getter
@Setter
public class ServiceOperationResponse {
    private Long id;
    private TypeOperation typeOperation;
    private Long orderId;
    private Long masterId;
    private BigDecimal price;
    private PaymentStatus status;
}
