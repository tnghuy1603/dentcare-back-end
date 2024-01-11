package advancedb.project.dentcare.controller;

import advancedb.project.dentcare.domain.User;
import advancedb.project.dentcare.dto.LoginRequest;
import advancedb.project.dentcare.dto.LoginResponse;
import advancedb.project.dentcare.service.AuthService;
import advancedb.project.dentcare.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestParam(name = "token") String accessToken,
                                           @AuthenticationPrincipal User user){
        return ResponseEntity.ok(authService.validateToken(accessToken, user));
    }

    
}
