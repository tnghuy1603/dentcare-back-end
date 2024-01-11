package advancedb.project.dentcare.service;

import advancedb.project.dentcare.domain.User;
import advancedb.project.dentcare.dto.LoginRequest;
import advancedb.project.dentcare.dto.LoginResponse;
import advancedb.project.dentcare.repository.UserRepository;
import advancedb.project.dentcare.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    public LoginResponse login(LoginRequest request) {
        Authentication authToken = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        UserDetails userDetails = (UserDetails) authToken.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authToken);

        String accessToken = jwtUtils.generateToken(userDetails);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .message("Logged in")
                .build();
    }

    public boolean validateToken(String accessToken, User user) {
        return jwtUtils.validateToken(accessToken, user);
    }
}
