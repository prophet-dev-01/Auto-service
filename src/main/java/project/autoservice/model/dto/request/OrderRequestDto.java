package project.autoservice.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import project.autoservice.model.OrderStatus;

@Getter
@Setter
public class OrderRequestDto {
    private Long carId;
    private String problemDescription;
    private LocalDate acceptanceDate;
    private List<Long> serviceOperationsId;
    private List<Long> productsId;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private OrderStatus orderStatus;
    private BigDecimal totalAmountDue;
}
