package com.techcup_futbol.techcup_futbol.controllers;

import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.model.User.UserType;
import com.techcup_futbol.techcup_futbol.repository.UserRepository;
import com.techcup_futbol.techcup_futbol.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock UserRepository userRepository;
    @Mock JwtService jwtService;
    @Mock PasswordEncoder passwordEncoder;
    @InjectMocks AuthController authController;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L).email("user@test.edu").password("$2a$10$hashed")
                .name("Test User").type(UserType.STUDENT)
                .birthDate(LocalDate.of(2000, 1, 1))
                .gender("M").phoneNumber(300L)
                .build();
    }

    @Test
    @DisplayName("login: credenciales correctas → 200 con token")
    void login_validCredentials_returns200() {
        when(userRepository.findByEmail("user@test.edu")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("plainPass", "$2a$10$hashed")).thenReturn(true);
        when(jwtService.generateToken(anyString(), any())).thenReturn("jwt.token.here");

        Map<String, String> body = Map.of("email", "user@test.edu", "password", "plainPass");
        ResponseEntity<?> response = authController.login(body);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        @SuppressWarnings("unchecked")
        Map<String, String> responseBody = (Map<String, String>) response.getBody();
        assertThat(responseBody.get("token")).isEqualTo("jwt.token.here");
    }

    @Test
    @DisplayName("login: contraseña incorrecta → 401")
    void login_wrongPassword_returns401() {
        when(userRepository.findByEmail("user@test.edu")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong", "$2a$10$hashed")).thenReturn(false);

        Map<String, String> body = Map.of("email", "user@test.edu", "password", "wrong");
        ResponseEntity<?> response = authController.login(body);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
        assertThat(response.getBody()).isEqualTo("Contraseña incorrecta");
    }

    @Test
    @DisplayName("login: usuario no encontrado → lanza excepción")
    void login_userNotFound_throwsException() {
        when(userRepository.findByEmail("noexiste@test.edu")).thenReturn(Optional.empty());

        Map<String, String> body = Map.of("email", "noexiste@test.edu", "password", "any");
        assertThatThrownBy(() -> authController.login(body))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Usuario no encontrado");
    }

    @Test
    @DisplayName("hashPassword: retorna el hash BCrypt del password")
    void hashPassword_returnsHash() {
        when(passwordEncoder.encode("myPassword")).thenReturn("$2a$10$hashedValue");

        String result = authController.hashPassword("myPassword");

        assertThat(result).isEqualTo("$2a$10$hashedValue");
        verify(passwordEncoder).encode("myPassword");
    }
}
