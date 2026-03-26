package com.techcup_futbol.techcup_futbol.model.Tournament;
import java.time.LocalDateTime;

public interface TournamentBuilder {



    TournamentBuilder id(Long id);

    TournamentBuilder startDate(LocalDateTime startDate);

    TournamentBuilder finishDate(LocalDateTime finishDate);

    TournamentBuilder finishDateToRegister(LocalDateTime finishDateToRegister);

    TournamentBuilder importantDates(LocalDateTime importantDates);

    TournamentBuilder costPerTeam(double costPerTeam);

    TournamentBuilder description(String description);
    
    Tournament build();

    
}
