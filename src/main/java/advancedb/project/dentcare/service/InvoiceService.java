package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Invoice;

import advancedb.project.dentcare.domain.Prescription;
import advancedb.project.dentcare.domain.Treatment;
import advancedb.project.dentcare.domain.TreatmentPlan;
import advancedb.project.dentcare.dto.CheckoutRequest;
import advancedb.project.dentcare.exception.ResourceNotFoundException;
import advancedb.project.dentcare.repository.InvoiceRepository;
import advancedb.project.dentcare.repository.PrescriptionRepository;
import advancedb.project.dentcare.repository.TreatmentPlanRepository;
import advancedb.project.dentcare.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final TreatmentRepository treatmentRepository;
    private final PrescriptionRepository prescriptionRepository;

    public List<Invoice> findByPatientId(Integer patientId) {
        return invoiceRepository.findByPatientId(patientId);
    }

    public Invoice checkout(CheckoutRequest request) {
        Invoice invoice = invoiceRepository.findById(request.getTreatmentPlanId()).orElse(null);
        if(invoice == null){
            invoice = getInvoiceByTreatmentPlan(request.getTreatmentPlanId());
        }
        invoice.setNote(request.getNote());
        invoice.setPaidAmount(request.getPaidAmount().add(invoice.getPaidAmount()));
        if(invoice.getPaidAmount().compareTo(invoice.getTotalFee()) > 0){
            invoice.setChangeAmount(invoice.getPaidAmount().subtract(invoice.getTotalFee()));
        } else{
            invoice.setChangeAmount(BigDecimal.ZERO);
        }

        invoice.setMethod(request.getMethod());
        invoice = invoiceRepository.save(invoice);
        return invoice;
    }

    public Invoice getInvoiceByTreatmentPlan(Integer treatmentPlanId) {
        Invoice  invoice = invoiceRepository.findById(treatmentPlanId).orElse(null);
        if(invoice != null){
            return invoice;
        }
        TreatmentPlan treatmentPlan = treatmentPlanRepository.findById(treatmentPlanId)
                .orElseThrow(() -> new ResourceNotFoundException("No treatment plan id = " + treatmentPlanId));
        List< Prescription> prescriptions = treatmentPlan.getPrescriptions();
        BigDecimal totalFee = BigDecimal.ZERO;

        BigDecimal treatmentFee = treatmentRepository.getTotalFeeByTreatmentPlanId(treatmentPlanId);
        BigDecimal prescriptionFee = prescriptionRepository.totalFeeByTreatmentPlan(treatmentPlanId);
        log.info("Treatment fee" + treatmentFee);

        totalFee = totalFee.add(treatmentFee);
        if(prescriptionFee != null){
            totalFee = totalFee.add(prescriptionFee);
        }

        invoice =  Invoice.builder()
                .totalFee(totalFee)
                .createdAt(LocalDateTime.now())
                .treatmentPlan(treatmentPlan)
                .paidAmount(BigDecimal.ZERO)
                .changeAmount(BigDecimal.ZERO)
                .build();
        invoice = invoiceRepository.save(invoice);
        return invoice;
    }

}
