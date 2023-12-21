package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Invoice;
import advancedb.project.dentcare.repository.InvoiceRepository;
import advancedb.project.dentcare.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public List<Invoice> findByPatientId(Integer patientId) {
        return invoiceRepository.findByPatientId(patientId);
    }
}
