package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.dto.AddWorkingScheduleRequest;
import advancedb.project.dentcare.service.WorkingScheduleService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("working-schedules")
@RequiredArgsConstructor
public class WorkingScheduleController {
    private final WorkingScheduleService workingScheduleService;
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(workingScheduleService.findAllSchedule());
    }
    @PostMapping
    public ResponseEntity<?> addSchedule(@RequestBody AddWorkingScheduleRequest request){
        return ResponseEntity.ok(workingScheduleService.addSchedule(request));
    }
}
