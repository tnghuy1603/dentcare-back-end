package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.dto.AddPrescriptionRequest;
import advancedb.project.dentcare.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    @PostMapping
    public ResponseEntity<?> addPrescriptions(@RequestBody AddPrescriptionRequest request) {
        return ResponseEntity.status(201).body(prescriptionService.addPrescription(request));
    }

    @GetMapping
    public ResponseEntity<?> getPrescriptions(@RequestParam(name = "patient") Integer patientId){
        return ResponseEntity.ok(prescriptionService.findByPatient(patientId));
    }





}
