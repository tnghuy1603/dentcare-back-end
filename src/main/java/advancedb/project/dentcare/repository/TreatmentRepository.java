package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
    @Query("SELECT SUM(t.fee) FROM Treatment t WHERE t.treatmentPlan.id = :treatmentPlanId")
    BigDecimal getTotalFeeByTreatmentPlanId(@Param("treatmentPlanId") Integer treatmentPlanId);
    @Query(nativeQuery = true, value = "SELECT treatment.perform_at, COUNT(*) FROM treatment_plan JOIN treatment ON treatment.treatment_plan_id = treatment_plan.id WHERE treatment_plan.dentist_id = :dentistId AND treatment.perform_at BETWEEN :fromDate AND :toDate GROUP BY treatment.perform_at")
    List<Object[]> getTreatmentCountByDateAndDentist(@Param("dentistId") Integer dentistId, @Param("fromDate") LocalDate from, @Param("toDate") LocalDate to);
    List<Treatment> findByTreatmentPlan_Id(Integer treatmentPlanId);
}
