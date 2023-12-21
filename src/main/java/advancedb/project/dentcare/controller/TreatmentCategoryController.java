package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.domain.TreatmentCategory;
import advancedb.project.dentcare.service.TreatmentCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("treatment-categories")
@RequiredArgsConstructor
public class TreatmentCategoryController {
    private final TreatmentCategoryService treatmentCategoryService;
    @GetMapping
    public ResponseEntity<?> findAllCategories(){
        return ResponseEntity.ok(treatmentCategoryService.findAll());
    }
}
