package advancedb.project.dentcare.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "treatment_code_id")
    private TreatmentCode treatmentCode;
    @ManyToOne
    @JoinColumn(name = "treatment_plan_id")
    private TreatmentPlan treatmentPlan;
    private BigDecimal fee;
    private String description;


    private LocalDate performAt;

}
