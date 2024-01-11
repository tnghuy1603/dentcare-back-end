package advancedb.project.dentcare.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class TreatmentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate startDate;

    @Column
    private String status;
    @Column(columnDefinition = "TEXT")
    private String note;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private User dentist;
    @ManyToOne
    @JoinColumn(name = "assistant_id")
    private User assistant;
    @ManyToMany
    @JoinTable(name = "treatment_plan_tooth",
    joinColumns = @JoinColumn(name = "treatment_plan_id"),
    inverseJoinColumns = @JoinColumn(name = "tooth_selection_id"))
    private List<ToothSelection> toothSelectionList;
    @OneToMany(mappedBy = "treatmentPlan", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Treatment> treatments;
    @OneToMany(mappedBy = "treatmentPlan", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Prescription> prescriptions;

}
