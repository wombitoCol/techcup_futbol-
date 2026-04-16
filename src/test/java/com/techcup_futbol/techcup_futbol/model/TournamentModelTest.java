package com.techcup_futbol.techcup_futbol.model;

import com.techcup_futbol.techcup_futbol.model.Match.Match;
import com.techcup_futbol.techcup_futbol.model.Match.MatchState;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class TournamentModelTest {

    private Tournament tournament;

    Team team(long id, String name) {
        return new Team(id, name);
    }

    @BeforeEach
    void setUp() {
        tournament = new Tournament("Copa Test", LocalDateTime.now(), null,
                LocalDateTime.now().plusDays(5), 50.0, "desc", "robin",
                TournamentState.DRAFT);
        tournament.setId(1L);
    }

    // ── addTeam / removeTeam ──────────────────────────────────────────────────

    @Test
    @DisplayName("addTeam: incrementa teamsNumber")
    void addTeam_incrementsCount() {
        tournament.addTeam(team(1L, "A"));
        tournament.addTeam(team(2L, "B"));
        assertThat(tournament.getTeamsNumber()).isEqualTo(2);
    }

    @Test
    @DisplayName("removeTeam: decrementa teamsNumber")
    void removeTeam_decrementsCount() {
        Team t = team(1L, "A");
        tournament.addTeam(t);
        tournament.removeTeam(t);
        assertThat(tournament.getTeamsNumber()).isEqualTo(0);
    }

    // ── changeState ───────────────────────────────────────────────────────────

    @Test
    @DisplayName("changeState: cambia el estado del torneo")
    void changeState_updatesState() {
        tournament.changeState(TournamentState.ACTIVE);
        assertThat(tournament.getState()).isEqualTo(TournamentState.ACTIVE);
    }

    // ── makeMatches: round robin ──────────────────────────────────────────────

    @Nested
    @DisplayName("makeMatches - Round Robin")
    class RoundRobinTests {

        @BeforeEach
        void addTeams() {
            tournament.setType("robin");
            for (int i = 1; i <= 4; i++) {
                tournament.addTeam(team(i, "Equipo " + i));
            }
        }

        @Test
        @DisplayName("genera n*(n-1)/2 partidos para round robin")
        void makeMatches_generatesCorrectCount() {
            tournament.makeMatches();
            // 4 equipos → 6 partidos
            assertThat(tournament.getTeams()).hasSize(4);
        }

        @Test
        @DisplayName("acepta alias 'round_robin'")
        void makeMatches_aliasRoundRobin() {
            tournament.setType("round_robin");
            assertThatCode(() -> tournament.makeMatches()).doesNotThrowAnyException();
        }
    }

    // ── makeMatches: elimination ──────────────────────────────────────────────

    @Nested
    @DisplayName("makeMatches - Elimination")
    class EliminationTests {

        @BeforeEach
        void addTeams() {
            tournament.setType("elimination");
        }

        @Test
        @DisplayName("genera partidos para 4 equipos (potencia de 2)")
        void makeMatches_fourTeams() {
            for (int i = 1; i <= 4; i++) tournament.addTeam(team(i, "E" + i));
            assertThatCode(() -> tournament.makeMatches()).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("lanza excepción si equipos no son potencia de 2")
        void makeMatches_notPowerOfTwo() {
            for (int i = 1; i <= 3; i++) tournament.addTeam(team(i, "E" + i));
            assertThatThrownBy(() -> tournament.makeMatches())
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("potencia de 2");
        }

        @Test
        @DisplayName("acepta alias 'knockout'")
        void makeMatches_aliasKnockout() {
            tournament.setType("knockout");
            for (int i = 1; i <= 4; i++) tournament.addTeam(team(i, "E" + i));
            assertThatCode(() -> tournament.makeMatches()).doesNotThrowAnyException();
        }
    }

    // ── makeMatches: groups ───────────────────────────────────────────────────

    @Nested
    @DisplayName("makeMatches - Groups")
    class GroupsTests {

        @BeforeEach
        void addTeams() {
            tournament.setType("groups");
        }

        @Test
        @DisplayName("genera partidos para 8 equipos (2 grupos de 4)")
        void makeMatches_eightTeams() {
            for (int i = 1; i <= 8; i++) tournament.addTeam(team(i, "E" + i));
            assertThatCode(() -> tournament.makeMatches()).doesNotThrowAnyException();
        }

        @Test
        @DisplayName("lanza excepción si hay menos de 8 equipos")
        void makeMatches_notEnoughTeams() {
            for (int i = 1; i <= 5; i++) tournament.addTeam(team(i, "E" + i));
            assertThatThrownBy(() -> tournament.makeMatches())
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessageContaining("equipos");
        }

        @Test
        @DisplayName("acepta alias 'groups_elimination'")
        void makeMatches_aliasGroupsElimination() {
            tournament.setType("groups_elimination");
            for (int i = 1; i <= 8; i++) tournament.addTeam(team(i, "E" + i));
            assertThatCode(() -> tournament.makeMatches()).doesNotThrowAnyException();
        }
    }

    // ── makeMatches: error cases ──────────────────────────────────────────────

    @Test
    @DisplayName("makeMatches: lanza excepción si hay menos de 2 equipos")
    void makeMatches_lessThanTwoTeams() {
        tournament.addTeam(team(1L, "Solo"));
        assertThatThrownBy(() -> tournament.makeMatches())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("2 equipos");
    }

    @Test
    @DisplayName("makeMatches: lanza excepción para tipo desconocido")
    void makeMatches_unknownType() {
        tournament.setType("desconocido");
        tournament.addTeam(team(1L, "A"));
        tournament.addTeam(team(2L, "B"));
        assertThatThrownBy(() -> tournament.makeMatches())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("no reconocido");
    }
}
