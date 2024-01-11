package advancedb.project.dentcare.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String description;
    private BigDecimal fee;

    @ManyToOne
    @JoinColumn(name = "treatment_category_id")
    private TreatmentCategory treatmentCategory;

}
