package advancedb.project.dentcare.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Table(name = "re-examination", uniqueConstraints = @UniqueConstraint(name = "uq_re-examination", columnNames = {"date", "treatment_plan_id"}))
public class ReExamination {
    @Id
    private Integer id;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "treatment_plan_id")
    private TreatmentPlan treatmentPlan;

}
