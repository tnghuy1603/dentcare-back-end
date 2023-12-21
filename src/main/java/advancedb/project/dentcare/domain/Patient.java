package advancedb.project.dentcare.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDate dob;
    private String gender;
    private String phoneNumber;
    private String address;
    private String oralHealth;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "allergic_medicine",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "medicine_id"))
    @JsonIgnore
    private Set<Medication> allergicMedicines = new HashSet<>();
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<Appointment> appointments;
    @OneToMany(mappedBy = "patient")
    @JsonIgnore
    private List<TreatmentPlan> treatmentPlans;

}
