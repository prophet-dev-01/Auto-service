package project.autoservice.model.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import project.autoservice.model.OrderStatus;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long carId;
    private String problemDescription;
    private LocalDate acceptanceDate;
    private List<Long> serviceOperationsId;
    private List<Long> productsId;
    private OrderStatus orderStatus;
    private BigDecimal totalAmountDue;
    private LocalDate completionDate;
}
