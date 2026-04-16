package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.dto.Request.TeamRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TeamResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @Mock private TeamRepository teamRepository;

    @InjectMocks private TeamServiceImpl teamService;

    private Team team;

    @BeforeEach
    void setUp() {
        team = new Team(1L, "Los Tigres");
    }

    // ── createTeam ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("createTeam: crea equipo y retorna DTO")
    void createTeam_success() {
        TeamRequestDTO dto = new TeamRequestDTO();
        dto.setName("Los Tigres");

        when(teamRepository.save(any(Team.class))).thenReturn(team);

        TeamResponseDTO result = teamService.createTeam(dto);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Los Tigres");
        verify(teamRepository).save(any(Team.class));
    }

    @Test
    @DisplayName("createTeam: el nombre se asigna correctamente")
    void createTeam_nameIsSet() {
        TeamRequestDTO dto = new TeamRequestDTO();
        dto.setName("Águilas FC");

        Team saved = new Team(2L, "Águilas FC");
        when(teamRepository.save(any(Team.class))).thenReturn(saved);

        TeamResponseDTO result = teamService.createTeam(dto);

        assertThat(result.getName()).isEqualTo("Águilas FC");
    }

    // ── getTeamById ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("getTeamById: retorna DTO cuando equipo existe")
    void getTeamById_found() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        TeamResponseDTO result = teamService.getTeamById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getName()).isEqualTo("Los Tigres");
    }

    @Test
    @DisplayName("getTeamById: lanza excepción si equipo no existe")
    void getTeamById_notFound() {
        when(teamRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> teamService.getTeamById(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Team not found");
    }
}
