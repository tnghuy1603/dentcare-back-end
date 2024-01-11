package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.dto.AddAppointmentRequest;
import advancedb.project.dentcare.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
@Slf4j
public class AppointmentController {
    private final AppointmentService appointmentService;
//    @GetMapping
//    public ResponseEntity<?> findAppointmentByPatientAndStatus(@RequestParam("patient") Integer patientId, @RequestParam("status") String status){
//        return ResponseEntity.ok(appointmentService.findByPatientIdAndStatus(patientId, status));
//    }

    @GetMapping("/{date}")
    public ResponseEntity<?> findAppointments(
            @PathVariable(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(name = "room", required = false) String room,
            @RequestParam(name = "patientName", required = false) String patientName,
            @RequestParam(name = "dentist", required = false) Integer dentistId
    ){



        if(room != null){
            return ResponseEntity.ok(appointmentService.findByRoomAndDate(room, date));
        }
        if(patientName != null){
            return ResponseEntity.ok(appointmentService.findByPatientNameAndDate(patientName, date));
        }

        if(dentistId != null){
            log.info("Hit dentist id");
            return ResponseEntity.ok(appointmentService.findByDentistAndDate(dentistId, date));
        }
        log.info("Not hit dentist id");
        return ResponseEntity.ok(appointmentService.findByDate(date));
    }
    @PostMapping
    public ResponseEntity<?> addAppointment(@RequestBody AddAppointmentRequest request){
        return ResponseEntity.status(201).body(appointmentService.addAppointment(request));
    }
    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Integer appointmentId){
        return ResponseEntity.ok(appointmentService.deleteAppointment(appointmentId));
    }
//    @GetMapping("/{appointmentId}")
//    public ResponseEntity<?> getAppointment(@PathVariable Integer appointmentId){
//        return ResponseEntity.ok(appointmentService.getAppointment(appointmentId));
//    }
    @GetMapping("/statistic")
    public ResponseEntity<?> getStatisticByDentist(@RequestParam Integer dentistId, @RequestParam LocalDate from, @RequestParam LocalDate to){
        return ResponseEntity.ok(appointmentService.getStatisticByDentist(dentistId, from, to));
    }



}
