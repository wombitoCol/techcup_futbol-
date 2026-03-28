package com.techcup_futbol.techcup_futbol.dto.Request;

public class TeamRequestDTO {
    
    private String name;

    public TeamRequestDTO() {}

    public TeamRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}