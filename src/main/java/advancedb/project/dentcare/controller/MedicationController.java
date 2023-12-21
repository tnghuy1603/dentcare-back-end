package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.domain.Medication;
import advancedb.project.dentcare.dto.AddMedicationRequest;
import advancedb.project.dentcare.dto.UpdateMedicineRequest;
import advancedb.project.dentcare.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medications")
@RequiredArgsConstructor
public class MedicationController {
    private final MedicationService medicationService;
    @GetMapping
    public ResponseEntity<List<Medication>> getAllMedications(){
        return ResponseEntity.ok(medicationService.getAllMedications());
    }
    @PostMapping
    public ResponseEntity<Medication> addMedication(@RequestBody AddMedicationRequest request){
        return ResponseEntity.status(201).body(medicationService.addMedication(request));
    }
    @PutMapping("/{medicineId}")
    public ResponseEntity<Medication> updateMedication(@PathVariable Long medicineId, UpdateMedicineRequest request){
        return ResponseEntity.ok(medicationService.updateMedication(request));
    }






}
