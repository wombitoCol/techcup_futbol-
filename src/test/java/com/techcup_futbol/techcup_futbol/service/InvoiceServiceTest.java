package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.dto.Request.InvoiceRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.InvoiceResponseDTO;
import com.techcup_futbol.techcup_futbol.exception.ResourceNotFoundException;
import com.techcup_futbol.techcup_futbol.mappers.InvoiceMapper;
import com.techcup_futbol.techcup_futbol.model.Payment.Invoice;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;
import com.techcup_futbol.techcup_futbol.repository.InvoiceRepository;
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
class InvoiceServiceTest {

    @Mock private InvoiceRepository invoiceRepository;
    @Mock private InvoiceMapper invoiceMapper;

    @InjectMocks private InvoiceService invoiceService;

    private Invoice invoice;
    private InvoiceRequestDTO requestDTO;
    private InvoiceResponseDTO responseDTO;
    private Tournament tournament;
    private Team team;

    @BeforeEach
    void setUp() {
        tournament = new Tournament("Copa", LocalDateTime.now(), null,
                LocalDateTime.now().plusDays(10), 100.0, "desc", "robin", TournamentState.ACTIVE);
        tournament.setId(1L);

        team = new Team(1L, "Equipo A");

        requestDTO = InvoiceRequestDTO.builder()
                .amount(150.0)
                .description("Inscripción torneo")
                .tournament(tournament)
                .team(team)
                .build();

        invoice = new Invoice();
        invoice.setId(1L);
        invoice.setAmount(150.0);
        invoice.setDescription("Inscripción torneo");
        invoice.setCreatedAt(LocalDateTime.now());

        responseDTO = new InvoiceResponseDTO(1L, 150.0, "Inscripción torneo",
                LocalDateTime.now(), "Equipo A");
    }

    // ── createInvoice ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("createInvoice: crea factura correctamente")
    void createInvoice_success() {
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        when(invoiceMapper.toDto(invoice)).thenReturn(responseDTO);

        InvoiceResponseDTO result = invoiceService.createInvoice(requestDTO);

        assertThat(result).isNotNull();
        assertThat(result.getAmount()).isEqualTo(150.0);
        assertThat(result.getDescription()).isEqualTo("Inscripción torneo");
        verify(invoiceRepository).save(any(Invoice.class));
    }

    @Test
    @DisplayName("createInvoice: asigna la fecha de creación automáticamente")
    void createInvoice_setsCreatedAt() {
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        when(invoiceMapper.toDto(invoice)).thenReturn(responseDTO);

        invoiceService.createInvoice(requestDTO);

        verify(invoiceRepository).save(argThat(inv -> inv.getCreatedAt() != null));
    }

    // ── updateInvoice ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("updateInvoice: actualiza factura existente")
    void updateInvoice_success() {
        InvoiceRequestDTO updateDTO = InvoiceRequestDTO.builder()
                .amount(200.0)
                .description("Actualizado")
                .tournament(tournament)
                .team(team)
                .build();

        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));
        when(invoiceRepository.save(any())).thenReturn(invoice);
        when(invoiceMapper.toDto(invoice)).thenReturn(responseDTO);

        InvoiceResponseDTO result = invoiceService.updateInvoice(1L, updateDTO);

        assertThat(result).isNotNull();
        verify(invoiceRepository).save(invoice);
    }

    @Test
    @DisplayName("updateInvoice: lanza excepción si factura no existe")
    void updateInvoice_notFound() {
        when(invoiceRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> invoiceService.updateInvoice(99L, requestDTO))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    // ── deleteInvoice ─────────────────────────────────────────────────────────

    @Test
    @DisplayName("deleteInvoice: elimina factura existente")
    void deleteInvoice_success() {
        when(invoiceRepository.existsById(1L)).thenReturn(true);
        doNothing().when(invoiceRepository).deleteById(1L);

        assertThatCode(() -> invoiceService.deleteInvoice(1L)).doesNotThrowAnyException();
        verify(invoiceRepository).deleteById(1L);
    }

    @Test
    @DisplayName("deleteInvoice: lanza excepción si factura no existe")
    void deleteInvoice_notFound() {
        when(invoiceRepository.existsById(99L)).thenReturn(false);

        assertThatThrownBy(() -> invoiceService.deleteInvoice(99L))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}
