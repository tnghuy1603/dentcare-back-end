package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {

    List<Prescription> findByTreatmentPlan_Id(Integer treatmentPlanId);
//    @Query("SELECT p FROM Prescription p WHERE p.")
//    List<Prescription> findByPatientId(Integer patientId);
    @Query("SELECT SUM(p.price) FROM Prescription p WHERE p.treatmentPlan.id =:treatmentPlanId")
    BigDecimal totalFeeByTreatmentPlan(@Param("treatmentPlanId") Integer treatmentPlanId);
    
}
