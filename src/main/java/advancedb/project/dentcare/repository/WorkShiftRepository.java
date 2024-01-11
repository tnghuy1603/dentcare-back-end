package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.WorkShift;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkShiftRepository extends JpaRepository<WorkShift, Integer> {

}
