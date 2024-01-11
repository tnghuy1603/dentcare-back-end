package advancedb.project.dentcare.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.List;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(name = "uq_tooth_tooth_surface", columnNames = {"tooth_id", "surface_id"}))
public class ToothSelection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "tooth_id")
    private Tooth tooth;
    @ManyToOne
    @JoinColumn(name = "surface_id")
    private ToothSurface toothSurface;

    @ManyToMany(mappedBy = "toothSelectionList")
    @JsonIgnore
    private List<TreatmentPlan> treatmentPlans;


}
