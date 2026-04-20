package com.techcup_futbol.techcup_futbol.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtFilterTest {

    @Mock JwtService jwtService;
    @InjectMocks JwtFilter jwtFilter;

    @BeforeEach
    void clearContext() {
        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("doFilterInternal: sin header Authorization → pasa sin autenticar")
    void noAuthHeader_passesThrough() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        jwtFilter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("doFilterInternal: header sin 'Bearer ' → pasa sin autenticar")
    void authHeaderWithoutBearer_passesThrough() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Basic abc123");
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        jwtFilter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("doFilterInternal: token válido → establece autenticación en contexto")
    void validToken_setsAuthentication() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer valid.jwt.token");
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        Claims claims = mock(Claims.class);
        when(jwtService.extractEmail("valid.jwt.token")).thenReturn("user@test.edu");
        when(jwtService.extractClaims("valid.jwt.token")).thenReturn(claims);
        when(claims.get("roles", List.class)).thenReturn(List.of("STUDENT"));

        jwtFilter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNotNull();
        assertThat(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .isEqualTo("user@test.edu");
    }

    @Test
    @DisplayName("doFilterInternal: email null en token → no establece autenticación")
    void nullEmail_noAuthentication() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer bad.token");
        MockHttpServletResponse response = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        when(jwtService.extractEmail("bad.token")).thenReturn(null);

        jwtFilter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    @Test
    @DisplayName("shouldNotFilter: retorna true para rutas /api/auth/")
    void shouldNotFilter_authPath() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setRequestURI("/api/auth/login");
        assertThat(jwtFilter.shouldNotFilter(req)).isTrue();
    }

    @Test
    @DisplayName("shouldNotFilter: retorna true para rutas /swagger-ui/")
    void shouldNotFilter_swaggerPath() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setRequestURI("/swagger-ui/index.html");
        assertThat(jwtFilter.shouldNotFilter(req)).isTrue();
    }

    @Test
    @DisplayName("shouldNotFilter: retorna true para rutas /v3/api-docs/")
    void shouldNotFilter_apiDocsPath() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setRequestURI("/v3/api-docs/swagger-config");
        assertThat(jwtFilter.shouldNotFilter(req)).isTrue();
    }

    @Test
    @DisplayName("shouldNotFilter: retorna false para rutas protegidas")
    void shouldNotFilter_protectedPath() throws Exception {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setRequestURI("/api/matches/1");
        assertThat(jwtFilter.shouldNotFilter(req)).isFalse();
    }
}
