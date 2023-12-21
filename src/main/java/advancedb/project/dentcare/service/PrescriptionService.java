package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Medication;
import advancedb.project.dentcare.domain.Patient;
import advancedb.project.dentcare.domain.Prescription;
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
        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("No patient with id" + request.getPatientId()));
        Prescription prescription = Prescription.builder()
                .createdAt(LocalDateTime.now())
                .quantity(request.getQuantity())
                .medication(medication)
                .patient(patient)
                .unit(request.getUnit())
                .build();
        return prescriptionRepository.save(prescription);
    }



    public List<Prescription> findByTreatmentPlanId(Integer treatmentPlanId) {
        return null;
    }

    public List<Prescription> findByPatient(Integer patientId) {
        return prescriptionRepository.findByPatient_Id(patientId);
    }

}
