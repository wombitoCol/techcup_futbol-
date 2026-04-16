package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
import com.techcup_futbol.techcup_futbol.mappers.TournamentMapper;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;
import com.techcup_futbol.techcup_futbol.repository.TournamentRepository;
import com.techcup_futbol.techcup_futbol.service.TournamentServiceImpl.TournamentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TournamentServiceImplTest {

    @Mock private TournamentRepository tournamentRepository;
    @Mock private TournamentMapper tournamentMapper;

    @InjectMocks private TournamentServiceImpl tournamentService;

    private Tournament savedTournament;
    private TournamentRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        requestDTO = TournamentRequestDTO.builder()
                .name("Copa Techcup 2025")
                .startDate(LocalDateTime.now().plusDays(10))
                .finishDateToRegister(LocalDateTime.now().plusDays(5))
                .costPerTeam(100.0)
                .description("Torneo universitario")
                .type("robin")
                .state(TournamentState.DRAFT)
                .build();

        savedTournament = new Tournament(
                requestDTO.getName(), requestDTO.getStartDate(), null,
                requestDTO.getFinishDateToRegister(), requestDTO.getCostPerTeam(),
                requestDTO.getDescription(), requestDTO.getType(), TournamentState.DRAFT);
        savedTournament.setId(1L);
    }

    // ── createTournament ──────────────────────────────────────────────────────

    @Test
    @DisplayName("createTournament: crea torneo con state explícito")
    void createTournament_withExplicitState() {
        when(tournamentRepository.save(any())).thenReturn(savedTournament);

        TournamentResponseDTO result = tournamentService.createTournament(requestDTO);

        assertThat(result).isNotNull();
        assertThat(result.getName()).isEqualTo("Copa Techcup 2025");
        assertThat(result.getState()).isEqualTo(TournamentState.DRAFT);
        verify(tournamentRepository).save(any(Tournament.class));
    }

    @Test
    @DisplayName("createTournament: usa DRAFT como estado por defecto si no se provee state")
    void createTournament_defaultStateDraft() {
        requestDTO.setState(null);
        when(tournamentRepository.save(any())).thenReturn(savedTournament);

        TournamentResponseDTO result = tournamentService.createTournament(requestDTO);

        assertThat(result.getState()).isEqualTo(TournamentState.DRAFT);
    }

    @Test
    @DisplayName("createTournament: mapea todos los campos correctamente")
    void createTournament_allFieldsMapped() {
        when(tournamentRepository.save(any())).thenReturn(savedTournament);

        TournamentResponseDTO result = tournamentService.createTournament(requestDTO);

        assertThat(result.getType()).isEqualTo("robin");
        assertThat(result.getCostPerTeam()).isEqualTo(100.0);
        assertThat(result.getDescription()).isEqualTo("Torneo universitario");
    }

    // ── updateTournament ──────────────────────────────────────────────────────

    @Test
    @DisplayName("updateTournament: actualiza torneo existente")
    void updateTournament_success() {
        TournamentRequestDTO updateDTO = TournamentRequestDTO.builder()
                .name("Copa Actualizada")
                .startDate(LocalDateTime.now().plusDays(15))
                .finishDateToRegister(LocalDateTime.now().plusDays(8))
                .costPerTeam(150.0)
                .description("Actualizado")
                .type("elimination")
                .state(TournamentState.ACTIVE)
                .build();

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(savedTournament));
        when(tournamentRepository.save(any())).thenReturn(savedTournament);

        TournamentResponseDTO result = tournamentService.updateTournament(1L, updateDTO);

        assertThat(result).isNotNull();
        verify(tournamentRepository).save(savedTournament);
    }

    @Test
    @DisplayName("updateTournament: no cambia estado si dto.state es null")
    void updateTournament_stateNullNotChanged() {
        TournamentRequestDTO updateDTO = TournamentRequestDTO.builder()
                .name("Sin cambio de state")
                .startDate(LocalDateTime.now().plusDays(15))
                .finishDateToRegister(LocalDateTime.now().plusDays(8))
                .costPerTeam(80.0)
                .type("robin")
                .state(null)
                .build();

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(savedTournament));
        when(tournamentRepository.save(any())).thenReturn(savedTournament);

        tournamentService.updateTournament(1L, updateDTO);

        // state original (DRAFT) debe mantenerse
        assertThat(savedTournament.getState()).isEqualTo(TournamentState.DRAFT);
    }

    @Test
    @DisplayName("updateTournament: lanza excepción si torneo no existe")
    void updateTournament_notFound() {
        when(tournamentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> tournamentService.updateTournament(99L, requestDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Tournament not found");
    }

    // ── deleteTournament ──────────────────────────────────────────────────────

    @Test
    @DisplayName("deleteTournament: elimina torneo existente")
    void deleteTournament_success() {
        when(tournamentRepository.existsById(1L)).thenReturn(true);
        doNothing().when(tournamentRepository).deleteById(1L);

        assertThatCode(() -> tournamentService.deleteTournament(1L)).doesNotThrowAnyException();
        verify(tournamentRepository).deleteById(1L);
    }

    @Test
    @DisplayName("deleteTournament: lanza excepción si no existe")
    void deleteTournament_notFound() {
        when(tournamentRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> tournamentService.deleteTournament(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Tournament not found");
    }
}
