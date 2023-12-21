package advancedb.project.dentcare.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class TreatmentPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startingDate;
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

}
