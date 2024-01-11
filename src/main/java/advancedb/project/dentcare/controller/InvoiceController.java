package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.dto.CheckoutRequest;
import advancedb.project.dentcare.repository.InvoiceRepository;
import advancedb.project.dentcare.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;
    @GetMapping
    public ResponseEntity<?> findInvoice(@RequestParam(name = "patient", required = false) Integer patientId,
                                            @RequestParam(name = "treatmentPlan", required = false) Integer treatmentPlanId){
        if(patientId != null){
            return ResponseEntity.ok(invoiceService.findByPatientId(patientId));
        }
        if(treatmentPlanId != null){
            return ResponseEntity.ok(invoiceService.getInvoiceByTreatmentPlan(treatmentPlanId));
        }
        return ResponseEntity.ok(null);
    }
    @PutMapping("/checkout")
    public ResponseEntity<?> checkout(@RequestBody CheckoutRequest request){
        return ResponseEntity.ok(invoiceService.checkout(request));
    }
}
