package advancedb.project.dentcare.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "dentist_id", "work_shift_id"})})

public class WorkingSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "dentist_id")
    private User dentist;

    @ManyToOne
    @JoinColumn(name = "work_shift_id")
    private WorkShift workShift;

}
