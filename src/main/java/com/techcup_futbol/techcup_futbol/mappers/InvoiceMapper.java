package com.techcup_futbol.techcup_futbol.mappers;

import org.springframework.stereotype.Component;
import com.techcup_futbol.techcup_futbol.dto.Request.InvoiceRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.InvoiceResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Invoice.Invoice;
import com.techcup_futbol.techcup_futbol.model.User.User;

import java.time.LocalDateTime;

@Component
public class InvoiceMapper {

    // Entidad -> ResponseDTO
    public InvoiceResponseDTO toDto(Invoice invoice) {
        if (invoice == null) {
            return null;
        }

        InvoiceResponseDTO dto = new InvoiceResponseDTO();
        dto.setId(invoice.getId());
        dto.setAmount(invoice.getAmount());
        dto.setDescription(invoice.getDescription());
        dto.setCreatedAt(invoice.getCreatedAt());

        // Mapeamos el nombre del usuario desde la relación
        if (invoice.getUser() != null) {
            dto.setUserName(invoice.getUser().getName());
        }

        return dto;
    }

    // RequestDTO -> Entidad
    // Pasamos el usuario como parámetro porque el DTO solo trae el userId (Long)
    public Invoice toEntity(InvoiceRequestDTO dto, User user) {
        if (dto == null) {
            return null;
        }

        Invoice invoice = new Invoice();
        invoice.setAmount(dto.getAmount());
        invoice.setDescription(dto.getDescription());
        invoice.setCreatedAt(LocalDateTime.now()); // Fecha de creación automática
        invoice.setUser(user); // Asociación con la entidad Usuario

        return invoice;
    }
}