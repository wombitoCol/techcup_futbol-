package com.techcup_futbol.techcup_futbol.model;

import com.techcup_futbol.techcup_futbol.exception.ResourceNotFoundException;
import com.techcup_futbol.techcup_futbol.model.Notification.Observer;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

class MiscModelTest {

    // ── ResourceNotFoundException ─────────────────────────────────────────────

    @Test
    @DisplayName("ResourceNotFoundException: constructor con mensaje")
    void resourceNotFoundException_message() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Something not found");
        assertThat(ex.getMessage()).isEqualTo("Something not found");
    }

    @Test
    @DisplayName("ResourceNotFoundException.create: genera mensaje correcto")
    void resourceNotFoundException_create() {
        ResourceNotFoundException ex = ResourceNotFoundException.create("User", 42L);
        assertThat(ex.getMessage()).contains("User").contains("42");
    }

    @Test
    @DisplayName("ResourceNotFoundException: extiende RuntimeException")
    void resourceNotFoundException_isRuntimeException() {
        assertThat(new ResourceNotFoundException("test"))
                .isInstanceOf(RuntimeException.class);
    }

    // ── Team Observer pattern ─────────────────────────────────────────────────

    @Test
    @DisplayName("Team: subscribe agrega observador")
    void team_subscribe() {
        Team team = new Team(1L, "Tigres");
        Observer obs = mock(Observer.class);
        team.subscribe(obs);
        assertThat(team.getSubscribers()).contains(obs);
    }

    @Test
    @DisplayName("Team: unsubscribe elimina observador")
    void team_unsubscribe() {
        Team team = new Team(1L, "Tigres");
        Observer obs = mock(Observer.class);
        team.subscribe(obs);
        team.unsubscribe(obs);
        assertThat(team.getSubscribers()).doesNotContain(obs);
    }

    @Test
    @DisplayName("Team: notifyObservers llama update() en todos los suscriptores")
    void team_notifyObservers() {
        Team team = new Team(1L, "Tigres");
        Observer obs1 = mock(Observer.class);
        Observer obs2 = mock(Observer.class);
        team.subscribe(obs1);
        team.subscribe(obs2);
        team.notifyObservers();
        verify(obs1).update();
        verify(obs2).update();
    }

    @Test
    @DisplayName("Team.update: propaga notificación a sus propios suscriptores")
    void team_update_propagates() {
        Team team = new Team(1L, "Tigres");
        Observer obs = mock(Observer.class);
        team.subscribe(obs);
        team.update();
        verify(obs).update();
    }

    @Test
    @DisplayName("Team: equals con mismo ID")
    void team_equals_sameId() {
        Team t1 = new Team(1L, "A");
        Team t2 = new Team(1L, "B");
        assertThat(t1).isEqualTo(t2);
    }

    @Test
    @DisplayName("Team: equals con ID null")
    void team_equals_nullId() {
        Team t1 = new Team();
        Team t2 = new Team();
        assertThat(t1).isNotEqualTo(t2);
    }
}
