package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.repository.InvoiceRepository;
import advancedb.project.dentcare.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    @GetMapping
    public ResponseEntity<?> findByPatient(@RequestParam("patient") Integer patientId){
        return ResponseEntity.ok(invoiceService.findByPatientId(patientId));
    }
}
