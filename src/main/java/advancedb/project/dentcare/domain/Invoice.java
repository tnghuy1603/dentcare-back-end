package advancedb.project.dentcare.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    private Long id;
    @Column(columnDefinition = "TEXT")
    private BigDecimal paidAmount;
    private String note;
    private String method;
    private LocalDateTime createdAt;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private TreatmentPlan treatmentPlan;
}
