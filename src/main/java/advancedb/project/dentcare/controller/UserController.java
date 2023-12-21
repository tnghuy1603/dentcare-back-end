package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.domain.User;
import advancedb.project.dentcare.dto.AddUserRequest;
import advancedb.project.dentcare.dto.UpdateUserRequest;
import advancedb.project.dentcare.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<?> findByRole(@RequestParam String role){
        return ResponseEntity.ok(userService.findByRole(role));
    }
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody AddUserRequest request){
        return ResponseEntity.status(201).body(userService.addUser(request));
    }
    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Integer userId, @RequestBody UpdateUserRequest request){
        return ResponseEntity.ok(userService.updateUser(request));
    }




}
