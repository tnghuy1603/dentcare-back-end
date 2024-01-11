package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Medication;
import advancedb.project.dentcare.domain.Patient;
import advancedb.project.dentcare.domain.Prescription;
import advancedb.project.dentcare.domain.TreatmentPlan;
import advancedb.project.dentcare.dto.AddPrescriptionRequest;
import advancedb.project.dentcare.dto.CustomResponse;
import advancedb.project.dentcare.exception.ResourceNotFoundException;
import advancedb.project.dentcare.repository.MedicationRepository;
import advancedb.project.dentcare.repository.PatientRepository;
import advancedb.project.dentcare.repository.PrescriptionRepository;
import advancedb.project.dentcare.repository.TreatmentPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final MedicationRepository medicationRepository;
    private final TreatmentPlanRepository treatmentPlanRepository;
    private final PatientRepository patientRepository;

    public Prescription addPrescription(AddPrescriptionRequest request) {
        Medication medication = medicationRepository.findById(request.getMedicineId())
                .orElseThrow(() -> new ResourceNotFoundException("No medication with id = " + request.getMedicineId()));
        TreatmentPlan treatmentPlan = treatmentPlanRepository.findById(request.getTreatmentPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("No treatment plan with id = " + request.getTreatmentPlanId()));
        Prescription prescription = Prescription.builder()
                .createdAt(LocalDateTime.now())
                .frequency(request.getFrequency())
                .dosage(request.getDosage())
                .treatmentPlan(treatmentPlan)
                .quantity(request.getQuantity())
                .medication(medication)
                .quantity(request.getQuantity())
                .build();
        return prescriptionRepository.save(prescription);
    }
    public void deletePrescription(Integer prescriptionId){
        prescriptionRepository.deleteById(prescriptionId);
    }

    public List<Prescription> findByTreatmentPlan(Integer treatmentPlanId) {
        return prescriptionRepository.findByTreatmentPlan_Id(treatmentPlanId);
    }
    public List<Prescription> findByPatient(Integer patientId){
        return null;
    }



}
