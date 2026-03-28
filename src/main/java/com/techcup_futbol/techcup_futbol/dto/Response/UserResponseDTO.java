package com.techcup_futbol.techcup_futbol.dto.Response;

public class UserResponseDTO {
    
    private Long id;
    private String email;

    public UserResponseDTO() {}

    public UserResponseDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}