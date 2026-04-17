package com.techcup_futbol.techcup_futbol.controllers;

import com.techcup_futbol.techcup_futbol.security.JwtService;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");

        log.info("Intento de login para email: {}", email);

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> {
                log.error("Login fallido: usuario {} no encontrado", email);
                return new RuntimeException("Usuario no encontrado");
            });

        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.warn("Login fallido: contraseña incorrecta para {}", email);
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        // Ahora extrae los nombres de los roles desde la lista
        List<String> roles = user.getRoles().stream()
            .map(role -> role.getName())
            .toList();

        String token = jwtService.generateToken(email, roles);
        log.info("Login exitoso para: {}", email);

        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/hash/{password}")
    public String hashPassword(@PathVariable String password) {
        return passwordEncoder.encode(password);
    }
}