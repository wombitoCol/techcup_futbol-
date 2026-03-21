package com.techcup_futbol.techcup_futbol.strategy;

import com.techcup_futbol.techcup_futbol.model.TournamentTeam;
import java.util.ArrayList;
import java.util.List;

public class GoalsForTieBreaker implements TieBreakingStrategy {
    @Override
    public List<TournamentTeam> calculatePositions(List<TournamentTeam> teams) {
        List<TournamentTeam> sortedTeams = new ArrayList<>(teams);
        
        sortedTeams.sort((team1, team2) -> Integer.compare(team2.getGoalsFor(), team1.getGoalsFor()));
        
        return sortedTeams;
    }
}