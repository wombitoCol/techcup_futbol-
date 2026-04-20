package com.techcup_futbol.techcup_futbol.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    private static final String SECRET =
            "clave-secreta-techcup-futbol-2024-muy-larga-para-hs256";
    private static final long EXPIRATION = 3_600_000L; // 1 hora

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", SECRET);
        ReflectionTestUtils.setField(jwtService, "expiration", EXPIRATION);
    }

    @Test
    @DisplayName("generateToken: genera token no nulo ni vacío")
    void generateToken_notNull() {
        String token = jwtService.generateToken("user@test.com", List.of("ROLE_USER"));
        assertThat(token).isNotNull().isNotBlank();
    }

    @Test
    @DisplayName("generateToken: tiene formato JWT (3 partes)")
    void generateToken_hasThreeParts() {
        String token = jwtService.generateToken("user@test.com", List.of("ROLE_ADMIN"));
        assertThat(token.split("\\.")).hasSize(3);
    }

    @Test
    @DisplayName("extractEmail: extrae correctamente el email del token")
    void extractEmail_correct() {
        String email = "jugador@techcup.edu";
        String token = jwtService.generateToken(email, List.of("ROLE_USER"));
        assertThat(jwtService.extractEmail(token)).isEqualTo(email);
    }

    @Test
    @DisplayName("extractClaims: subject correcto")
    void extractClaims_subject() {
        String token = jwtService.generateToken("admin@test.com", List.of("ROLE_ADMIN"));
        assertThat(jwtService.extractClaims(token).getSubject()).isEqualTo("admin@test.com");
    }

    @Test
    @DisplayName("extractClaims: contiene los roles")
    void extractClaims_roles() {
        String token = jwtService.generateToken("user@test.com", List.of("ROLE_USER", "ROLE_ADMIN"));
        Object roles = jwtService.extractClaims(token).get("roles");
        assertThat(roles).isNotNull();
    }

    @Test
    @DisplayName("isTokenValid: true para token válido con email correcto")
    void isTokenValid_true() {
        String email = "valid@techcup.edu";
        String token = jwtService.generateToken(email, List.of("ROLE_USER"));
        assertThat(jwtService.isTokenValid(token, email)).isTrue();
    }

    @Test
    @DisplayName("isTokenValid: false cuando email no coincide")
    void isTokenValid_wrongEmail() {
        String token = jwtService.generateToken("original@techcup.edu", List.of("ROLE_USER"));
        assertThat(jwtService.isTokenValid(token, "otro@techcup.edu")).isFalse();
    }

    @Test
    @DisplayName("isTokenValid: lanza ExpiredJwtException para token expirado")
    void isTokenValid_expired() {
        // Generar token con expiración en el pasado
        ReflectionTestUtils.setField(jwtService, "expiration", -10_000L);
        String token = jwtService.generateToken("user@test.com", List.of("ROLE_USER"));

        // El token ya expiró → extractClaims lanza ExpiredJwtException
        assertThatThrownBy(() -> jwtService.isTokenValid(token, "user@test.com"))
                .isInstanceOf(ExpiredJwtException.class);
    }
}
