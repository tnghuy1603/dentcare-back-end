package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.TreatmentCode;
import advancedb.project.dentcare.repository.TreatmentCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentCodeService {

    private final TreatmentCodeRepository treatmentCodeRepository;

    public List<TreatmentCode> findAll() {
        return treatmentCodeRepository.findAll();
    }

    public List<TreatmentCode> findByTreatmentCategory(Integer treatmentCategoryId) {
        return treatmentCodeRepository.findByTreatmentCategory_Id(treatmentCategoryId);
    }
}
