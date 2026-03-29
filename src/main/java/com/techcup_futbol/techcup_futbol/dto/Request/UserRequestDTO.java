package com.techcup_futbol.techcup_futbol.dto.Request;

public class UserRequestDTO {
    
    private String email;
    private String password;

    public UserRequestDTO() {}

    public UserRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters y Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}