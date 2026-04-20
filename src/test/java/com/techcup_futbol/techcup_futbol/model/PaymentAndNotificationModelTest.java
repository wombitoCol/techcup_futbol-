package com.techcup_futbol.techcup_futbol.model;

import com.techcup_futbol.techcup_futbol.model.Notification.Notification;
import com.techcup_futbol.techcup_futbol.model.Payment.Invoice;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.model.User.UserType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class PaymentAndNotificationModelTest {

    // ─── Invoice ──────────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Invoice model")
    class InvoiceTests {

        Invoice invoice;
        Tournament tournament;
        Team team;
        User user;

        @BeforeEach
        void setUp() {
            tournament = new Tournament("Copa", LocalDateTime.now(), null,
                    LocalDateTime.now().plusDays(10), 100.0, "desc", "robin",
                    TournamentState.ACTIVE);
            tournament.setId(1L);

            team = new Team(1L, "Tigres");

            user = User.builder()
                    .id(1L).email("u@test.edu").name("Test").password("p")
                    .type(UserType.STUDENT).birthDate(LocalDate.of(2000, 1, 1))
                    .gender("M").phoneNumber(300L).build();

            invoice = new Invoice();
            invoice.setId(10L);
            invoice.setAmount(200.0);
            invoice.setDescription("Inscripción");
            invoice.setCreatedAt(LocalDateTime.of(2025, 4, 1, 10, 0));
            invoice.setUser(user);
            invoice.setTournament(tournament);
            invoice.setTeam(team);
        }

        @Test @DisplayName("getters retornan valores seteados")
        void getters_returnSetValues() {
            assertThat(invoice.getId()).isEqualTo(10L);
            assertThat(invoice.getAmount()).isEqualTo(200.0);
            assertThat(invoice.getDescription()).isEqualTo("Inscripción");
            assertThat(invoice.getCreatedAt()).isEqualTo(LocalDateTime.of(2025, 4, 1, 10, 0));
            assertThat(invoice.getUser().getEmail()).isEqualTo("u@test.edu");
            assertThat(invoice.getTeam().getName()).isEqualTo("Tigres");
        }

        @Test @DisplayName("getTournamentId retorna ID del torneo")
        void getTournamentId_returnsTournamentId() {
            assertThat(invoice.getTournamentId()).isEqualTo(1L);
        }

        @Test @DisplayName("getTournamentId retorna null cuando tournament es null")
        void getTournamentId_nullTournament() {
            invoice.setTournament(null);
            assertThat(invoice.getTournamentId()).isNull();
        }

        @Test @DisplayName("constructor vacío funciona")
        void emptyConstructor_works() {
            Invoice empty = new Invoice();
            assertThat(empty.getId()).isNull();
            assertThat(empty.getAmount()).isNull();
        }
    }

    // ─── Notification ─────────────────────────────────────────────────────────

    @Nested
    @DisplayName("Notification model")
    class NotificationTests {

        Notification notification;

        @BeforeEach
        void setUp() {
            notification = new Notification("N-001", "Partido programado",
                    LocalDateTime.of(2025, 5, 15, 9, 30));
        }

        @Test @DisplayName("constructor asigna campos correctamente")
        void constructor_setsFields() {
            assertThat(notification.getId()).isEqualTo("N-001");
            assertThat(notification.getMessage()).isEqualTo("Partido programado");
            assertThat(notification.getDate()).isEqualTo(LocalDateTime.of(2025, 5, 15, 9, 30));
        }

        @Test @DisplayName("setters actualizan campos")
        void setters_updateFields() {
            notification.setId("N-002");
            notification.setMessage("Resultado registrado");
            notification.setDate(LocalDateTime.of(2025, 6, 1, 14, 0));

            assertThat(notification.getId()).isEqualTo("N-002");
            assertThat(notification.getMessage()).isEqualTo("Resultado registrado");
            assertThat(notification.getDate()).isEqualTo(LocalDateTime.of(2025, 6, 1, 14, 0));
        }
    }
}
