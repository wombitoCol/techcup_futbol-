package com.techcup_futbol.techcup_futbol.controllers;

import com.techcup_futbol.techcup_futbol.dto.Request.MatchRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.MatchResponseDTO;
import com.techcup_futbol.techcup_futbol.service.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchControllerTest {

    @Mock MatchService matchService;
    @InjectMocks MatchController matchController;

    private MatchResponseDTO responseDTO;
    private MatchRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        responseDTO = new MatchResponseDTO();
        responseDTO.setId(1L);
        responseDTO.setState("SCHEDULED");
        responseDTO.setHomeTeamName("Equipo A");
        responseDTO.setAwayTeamName("Equipo B");

        requestDTO = new MatchRequestDTO();
        requestDTO.setTournamentId(10L);
        requestDTO.setHomeTeamId(1L);
        requestDTO.setAwayTeamId(2L);
        requestDTO.setMatchDate(LocalDateTime.now().plusDays(1));
        requestDTO.setRound("Jornada 1");
    }

    @Test
    @DisplayName("createMatch: retorna 201 CREATED con el partido creado")
    void createMatch_returns201() {
        when(matchService.createMatch(any())).thenReturn(responseDTO);
        ResponseEntity<MatchResponseDTO> response = matchController.createMatch(requestDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("getMatchById: retorna 200 OK con el partido")
    void getMatchById_returns200() {
        when(matchService.getMatchById(1L)).thenReturn(responseDTO);
        ResponseEntity<MatchResponseDTO> response = matchController.getMatchById(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("getMatchesByTournament: retorna 200 OK con lista")
    void getMatchesByTournament_returns200() {
        when(matchService.getMatchesByTournament(10L)).thenReturn(List.of(responseDTO));
        ResponseEntity<List<MatchResponseDTO>> response = matchController.getMatchesByTournament(10L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    @DisplayName("getMatchesByTeam: retorna 200 OK con partidos del equipo")
    void getMatchesByTeam_returns200() {
        when(matchService.getMatchesByTeam(1L)).thenReturn(List.of(responseDTO));
        ResponseEntity<List<MatchResponseDTO>> response = matchController.getMatchesByTeam(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).hasSize(1);
    }

    @Test
    @DisplayName("registerResult: retorna 200 OK")
    void registerResult_returns200() {
        requestDTO.setHomeTeamGoals(2);
        requestDTO.setAwayTeamGoals(1);
        when(matchService.registerResult(eq(1L), any())).thenReturn(responseDTO);
        ResponseEntity<MatchResponseDTO> response = matchController.registerResult(1L, requestDTO);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("deleteMatch: retorna 204 NO CONTENT")
    void deleteMatch_returns204() {
        doNothing().when(matchService).deleteMatch(1L);
        ResponseEntity<Void> response = matchController.deleteMatch(1L);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(matchService).deleteMatch(1L);
    }
}
