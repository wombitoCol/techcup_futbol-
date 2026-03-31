package com.techcup_futbol.techcup_futbol.repository;
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;

import com.techcup_futbol.techcup_futbol.model.Match.Match;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
 
public interface MatchRepository extends JpaRepository<Match, Long> {
 
    List<Match> findByTournament(Tournament tournament);
 
    List<Match> findByHomeTeamOrAwayTeam(Team homeTeam, Team awayTeam);
 
    List<Match> findByWinner(Team winner);
}
 