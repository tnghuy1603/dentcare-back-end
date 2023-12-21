package advancedb.project.dentcare.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Treatment {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "treatment_code_id")
    private TreatmentCode treatmentCode;
    @ManyToOne
    @JoinColumn(name = "treatmnet_plan_id")
    private TreatmentPlan treatmentPlan;

    private LocalDate performAt;
}
