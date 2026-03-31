package com.techcup_futbol.techcup_futbol.dto.Response;

import java.io.Serializable;
import java.time.LocalDateTime;

public class InvoiceResponseDTO implements Serializable {
    private Long id;
    private Double amount;
    private String description;
    private LocalDateTime createdAt;
    private String userName; // Nombre del usuario asociado

    public InvoiceResponseDTO() {}

    public InvoiceResponseDTO(Long id, Double amount, String description, LocalDateTime createdAt, String userName) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.createdAt = createdAt;
        this.userName = userName;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}