package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.TreatmentCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<TreatmentCode, Integer> {
    List<TreatmentCode> findByTreatmentCategory_Id(Integer treatmentCategoryId);
}
