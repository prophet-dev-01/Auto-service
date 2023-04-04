package project.autoservice.model.dto.request;

import java.math.BigDecimal;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import project.autoservice.model.OrderStatus;

@Getter
@Setter
public class OrderRequestDto {
    private Long carId;
    private String problemDescription;
    private List<Long> serviceOperationsId;
    private List<Long> productsId;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private OrderStatus orderStatus;
    private BigDecimal totalAmountDue;
}
