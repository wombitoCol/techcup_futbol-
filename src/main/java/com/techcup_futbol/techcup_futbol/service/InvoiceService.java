package com.techcup_futbol.techcup_futbol.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.techcup_futbol.techcup_futbol.dto.Request.InvoiceRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.InvoiceResponseDTO;
import com.techcup_futbol.techcup_futbol.exception.ResourceNotFoundException;
import com.techcup_futbol.techcup_futbol.mappers.InvoiceMapper;
import com.techcup_futbol.techcup_futbol.model.Payment.Invoice;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.repository.InvoiceRepository;
import com.techcup_futbol.techcup_futbol.repository.TournamentRepository;
import com.techcup_futbol.techcup_futbol.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;


    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO request) {


        Invoice invoice = new Invoice();
        invoice.setAmount(request.getAmount());
        invoice.setDescription(request.getDescription());
        invoice.setCreatedAt(LocalDateTime.now());
        invoice.setTeam(request.getTeam());
        invoice.setTournament(request.getTournament());

        Invoice savedInvoice = invoiceRepository.save(invoice);

        return invoiceMapper.toDto(savedInvoice);
    }

    public InvoiceResponseDTO updateInvoice(Long id, InvoiceRequestDTO dto) {

        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> ResourceNotFoundException.create("Invoice ID", id));


        invoice.setAmount(dto.getAmount());
        invoice.setDescription(dto.getDescription());
        invoice.setTeam(dto.getTeam());
        invoice.setTournament(dto.getTournament());

        Invoice updated = invoiceRepository.save(invoice);
        return invoiceMapper.toDto(updated);
    }

    public void deleteInvoice(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw ResourceNotFoundException.create("Invoice ID", id);
        }
        invoiceRepository.deleteById(id);
    }
}