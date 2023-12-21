package advancedb.project.dentcare.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkingSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @ManyToMany
    @JoinTable(name = "dentist_working_schedule",
    joinColumns = @JoinColumn(name = "working_schedule_id"),
    inverseJoinColumns = @JoinColumn(name = "dentist_id"))
    private Set<User> dentists;
}
