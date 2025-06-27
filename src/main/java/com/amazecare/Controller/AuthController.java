package com.amazecare.Controller;

import com.amazecare.config.JwtTokenProvider;
import com.amazecare.dto.AuthRequest;
import com.amazecare.dto.AuthResponse;
import com.amazecare.entity.User;
import com.amazecare.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody AuthRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("USER")
                .build();

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
    	Authentication authentication = authenticationManager.authenticate(
    		    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
    		);

    		// Just to "use" the variable, this is optional but clean
    		if (!authentication.isAuthenticated()) {
    		    return ResponseEntity.status(401).build();
    		}

        String token = jwtTokenProvider.generateToken(request.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
