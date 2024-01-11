package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.domain.Patient;
import advancedb.project.dentcare.dto.AddPatientRequest;
import advancedb.project.dentcare.dto.UpdateOralHealthRequest;
import advancedb.project.dentcare.dto.UpdatePatientRequest;
import advancedb.project.dentcare.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<?> findPatient(@RequestParam(name = "patientName", required = false) String name,
                                         @RequestParam(name = "size", defaultValue = "10") int size,
                                         @RequestParam(name = "page")int page){
        if(name == null){
            return ResponseEntity.ok(patientService.getAll(page, size));
        }

        return ResponseEntity.ok(patientService.findByName(name, page, size));
    }
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody AddPatientRequest addPatientRequest){
        return ResponseEntity.status(201).body(patientService.addPatient(addPatientRequest));
    }
    @PutMapping ("{patientId}")
    ResponseEntity<Patient> updatePatient(@PathVariable Integer patientId, @RequestBody UpdatePatientRequest request){
        return ResponseEntity.ok(patientService.updatePatient(request));

    }
    @DeleteMapping("allergic/{patientId}/{medicineId}")
    public ResponseEntity<?> deleteAllergicMedicine(@PathVariable("medicineId") Integer medicineId, @PathVariable("patientId") Integer patientId){
        return ResponseEntity.ok(patientService.deleteAllergicMedicine(patientId, medicineId));
    }
    @PostMapping("allergic/{patientId}")
    public ResponseEntity<?> addAllergicMedicine(@RequestParam(name = "medicine") Integer medicineId, @PathVariable Integer patientId){
        return ResponseEntity.status(201).body(patientService.addAllergicMedicine(patientId, medicineId));
    }
    @GetMapping("allergic/{patientId}")
    public ResponseEntity<?> getAllAllergicMedicines(@PathVariable Integer patientId){
        return ResponseEntity.ok(patientService.findAllergicByPatient(patientId));
    }
    @PutMapping("oral-health/{patientId}")
    public ResponseEntity<?> updateOralHealth(@PathVariable Integer patientId, @RequestBody UpdateOralHealthRequest request){
        return ResponseEntity.ok(patientService.updateOralHealth(patientId, request));
    }
    






}
