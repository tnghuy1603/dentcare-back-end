package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.domain.TreatmentPlan;
import advancedb.project.dentcare.dto.AddTreatmentPlanRequest;
import advancedb.project.dentcare.dto.UpdateTreatmentPlanRequest;
import advancedb.project.dentcare.service.TreatmentPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treatment-plans")
@RequiredArgsConstructor
public class TreatmentPlanController {
    private final TreatmentPlanService treatmentPlanService;
    @GetMapping("{patientId}")
    public ResponseEntity<?> findTreatmentPlanByPatient(@PathVariable Integer patientId){
        return ResponseEntity.ok(treatmentPlanService.findByPatient(patientId));
    }
    @PostMapping
    public ResponseEntity<?> addTreatmentPlan(@RequestBody AddTreatmentPlanRequest request){
        return ResponseEntity.ok(treatmentPlanService.addTreatmentPlan(request));
    }
    @PutMapping("/{treatmentPlanId}")
    public ResponseEntity<?> updateTreatmentPlan(@PathVariable Integer treatmentPlanId, @RequestBody UpdateTreatmentPlanRequest request){
        return ResponseEntity.ok(treatmentPlanService.udpateTreatmentPlan(request));
    }






}
