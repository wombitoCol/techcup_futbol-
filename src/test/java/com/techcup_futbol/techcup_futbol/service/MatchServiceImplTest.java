package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.dto.Request.MatchRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.MatchResponseDTO;
import com.techcup_futbol.techcup_futbol.mappers.MatchMapper;
import com.techcup_futbol.techcup_futbol.model.Match.Match;
import com.techcup_futbol.techcup_futbol.model.Match.MatchState;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;
import com.techcup_futbol.techcup_futbol.repository.MatchRepository;
import com.techcup_futbol.techcup_futbol.repository.TeamRepository;
import com.techcup_futbol.techcup_futbol.repository.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {

    @Mock private MatchRepository matchRepository;
    @Mock private TournamentRepository tournamentRepository;
    @Mock private TeamRepository teamRepository;
    @Mock private MatchMapper matchMapper;

    @InjectMocks private MatchServiceImpl matchService;

    private Tournament tournament;
    private Team homeTeam;
    private Team awayTeam;
    private Match match;
    private MatchResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        tournament = new Tournament("Copa Techcup", LocalDateTime.now().plusDays(1),
                null, LocalDateTime.now().plusDays(30), 50.0, "Torneo test", "robin",
                TournamentState.ACTIVE);
        tournament.setId(1L);

        homeTeam = new Team(1L, "Equipo A");
        awayTeam = new Team(2L, "Equipo B");

        match = new Match(tournament, homeTeam, awayTeam, LocalDateTime.now().plusDays(2), "Jornada 1");
        match.setId(10L);

        responseDTO = new MatchResponseDTO();
        responseDTO.setId(10L);
        responseDTO.setState("SCHEDULED");
    }

    // ── createMatch ────────────────────────────────────────────────────────────

    @Test
    @DisplayName("createMatch: crea partido exitosamente sin resultado")
    void createMatch_success_noResult() {
        MatchRequestDTO request = buildRequest(null, null);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(teamRepository.findById(1L)).thenReturn(Optional.of(homeTeam));
        when(teamRepository.findById(2L)).thenReturn(Optional.of(awayTeam));
        when(matchRepository.save(any())).thenReturn(match);
        when(matchMapper.toDto(match)).thenReturn(responseDTO);

        MatchResponseDTO result = matchService.createMatch(request);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(10L);
        verify(matchRepository).save(any(Match.class));
    }

    @Test
    @DisplayName("createMatch: crea partido con resultado inmediato")
    void createMatch_success_withResult() {
        MatchRequestDTO request = buildRequest(2, 1);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(teamRepository.findById(1L)).thenReturn(Optional.of(homeTeam));
        when(teamRepository.findById(2L)).thenReturn(Optional.of(awayTeam));
        when(matchRepository.save(any())).thenReturn(match);
        when(matchMapper.toDto(match)).thenReturn(responseDTO);

        MatchResponseDTO result = matchService.createMatch(request);

        assertThat(result).isNotNull();
        verify(matchRepository).save(any(Match.class));
    }

    @Test
    @DisplayName("createMatch: lanza excepción si torneo no existe")
    void createMatch_tournamentNotFound() {
        MatchRequestDTO request = buildRequest(null, null);
        when(tournamentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> matchService.createMatch(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Torneo no encontrado");
    }

    @Test
    @DisplayName("createMatch: lanza excepción si equipo local no existe")
    void createMatch_homeTeamNotFound() {
        MatchRequestDTO request = buildRequest(null, null);
        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(teamRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> matchService.createMatch(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Equipo local no encontrado");
    }

    @Test
    @DisplayName("createMatch: lanza excepción si equipo visitante no existe")
    void createMatch_awayTeamNotFound() {
        MatchRequestDTO request = buildRequest(null, null);
        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(teamRepository.findById(1L)).thenReturn(Optional.of(homeTeam));
        when(teamRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> matchService.createMatch(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Equipo visitante no encontrado");
    }

    @Test
    @DisplayName("createMatch: lanza excepción si local y visitante son el mismo equipo")
    void createMatch_sameTeams() {
        MatchRequestDTO request = buildRequest(null, null);
        request.setAwayTeamId(1L); // mismo que home

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(teamRepository.findById(1L)).thenReturn(Optional.of(homeTeam));

        assertThatThrownBy(() -> matchService.createMatch(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("no pueden ser el mismo");
    }

    @Test
    @DisplayName("createMatch: registra goleador cuando se proveen datos")
    void createMatch_withTopScorer() {
        MatchRequestDTO request = buildRequest(3, 1);
        request.setTopScorerName("Juan García");
        request.setTopScorerTeamId(1L);
        request.setTopScorerGoals(3);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(teamRepository.findById(1L)).thenReturn(Optional.of(homeTeam));
        when(teamRepository.findById(2L)).thenReturn(Optional.of(awayTeam));
        // for topScorerTeamId lookup
        when(matchRepository.save(any())).thenReturn(match);
        when(matchMapper.toDto(match)).thenReturn(responseDTO);

        MatchResponseDTO result = matchService.createMatch(request);
        assertThat(result).isNotNull();
    }

    // ── getMatchById ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("getMatchById: retorna DTO cuando el partido existe")
    void getMatchById_found() {
        when(matchRepository.findById(10L)).thenReturn(Optional.of(match));
        when(matchMapper.toDto(match)).thenReturn(responseDTO);

        MatchResponseDTO result = matchService.getMatchById(10L);
        assertThat(result.getId()).isEqualTo(10L);
    }

    @Test
    @DisplayName("getMatchById: lanza excepción cuando no existe")
    void getMatchById_notFound() {
        when(matchRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> matchService.getMatchById(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Partido no encontrado");
    }

    // ── getMatchesByTournament ────────────────────────────────────────────────

    @Test
    @DisplayName("getMatchesByTournament: retorna lista de partidos")
    void getMatchesByTournament_success() {
        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(matchRepository.findByTournament(tournament)).thenReturn(List.of(match));
        when(matchMapper.toDto(match)).thenReturn(responseDTO);

        List<MatchResponseDTO> result = matchService.getMatchesByTournament(1L);
        assertThat(result).hasSize(1);
    }

    @Test
    @DisplayName("getMatchesByTournament: lanza excepción si torneo no existe")
    void getMatchesByTournament_tournamentNotFound() {
        when(tournamentRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> matchService.getMatchesByTournament(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Torneo no encontrado");
    }

    // ── getMatchesByTeam ──────────────────────────────────────────────────────

    @Test
    @DisplayName("getMatchesByTeam: retorna partidos del equipo")
    void getMatchesByTeam_success() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(homeTeam));
        when(matchRepository.findByHomeTeamOrAwayTeam(homeTeam, homeTeam)).thenReturn(List.of(match));
        when(matchMapper.toDto(match)).thenReturn(responseDTO);

        List<MatchResponseDTO> result = matchService.getMatchesByTeam(1L);
        assertThat(result).hasSize(1);
    }

    @Test
    @DisplayName("getMatchesByTeam: lanza excepción si equipo no existe")
    void getMatchesByTeam_teamNotFound() {
        when(teamRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> matchService.getMatchesByTeam(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Equipo no encontrado");
    }

    // ── registerResult ────────────────────────────────────────────────────────

    @Test
    @DisplayName("registerResult: registra resultado correctamente")
    void registerResult_success() {
        MatchRequestDTO request = buildRequest(2, 0);

        when(matchRepository.findById(10L)).thenReturn(Optional.of(match));
        when(matchRepository.save(any())).thenReturn(match);
        when(matchMapper.toDto(match)).thenReturn(responseDTO);

        MatchResponseDTO result = matchService.registerResult(10L, request);
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("registerResult: lanza excepción si partido no existe")
    void registerResult_matchNotFound() {
        MatchRequestDTO request = buildRequest(1, 0);
        when(matchRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> matchService.registerResult(99L, request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Partido no encontrado");
    }

    @Test
    @DisplayName("registerResult: lanza excepción si no se proveen goles")
    void registerResult_noGoalsProvided() {
        MatchRequestDTO request = buildRequest(null, null);

        when(matchRepository.findById(10L)).thenReturn(Optional.of(match));

        assertThatThrownBy(() -> matchService.registerResult(10L, request))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("goles de ambos equipos");
    }

    // ── deleteMatch ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("deleteMatch: elimina partido existente")
    void deleteMatch_success() {
        when(matchRepository.existsById(10L)).thenReturn(true);
        doNothing().when(matchRepository).deleteById(10L);

        assertThatCode(() -> matchService.deleteMatch(10L)).doesNotThrowAnyException();
        verify(matchRepository).deleteById(10L);
    }

    @Test
    @DisplayName("deleteMatch: lanza excepción si partido no existe")
    void deleteMatch_notFound() {
        when(matchRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> matchService.deleteMatch(99L))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Partido no encontrado");
    }

    // ── helpers ───────────────────────────────────────────────────────────────

    private MatchRequestDTO buildRequest(Integer homeGoals, Integer awayGoals) {
        MatchRequestDTO req = new MatchRequestDTO();
        req.setTournamentId(1L);
        req.setHomeTeamId(1L);
        req.setAwayTeamId(2L);
        req.setMatchDate(LocalDateTime.now().plusDays(2));
        req.setRound("Jornada 1");
        req.setHomeTeamGoals(homeGoals);
        req.setAwayTeamGoals(awayGoals);
        return req;
    }
}
