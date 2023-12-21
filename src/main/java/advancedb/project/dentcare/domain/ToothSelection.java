package advancedb.project.dentcare.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.List;
@Entity
@Getter
@Setter
public class ToothSelection {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "tooth_id")
    private Tooth tooth;
    @ManyToOne
    @JoinColumn(name = "surface_id")
    private ToothSurface toothSurface;

    @ManyToMany(mappedBy = "toothSelectionList")
    private List<TreatmentPlan> treatmentPlans;


}
