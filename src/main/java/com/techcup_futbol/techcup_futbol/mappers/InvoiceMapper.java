package com.techcup_futbol.techcup_futbol.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import com.techcup_futbol.techcup_futbol.dto.Request.InvoiceRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.InvoiceResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Payment.Invoice;
import com.techcup_futbol.techcup_futbol.model.User.User;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    public InvoiceResponseDTO toDto(Invoice invoice);
    public Invoice toEntity(InvoiceRequestDTO dto, User user);
}