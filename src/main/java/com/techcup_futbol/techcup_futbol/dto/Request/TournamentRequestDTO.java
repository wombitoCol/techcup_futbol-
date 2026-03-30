package com.techcup_futbol.techcup_futbol.dto.Request;
import java.time.LocalDateTime;

import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TournamentRequestDTO {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime finishDateToRegister;
    private double costPerTeam;
    private String description;
    private TournamentState state;
    private String type;


}