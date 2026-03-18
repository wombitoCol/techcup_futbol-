package com.techcup_futbol.techcup_futbol.TournamentBuilder;
import java.time.LocalDateTime;
import java.util.List;

public class TournamentLightning extends Tournament{


    public TournamentLightning(String id, LocalDateTime startDate, LocalDateTime finishDate,
                               LocalDateTime finishDateToRegister, LocalDateTime importantDates,
                               double costPerTeam, String description) {
        super(id, startDate, finishDate, finishDateToRegister, importantDates, costPerTeam, description);
    }

    public void makeMatches(){}

}