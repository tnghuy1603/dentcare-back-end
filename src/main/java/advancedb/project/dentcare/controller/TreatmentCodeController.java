package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.domain.TreatmentCode;
import advancedb.project.dentcare.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("treatments")
@RequiredArgsConstructor
public class TreatmentCodeController {
    private final TreatmentService treatmentService;
    @GetMapping()
    public ResponseEntity<List<TreatmentCode>> findTreatment(
            @RequestParam(value = "treatmentCategory", required = false) Integer treatmentCategoryId){
        if(treatmentCategoryId == null){
            return ResponseEntity.ok(treatmentService.findAll());
        }
        return ResponseEntity.ok(treatmentService.findByTreatmentCategory(treatmentCategoryId));

    }

}
