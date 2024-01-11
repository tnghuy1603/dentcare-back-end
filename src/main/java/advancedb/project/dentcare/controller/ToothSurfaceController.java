package advancedb.project.dentcare.controller;


import advancedb.project.dentcare.service.ToothSurfaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/surfaces")
@RequiredArgsConstructor
@Slf4j
public class ToothSurfaceController {
    private final ToothSurfaceService toothSurfaceService;
    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(toothSurfaceService.findAll());
    }


}
