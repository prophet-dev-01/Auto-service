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
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private TypeOperation typeOperation;
    @JoinColumn(name = "order_id")
    @ManyToOne
    private Order order;
    @JoinColumn(name = "master_id")
    @ManyToOne
    private Master master;
    private BigDecimal price;
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus status;

    public enum TypeOperation {
        OIL_CHANGE,
        TIRE_ROTATION,
        BRAKE_REPLACEMENT,
        ENGINE_TUNE_UP,
        DIAGNOSTIC,
        TRANSMISSION_SERVICE
    }

    public enum PaymentStatus {
        PAID,
        UNPAID
    }
}
