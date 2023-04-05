package project.autoservice.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "service_operations")
public class ServiceOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeOperation typeOperation;
    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order order;
    @JoinColumn(name = "master_id")
    @OneToOne
    private Master master;
    private BigDecimal price;
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;
}
