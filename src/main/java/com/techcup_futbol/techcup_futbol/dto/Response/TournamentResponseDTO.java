package com.techcup_futbol.techcup_futbol.dto.Response;

public class TournamentResponseDTO {

    private Long id;
    private String name;
    private String state;

    // void constructor
    public TournamentResponseDTO() {}

    // compleate constructor
    public TournamentResponseDTO(Long id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    // getters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    // setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setState(String state) {
        this.state = state;
    }
}
