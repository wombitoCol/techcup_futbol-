package com.techcup_futbol.techcup_futbol.controllers;

import com.techcup_futbol.techcup_futbol.dto.Request.InvoiceRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Request.TeamRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.InvoiceResponseDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TeamResponseDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;
import com.techcup_futbol.techcup_futbol.service.InvoiceService;
import com.techcup_futbol.techcup_futbol.service.TeamService;
import com.techcup_futbol.techcup_futbol.service.TournamentService;
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

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

// ─── TournamentController ─────────────────────────────────────────────────────
@ExtendWith(MockitoExtension.class)
class TournamentControllerTest {

    @Mock TournamentService tournamentService;
    @InjectMocks TournamentController tournamentController;

    TournamentRequestDTO requestDTO;
    TournamentResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = TournamentRequestDTO.builder()
                .name("Copa Techcup").startDate(LocalDateTime.now().plusDays(10))
                .finishDateToRegister(LocalDateTime.now().plusDays(5))
                .costPerTeam(100.0).description("Test").type("robin")
                .state(TournamentState.DRAFT).build();
        responseDTO = TournamentResponseDTO.builder()
                .id(1L).name("Copa Techcup").state(TournamentState.DRAFT).build();
    }

    @Test @DisplayName("create: retorna 201 CREATED")
    void create_returns201() {
        when(tournamentService.createTournament(any())).thenReturn(responseDTO);
        ResponseEntity<TournamentResponseDTO> resp = tournamentController.create(requestDTO);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resp.getBody().getName()).isEqualTo("Copa Techcup");
    }

    @Test @DisplayName("update: retorna 200 OK")
    void update_returns200() {
        when(tournamentService.updateTournament(eq(1L), any())).thenReturn(responseDTO);
        ResponseEntity<TournamentResponseDTO> resp = tournamentController.update(1L, requestDTO);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test @DisplayName("delete: retorna 204 NO CONTENT")
    void delete_returns204() {
        doNothing().when(tournamentService).deleteTournament(1L);
        ResponseEntity<Void> resp = tournamentController.delete(1L);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(tournamentService).deleteTournament(1L);
    }
}

// ─── TeamController ───────────────────────────────────────────────────────────
@ExtendWith(MockitoExtension.class)
class TeamControllerTest {

    @Mock TeamService teamService;
    @InjectMocks TeamController teamController;

    @Test @DisplayName("createTeam: retorna 201 CREATED")
    void createTeam_returns201() {
        TeamRequestDTO req = new TeamRequestDTO();
        req.setName("Los Tigres");
        TeamResponseDTO resp = TeamResponseDTO.builder().id(1L).name("Los Tigres").build();
        when(teamService.createTeam(any())).thenReturn(resp);

        ResponseEntity<TeamResponseDTO> result = teamController.createTeam(req);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getName()).isEqualTo("Los Tigres");
    }

    @Test @DisplayName("getTeamById: retorna 200 OK")
    void getTeamById_returns200() {
        TeamResponseDTO resp = TeamResponseDTO.builder().id(1L).name("Los Tigres").build();
        when(teamService.getTeamById(1L)).thenReturn(resp);

        ResponseEntity<TeamResponseDTO> result = teamController.getTeamById(1L);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getId()).isEqualTo(1L);
    }
}

// ─── InvoiceController ────────────────────────────────────────────────────────
@ExtendWith(MockitoExtension.class)
class InvoiceControllerTest {

    @Mock InvoiceService invoiceService;
    @InjectMocks InvoiceController invoiceController;

    InvoiceRequestDTO requestDTO;
    InvoiceResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = InvoiceRequestDTO.builder()
                .amount(150.0).description("Inscripción").build();
        responseDTO = new InvoiceResponseDTO(1L, 150.0, "Inscripción", LocalDateTime.now(), "Equipo A");
    }

    @Test @DisplayName("createInvoice: retorna 201 CREATED")
    void createInvoice_returns201() {
        when(invoiceService.createInvoice(any())).thenReturn(responseDTO);
        ResponseEntity<InvoiceResponseDTO> resp = invoiceController.createInvoice(requestDTO);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(resp.getBody().getAmount()).isEqualTo(150.0);
    }

    @Test @DisplayName("updateInvoice: retorna 200 OK")
    void updateInvoice_returns200() {
        when(invoiceService.updateInvoice(eq(1L), any())).thenReturn(responseDTO);
        ResponseEntity<InvoiceResponseDTO> resp = invoiceController.updateInvoice(1L, requestDTO);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test @DisplayName("deleteInvoice: retorna 204 NO CONTENT")
    void deleteInvoice_returns204() {
        doNothing().when(invoiceService).deleteInvoice(1L);
        ResponseEntity<Void> resp = invoiceController.deleteInvoice(1L);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(invoiceService).deleteInvoice(1L);
    }
}
