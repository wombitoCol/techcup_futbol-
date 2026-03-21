package com.techcup_futbol.techcup_futbol.strategy;

import com.techcup_futbol.techcup_futbol.model.TournamentTeam;
import java.util.ArrayList;
import java.util.List;

public class FairPlayTieBreaker implements TieBreakingStrategy {
    @Override
    public List<TournamentTeam> calculatePositions(List<TournamentTeam> teams) {
        List<TournamentTeam> sortedTeams = new ArrayList<>(teams);
        
        sortedTeams.sort((team1, team2) -> Double.compare(team2.getFairPlayPoints(), team1.getFairPlayPoints()));
        
        return sortedTeams;
    }
}