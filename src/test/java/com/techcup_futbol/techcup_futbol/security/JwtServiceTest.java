package com.techcup_futbol.techcup_futbol.security;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class JwtServiceTest {

    private JwtService jwtService;

    private static final String SECRET =
            "clave-secreta-techcup-futbol-2024-muy-larga-para-hs256";
    private static final long EXPIRATION = 3_600_000L; // 1 hour

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", SECRET);
        ReflectionTestUtils.setField(jwtService, "expiration", EXPIRATION);
    }

    // ── generateToken ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("generateToken: genera un token no nulo ni vacío")
    void generateToken_notNull() {
        String token = jwtService.generateToken("user@test.com", List.of("ROLE_USER"));
        assertThat(token).isNotNull().isNotBlank();
    }

    @Test
    @DisplayName("generateToken: token tiene formato JWT (3 partes separadas por '.')")
    void generateToken_hasThreeParts() {
        String token = jwtService.generateToken("user@test.com", List.of("ROLE_ADMIN"));
        assertThat(token.split("\\.")).hasSize(3);
    }

    // ── extractEmail ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("extractEmail: extrae correctamente el email del token")
    void extractEmail_correct() {
        String email = "jugador@techcup.edu";
        String token = jwtService.generateToken(email, List.of("ROLE_USER"));
        assertThat(jwtService.extractEmail(token)).isEqualTo(email);
    }

    // ── extractClaims ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("extractClaims: retorna claims con el subject correcto")
    void extractClaims_subject() {
        String token = jwtService.generateToken("admin@test.com", List.of("ROLE_ADMIN"));
        assertThat(jwtService.extractClaims(token).getSubject()).isEqualTo("admin@test.com");
    }

    @Test
    @DisplayName("extractClaims: incluye los roles en el token")
    void extractClaims_roles() {
        String token = jwtService.generateToken("user@test.com", List.of("ROLE_USER", "ROLE_ADMIN"));
        Object roles = jwtService.extractClaims(token).get("roles");
        assertThat(roles).isNotNull();
    }

    // ── isTokenValid ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("isTokenValid: retorna true para token válido y email correcto")
    void isTokenValid_true() {
        String email = "valid@techcup.edu";
        String token = jwtService.generateToken(email, List.of("ROLE_USER"));
        assertThat(jwtService.isTokenValid(token, email)).isTrue();
    }

    @Test
    @DisplayName("isTokenValid: retorna false si el email no coincide")
    void isTokenValid_wrongEmail() {
        String token = jwtService.generateToken("original@techcup.edu", List.of("ROLE_USER"));
        assertThat(jwtService.isTokenValid(token, "otro@techcup.edu")).isFalse();
    }

    @Test
    @DisplayName("TC6 - token expirado lanza excepción al validar")
    void isTokenValid_expired() {
        ReflectionTestUtils.setField(jwtService, "expiration", -1000L);
        String token = jwtService.generateToken("user@techcup.com", List.of("USER"));
        assertThrows(Exception.class,
            () -> jwtService.isTokenValid(token, "user@techcup.com"));
    }
}
