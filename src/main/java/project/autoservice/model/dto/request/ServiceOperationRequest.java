package project.autoservice.model.dto.request;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import project.autoservice.model.PaymentStatus;
import project.autoservice.model.TypeOperation;

@Getter
@Setter
public class ServiceOperationRequest {
    private Long orderId;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private TypeOperation typeOperation;
    private Long masterId;
    private BigDecimal price;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private PaymentStatus status;
}
