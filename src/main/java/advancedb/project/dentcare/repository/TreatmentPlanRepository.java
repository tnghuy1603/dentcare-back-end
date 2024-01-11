package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Integer> {

    List<TreatmentPlan> findByPatient_Id(Integer patientId);

}
