package com.techcup_futbol.techcup_futbol.model;

import com.techcup_futbol.techcup_futbol.dto.Response.MatchResponseDTO;
import com.techcup_futbol.techcup_futbol.mappers.MatchMapper;
import com.techcup_futbol.techcup_futbol.model.Match.Match;
import com.techcup_futbol.techcup_futbol.model.Match.MatchState;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class MatchModelTest {

    private Tournament tournament;
    private Team homeTeam;
    private Team awayTeam;
    private Match match;

    @BeforeEach
    void setUp() {
        tournament = new Tournament("T1", LocalDateTime.now(), null,
                LocalDateTime.now().plusDays(10), 50.0, "desc", "robin", TournamentState.ACTIVE);
        tournament.setId(1L);

        homeTeam = new Team(1L, "Local FC");
        awayTeam = new Team(2L, "Visitante FC");

        match = new Match(tournament, homeTeam, awayTeam, LocalDateTime.now().plusDays(1), "Jornada 1");
        match.setId(10L);
    }

    // ── registerResult ────────────────────────────────────────────────────────

    @Test
    @DisplayName("registerResult: local gana → winner = homeTeam")
    void registerResult_homeWins() {
        match.registerResult(3, 1);
        assertThat(match.getWinner()).isEqualTo(homeTeam);
        assertThat(match.getState()).isEqualTo(MatchState.FINISHED);
        assertThat(match.isDraw()).isFalse();
    }

    @Test
    @DisplayName("registerResult: visitante gana → winner = awayTeam")
    void registerResult_awayWins() {
        match.registerResult(0, 2);
        assertThat(match.getWinner()).isEqualTo(awayTeam);
        assertThat(match.isDraw()).isFalse();
    }

    @Test
    @DisplayName("registerResult: empate → winner = null, isDraw = true")
    void registerResult_draw() {
        match.registerResult(1, 1);
        assertThat(match.getWinner()).isNull();
        assertThat(match.isDraw()).isTrue();
    }

    // ── isDraw ────────────────────────────────────────────────────────────────

    @Test
    @DisplayName("isDraw: retorna false si partido no ha terminado")
    void isDraw_scheduledMatch_returnsFalse() {
        assertThat(match.isDraw()).isFalse();
    }

    // ── involvesTeam ──────────────────────────────────────────────────────────

    @Test
    @DisplayName("involvesTeam: true para equipo local")
    void involvesTeam_homeTeam() {
        assertThat(match.involvesTeam(homeTeam)).isTrue();
    }

    @Test
    @DisplayName("involvesTeam: true para equipo visitante")
    void involvesTeam_awayTeam() {
        assertThat(match.involvesTeam(awayTeam)).isTrue();
    }

    @Test
    @DisplayName("involvesTeam: false para equipo que no participa")
    void involvesTeam_otherTeam() {
        Team other = new Team(99L, "Otro FC");
        assertThat(match.involvesTeam(other)).isFalse();
    }

    // ── equals / hashCode ─────────────────────────────────────────────────────

    @Test
    @DisplayName("equals: dos Match con mismo ID son iguales")
    void equals_sameId() {
        Match other = new Match(tournament, homeTeam, awayTeam,
                LocalDateTime.now(), "J1");
        other.setId(10L);
        assertThat(match).isEqualTo(other);
    }

    @Test
    @DisplayName("equals: Match con ID null no es igual a otro")
    void equals_nullId() {
        Match noId = new Match(tournament, homeTeam, awayTeam, LocalDateTime.now(), "J1");
        assertThat(match).isNotEqualTo(noId);
    }

    @Test
    @DisplayName("estado inicial es SCHEDULED")
    void initialState_isScheduled() {
        assertThat(match.getState()).isEqualTo(MatchState.SCHEDULED);
    }

    // ── MatchMapper ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("MatchMapper.toDto: retorna null para match null")
    void matchMapper_null() {
        MatchMapper mapper = new MatchMapper();
        assertThat(mapper.toDto(null)).isNull();
    }

    @Test
    @DisplayName("MatchMapper.toDto: mapea campos correctamente")
    void matchMapper_mapsFields() {
        match.registerResult(2, 1);
        MatchMapper mapper = new MatchMapper();
        MatchResponseDTO dto = mapper.toDto(match);

        assertThat(dto.getId()).isEqualTo(10L);
        assertThat(dto.getHomeTeamGoals()).isEqualTo(2);
        assertThat(dto.getAwayTeamGoals()).isEqualTo(1);
        assertThat(dto.getState()).isEqualTo("FINISHED");
        assertThat(dto.isDraw()).isFalse();
        assertThat(dto.getTournamentId()).isEqualTo(1L);
        assertThat(dto.getHomeTeamName()).isEqualTo("Local FC");
        assertThat(dto.getAwayTeamName()).isEqualTo("Visitante FC");
        assertThat(dto.getWinnerName()).isEqualTo("Local FC");
    }

    @Test
    @DisplayName("MatchMapper.toDto: mapea empate (sin ganador)")
    void matchMapper_drawNoWinner() {
        match.registerResult(0, 0);
        MatchMapper mapper = new MatchMapper();
        MatchResponseDTO dto = mapper.toDto(match);

        assertThat(dto.getWinnerId()).isNull();
        assertThat(dto.isDraw()).isTrue();
    }
}
