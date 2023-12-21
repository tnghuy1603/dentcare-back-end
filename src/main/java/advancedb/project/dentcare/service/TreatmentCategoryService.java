package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.TreatmentCategory;
import advancedb.project.dentcare.repository.TreatmentCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentCategoryService {
    private final TreatmentCategoryRepository treatmentCategoryRepository;

    public List<TreatmentCategory> findAll() {
        return treatmentCategoryRepository.findAll();
    }

}
