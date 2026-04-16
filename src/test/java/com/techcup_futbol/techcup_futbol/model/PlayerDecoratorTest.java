package com.techcup_futbol.techcup_futbol.model;

import com.techcup_futbol.techcup_futbol.model.PlayerDecorator.CaptainDecorator;
import com.techcup_futbol.techcup_futbol.model.PlayerDecorator.PlayerComponent;
import com.techcup_futbol.techcup_futbol.model.PlayerDecorator.PlayerDecorator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PlayerDecoratorTest {

    /**
     * Implementación mínima de PlayerComponent para usarla en tests sin
     * necesitar la clase Player (que está comentada en el proyecto).
     */
    static class SimplePlayer implements PlayerComponent {
        private final String name;

        SimplePlayer(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<String> getActions() {
            return List.of("shoot", "pass");
        }

        @Override
        public boolean canPerformAction(String action) {
            return "shoot".equals(action) || "pass".equals(action);
        }
    }

    private SimplePlayer basePlayer;
    private CaptainDecorator captain;

    @BeforeEach
    void setUp() {
        basePlayer = new SimplePlayer("Carlos Pérez");
        captain = new CaptainDecorator(basePlayer);
    }

    // ── PlayerDecorator delegation ────────────────────────────────────────────

    @Test
    @DisplayName("CaptainDecorator: getName() delega al jugador base")
    void getName_delegatesToPlayer() {
        assertThat(captain.getName()).isEqualTo("Carlos Pérez");
    }

    @Test
    @DisplayName("CaptainDecorator: canPerformAction() delega al jugador base")
    void canPerformAction_delegatesToPlayer() {
        assertThat(captain.canPerformAction("shoot")).isTrue();
        assertThat(captain.canPerformAction("tackle")).isFalse();
    }

    @Test
    @DisplayName("CaptainDecorator: getActions() retorna null (implementación pendiente)")
    void getActions_returnsNull() {
        // La implementación actual retorna null (TODO del equipo)
        assertThat(captain.getActions()).isNull();
    }

    @Test
    @DisplayName("CaptainDecorator: createTeam() no lanza excepción")
    void createTeam_doesNotThrow() {
        assertThatCode(() -> captain.createTeam("torneo-1", new Object()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("CaptainDecorator: defineLineup() no lanza excepción")
    void defineLineup_doesNotThrow() {
        assertThatCode(() -> captain.defineLineup("match-1", new Object()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("CaptainDecorator: es instancia de PlayerDecorator y PlayerComponent")
    void captainDecorator_typeHierarchy() {
        assertThat(captain).isInstanceOf(PlayerDecorator.class);
        assertThat(captain).isInstanceOf(PlayerComponent.class);
    }

    // ── Wrapping múltiple (double-decorator) ──────────────────────────────────

    @Test
    @DisplayName("CaptainDecorator puede envolver otro CaptainDecorator")
    void doubleDecorator_works() {
        CaptainDecorator doubleCaptain = new CaptainDecorator(captain);
        assertThat(doubleCaptain.getName()).isEqualTo("Carlos Pérez");
        assertThat(doubleCaptain.canPerformAction("pass")).isTrue();
    }
}
