package advancedb.project.dentcare.service;
import advancedb.project.dentcare.domain.Medication;
import advancedb.project.dentcare.dto.AddMedicationRequest;
import advancedb.project.dentcare.dto.UpdateMedicineRequest;
import advancedb.project.dentcare.exception.ResourceNotFoundException;
import advancedb.project.dentcare.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationService {
    private final MedicationRepository medicationRepository;


    public List<Medication> getAllMedications() {
        return medicationRepository.findAll();
    }

    public Medication updateMedication(UpdateMedicineRequest request) {
        Medication medicationToUpdate = medicationRepository.findById(request.getMedicineId())
                .orElseThrow(() -> new ResourceNotFoundException("No medication with id" + request.getMedicineId()));
        medicationToUpdate.setName(request.getName());
        medicationToUpdate.setPrice(request.getPrice());
        medicationToUpdate.setDescription(request.getDescription());
        medicationToUpdate.setExpireDate(request.getExpireDate());
        return medicationRepository.save(medicationToUpdate);
    }


    public Medication addMedication(AddMedicationRequest request) {
        Medication medication = Medication.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .expireDate(request.getExpireDate())
                .build();
        return medicationRepository.save(medication);
    }
}
