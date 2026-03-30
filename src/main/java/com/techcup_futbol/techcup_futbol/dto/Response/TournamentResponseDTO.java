package com.techcup_futbol.techcup_futbol.dto.Response;


import java.time.LocalDate;
import java.time.LocalDateTime;

import com.techcup_futbol.techcup_futbol.model.Tournament.Team;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TournamentResponseDTO {

    private Long id;
    private String name;
    private String state;
    private String description;
    private int teamsNumber;
    private LocalDateTime startDate;
    private LocalDateTime finishDateToRegister;
    private Double costPerTeam;
    private Team winner;

}