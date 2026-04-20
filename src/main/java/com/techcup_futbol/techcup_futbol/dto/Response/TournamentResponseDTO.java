package com.techcup_futbol.techcup_futbol.dto.Response;

import java.time.LocalDateTime;
import java.util.List;

import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TournamentResponseDTO {

    private Long id;
    private String name;
    private TournamentState state;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private LocalDateTime finishDateToRegister;
    private String type;
    private double costPerTeam;
    private String description;
    private int teamsNumber;
    private List<Team> teams;
    private Team winner;
    
}
