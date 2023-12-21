package advancedb.project.dentcare.repository;

import advancedb.project.dentcare.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query("SELECT i FROM Invoice i WHERE i.treatmentPlan.patient.id = :patientId")
    List<Invoice> findByPatientId(@RequestParam("patientId") Integer patientId);
}
