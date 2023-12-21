package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    @Query("select m from Patient p JOIN p.allergicMedicines m" )
    List<Medication> findAllergicByPatientId(Integer patientId);


}
