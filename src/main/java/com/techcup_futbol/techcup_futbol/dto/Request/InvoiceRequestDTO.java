package com.techcup_futbol.techcup_futbol.dto.Request;

import java.io.Serializable;

public class InvoiceRequestDTO implements Serializable {
    private Double amount;
    private String description;
    private Long userId; // ID del dueño de la factura

    public InvoiceRequestDTO() {}

    public InvoiceRequestDTO(Double amount, String description, Long userId) {
        this.amount = amount;
        this.description = description;
        this.userId = userId;
    }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}