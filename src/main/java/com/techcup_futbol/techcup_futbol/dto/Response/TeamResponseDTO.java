package com.techcup_futbol.techcup_futbol.dto.Response;

public class TeamResponseDTO {
    
    private Long id;
    private String name;

    public TeamResponseDTO() {}

    public TeamResponseDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}