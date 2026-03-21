package com.techcup_futbol.techcup_futbol.model.Tournament;
import java.time.LocalDateTime;
import java.util.List;

public class TournamentLightning extends Tournament{


    public TournamentLightning(Long id, LocalDateTime startDate, LocalDateTime finishDate,
                               LocalDateTime finishDateToRegister, LocalDateTime importantDates,
                               double costPerTeam, String description) {
        super(id, startDate, finishDate, finishDateToRegister, importantDates, costPerTeam, description);
    }

    public void makeMatches(){}

    @Override
    protected void setId(long andIncrement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }

}