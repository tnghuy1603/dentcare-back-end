package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.WorkingSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkingScheduleRepository extends JpaRepository<WorkingSchedule, Long> {
    List<WorkingSchedule> findByDateAndWorkShift_Id(LocalDate date, Integer workShift);
}
