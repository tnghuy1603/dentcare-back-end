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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {
    private final PatientRepository patientRepository;
    private final MedicationRepository medicationRepository;

    public Map<String, Object> getAll(int page, int size) {
        Map<String, Object> response = new HashMap<>();
        Pageable paging = PageRequest.of(page, size);
        Page<Patient> patientPage = patientRepository.findAll(paging);
        response.put("totalPages", patientPage.getTotalPages());
        response.put("patients", patientPage.getContent());
        response.put("currentPage", patientPage.getNumber());
        response.put("totals", patientPage.getTotalElements());
        return response;
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

    public Medication addAllergicMedicine(Integer patientId, Integer medicineId) {
        if(!patientRepository.existsById(patientId)){
            throw new ResourceNotFoundException("No patient with id = " + patientId);
        }
        if(!medicationRepository.existsById(medicineId)){
            throw new ResourceNotFoundException("No medication with id" + patientId);
        }
        Medication medication = medicationRepository.findById(medicineId).
                orElseThrow(() -> new ResourceNotFoundException("No medication with id" + patientId));
        patientRepository.insertAllergicMedicine(patientId, medicineId);
        return medication;
    }

    public Patient updateOralHealth(Integer patientId, UpdateOralHealthRequest request) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("No patient with id" + patientId));
        patient.setOralHealth(request.getOralHealth());
        return patientRepository.save(patient);
    }

    public Map<String, Object> findByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> response = new HashMap<>();
        Page<Patient> patientPage = patientRepository.findByNameContaining(name, pageable);
        response.put("totalPages", patientPage.getTotalPages());
        response.put("patients", patientPage.getContent());
        response.put("currentPage", patientPage.getNumber());
        response.put("totals", patientPage.getTotalElements());
        return response;
    }
}
