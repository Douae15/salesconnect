package com.salesconnect.backend.controller;

import com.salesconnect.backend.config.CustomUserDetailsService;
import com.salesconnect.backend.config.jwt.JwtTokenProvider;
import com.salesconnect.backend.config.request.LoginRequest;
import com.salesconnect.backend.config.request.RegisterRequest;
import com.salesconnect.backend.config.response.JwtResponse;
import com.salesconnect.backend.dto.UserDTO;
import com.salesconnect.backend.entity.User;
import com.salesconnect.backend.service.AuthService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest authenticationRequest) {

    System.out.println("Tentative de login: " + authenticationRequest.getEmail());
    // Authentification avec l'email et le mot de passe
    Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getEmail(), 
                    authenticationRequest.getPassword()
            )
    );

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtTokenProvider.createToken(authentication);

    // Récupérer les rôles
    List<String> roles = authentication.getAuthorities()
            .stream()
            .map(item -> item.getAuthority())
            .toList();

    // Extraire les infos utilisateur
    User userDetails = (User) authentication.getPrincipal();

    return ResponseEntity.ok(new JwtResponse(
            jwt,
            "Bearer",
            userDetails.getUserId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles
    ));
}

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody RegisterRequest request) {
        UserDTO registeredUser = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("you have access now");
    }
}





