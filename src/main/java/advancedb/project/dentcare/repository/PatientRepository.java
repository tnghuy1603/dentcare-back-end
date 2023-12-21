package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.Medication;
import advancedb.project.dentcare.domain.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM allergic_medicine WHERE patient_id = :patientId AND medicine_id = :medicineId", nativeQuery = true)
    void deleteAllergicMedicine(@Param("patientId") Integer patientId, @Param("medicineId") Integer medicineId);
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO allergic_medicine(patient_id, medicine_id) VALUES (:patientId, :medicineId)", nativeQuery = true)
    void insertAllergicMedicine(@Param("patientId") Integer patientId, @Param("medicineId") Integer medicineId);
}
