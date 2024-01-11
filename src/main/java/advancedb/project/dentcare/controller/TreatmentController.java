package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.dto.UpdateTreatmentRequest;
import advancedb.project.dentcare.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("treatments")
public class TreatmentController {
    private final TreatmentService treatmentService;
    @GetMapping("statistics")
    public ResponseEntity<?> getStatisticByDateAndDentist(@RequestParam LocalDate from, @RequestParam LocalDate to, @RequestParam int dentistId){
        return ResponseEntity.ok(treatmentService.getStatisticByDateAndDentist(from, to, dentistId));
    }
    @GetMapping
    public ResponseEntity<?> findByTreatmentPlan(@RequestParam(name = "treatmentPlan") Integer treatmentPlanId){
        return ResponseEntity.ok(treatmentService.findByTreatmentPlan(treatmentPlanId));
    }

    @PutMapping
    public ResponseEntity<?> updateTreatment(@RequestBody UpdateTreatmentRequest request){
        return ResponseEntity.ok(treatmentService.updateTreatment(request));
    }
}
