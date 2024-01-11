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

public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dosage;
    private int quantity;
    private String frequency;
    private BigDecimal price;
    @ManyToOne(targetEntity = TreatmentPlan.class)
    @JoinColumn(name = "treatment_plan_id")
    private TreatmentPlan treatmentPlan;
    @ManyToOne(targetEntity = Medication.class)
    @JoinColumn(name = "medication_id")
    private Medication medication;
    private LocalDateTime createdAt;

}
