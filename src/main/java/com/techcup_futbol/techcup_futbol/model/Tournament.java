package com.techcup_futbol.techcup_futbol.model;

import com.techcup_futbol.techcup_futbol.strategy.TieBreakingStrategy;
import java.util.ArrayList;
import java.util.List;

public class Tournament {
    private Long id;
    private String name;
    private String state;


    private List<TournamentTeam> teams;             // Los equipos vigentes
    private List<TournamentBracket> brackets;       // Las llaves (8vos, 4tos, etc.)
    private TieBreakingStrategy strategy;           // El patrón de desempate

    public Tournament() {
        this.state = "Draft";
        this.teams = new ArrayList<>();
        this.brackets = new ArrayList<>();
    }

    public List<TournamentTeam> calculateStandings() {
        if (this.strategy != null) {
            return this.strategy.calculatePositions(this.teams);
        }
        return this.teams;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public List<TournamentTeam> getTeams() { return teams; }
    public void setTeams(List<TournamentTeam> teams) { this.teams = teams; }

    public List<TournamentBracket> getBrackets() { return brackets; }
    public void setBrackets(List<TournamentBracket> brackets) { this.brackets = brackets; }

    public TieBreakingStrategy getStrategy() { return strategy; }
    public void setStrategy(TieBreakingStrategy strategy) { this.strategy = strategy; }
}