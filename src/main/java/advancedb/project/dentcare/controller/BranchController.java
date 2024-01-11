package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("branches")
@RequiredArgsConstructor
public class BranchController {
    private final BranchService branchService;
    @GetMapping
    public ResponseEntity<?> getAllBranch(){
        return ResponseEntity.ok(branchService.findAll());
    }
}
