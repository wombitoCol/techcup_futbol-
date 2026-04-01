package com.techcup_futbol.techcup_futbol.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.techcup_futbol.techcup_futbol.model.Payment.Invoice;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.dto.Request.InvoiceRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.InvoiceResponseDTO;
import com.techcup_futbol.techcup_futbol.mappers.InvoiceMapper;
import com.techcup_futbol.techcup_futbol.repository.InvoiceRepository;
import com.techcup_futbol.techcup_futbol.repository.UserRepository;

@Service
public class InvoiceService {
    
    @Autowired 
    private InvoiceRepository invoiceRepository;
    
    @Autowired 
    private UserRepository userRepository;
    
    @Autowired 
    private InvoiceMapper invoiceMapper;

    public InvoiceResponseDTO createInvoice(InvoiceRequestDTO dto) {
        // 1. Buscamos al usuario por el ID que viene en el DTO
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // 2. Usamos el mapper para convertir DTO a Entidad
        Invoice invoice = invoiceMapper.toEntity(dto, user);

        // 3. Guardamos en la base de datos
        Invoice savedInvoice = invoiceRepository.save(invoice);

        // 4. Devolvemos el ResponseDTO
        return invoiceMapper.toDto(savedInvoice);
    }
}