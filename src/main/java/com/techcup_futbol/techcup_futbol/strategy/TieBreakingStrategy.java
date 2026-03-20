package com.techcup_futbol.techcup_futbol.strategy;

import com.techcup_futbol.techcup_futbol.model.TournamentTeam;
import java.util.List;

public interface TieBreakingStrategy {
    List<TournamentTeam> calculatePositions(List<TournamentTeam> teams);
}