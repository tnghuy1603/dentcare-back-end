package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.Medication;
import advancedb.project.dentcare.domain.Patient;
import advancedb.project.dentcare.dto.AddPatientRequest;
import advancedb.project.dentcare.dto.CustomResponse;
import advancedb.project.dentcare.dto.UpdateOralHealthRequest;
import advancedb.project.dentcare.dto.UpdatePatientRequest;
import advancedb.project.dentcare.exception.ResourceNotFoundException;
import advancedb.project.dentcare.repository.MedicationRepository;
import advancedb.project.dentcare.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient addPatient(AddPatientRequest addPatientRequest) {
        Patient patient = Patient.builder()
                .name(addPatientRequest.getName())
                .gender(addPatientRequest.getGender())
                .phoneNumber(addPatientRequest.getPhoneNumber())
                .dob(addPatientRequest.getDob())
                .address(addPatientRequest.getAddress())
                .build();
        return patientRepository.save(patient);
    }


    public Patient updatePatient(UpdatePatientRequest request) {
        Patient patientToUpdate = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient with that id" + request.getPatientId()));
        patientToUpdate.setAddress(request.getAddress());
        patientToUpdate.setName(request.getName());
        patientToUpdate.setDob(request.getDob());
        patientToUpdate.setGender(request.getGender());
        patientToUpdate.setPhoneNumber(request.getPhoneNumber());
        return patientRepository.save(patientToUpdate);
    }
    public CustomResponse deleteAllergicMedicine(Integer patientId, Integer medicineId) {
        if(!patientRepository.existsById(patientId)){
            throw new ResourceNotFoundException("No patient with id = " + patientId);
        }
        if(!medicationRepository.existsById(medicineId)){
            throw new ResourceNotFoundException("No medication with id" + patientId);
        }
        patientRepository.deleteAllergicMedicine(patientId, medicineId);
        return new CustomResponse("Delete successfully", List.of());
    }



    public List<Medication> findAllergicByPatient(Integer patientId) {
        if(!patientRepository.existsById(patientId)){
            throw new ResourceNotFoundException("No patient with that id");
        }
        return medicationRepository.findAllergicByPatientId(patientId);
    }

    public CustomResponse addAllergicMedicine(Integer patientId, Integer medicineId) {
        if(!patientRepository.existsById(patientId)){
            throw new ResourceNotFoundException("No patient with id = " + patientId);
        }
        if(!medicationRepository.existsById(medicineId)){
            throw new ResourceNotFoundException("No medication with id" + patientId);
        }
        patientRepository.insertAllergicMedicine(patientId, medicineId);
        return new CustomResponse("Added", List.of());
    }

    public Patient updateOralHealth(Integer patientId, UpdateOralHealthRequest request) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("No patient with id" + patientId));
        patient.setOralHealth(request.getOralHealth());
        return patientRepository.save(patient);
    }

}
