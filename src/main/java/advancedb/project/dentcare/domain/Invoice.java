package advancedb.project.dentcare.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Invoice {
    @Id
    private Integer id;
    private BigDecimal totalFee;
    private BigDecimal paidAmount;
    private BigDecimal changeAmount;
    private String note;
    private String method;
    private LocalDateTime createdAt;

    @OneToOne
    @MapsId
    private TreatmentPlan treatmentPlan;
}
