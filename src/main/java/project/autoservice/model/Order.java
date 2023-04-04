package project.autoservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Car car;
    private String problemDescription;
    private LocalDate acceptanceDate;
    @OneToMany
    private List<ServiceOperation> serviceOperations;
    @OneToMany
    private List<Product> products;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal totalAmountDue;
    private LocalDate completionDate;
}
