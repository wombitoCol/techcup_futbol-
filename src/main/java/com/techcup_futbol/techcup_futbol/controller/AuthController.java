package com.techcup_futbol.techcup_futbol.controller;

import com.techcup_futbol.techcup_futbol.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Security and login endpoints")
public class AuthController {

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Validates the user's email and password.")
    public ResponseEntity<String> login(@RequestBody User loginData) {
        if ("demo@tournament.com".equals(loginData.getEmail()) && "1234".equals(loginData.getPassword())) {
            return ResponseEntity.ok("Successful Authentication");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}