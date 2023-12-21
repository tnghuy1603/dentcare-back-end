package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.TreatmentCode;
import advancedb.project.dentcare.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentService {
    private final TreatmentRepository treatmentRepository;

    public List<TreatmentCode> findAll() {
        return treatmentRepository.findAll();
    }

    public List<TreatmentCode> findByTreatmentCategory(Integer treatmentCategoryId) {
        return treatmentRepository.findByTreatmentCategory_Id(treatmentCategoryId);
    }
}
