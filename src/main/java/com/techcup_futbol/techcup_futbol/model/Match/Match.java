package com.techcup_futbol.techcup_futbol.model.Match;

import java.time.LocalDateTime;

import com.techcup_futbol.techcup_futbol.model.PlayerDecorator.PlayerComponent;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "home_team_id", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id", nullable = false)
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name = "winner_team_id")
    private Team winner;

    @Transient
    private PlayerComponent topScorer;

    @ManyToOne
    @JoinColumn(name = "top_scorer_team_id")
    private Team topScorerTeam;

    @Column(nullable = false)
    private int homeTeamGoals = 0;

    @Column(nullable = false)
    private int awayTeamGoals = 0;

    @Column(nullable = false)
    private int topScorerGoals = 0;

    @Column(nullable = false)
    private LocalDateTime matchDate;

    @Column
    private String round;

    @Column
    private String fieldName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MatchState state = MatchState.SCHEDULED;


    public Match() {}

    public Match(Tournament tournament, Team homeTeam, Team awayTeam,
                 LocalDateTime matchDate, String round) {
        this.tournament = tournament;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.matchDate = matchDate;
        this.round = round;
        this.state = MatchState.SCHEDULED;
    }


    public void registerResult(int homeGoals, int awayGoals) {
        this.homeTeamGoals = homeGoals;
        this.awayTeamGoals = awayGoals;
        this.state = MatchState.FINISHED;

        if (homeGoals > awayGoals) {
            this.winner = homeTeam;
        } else if (awayGoals > homeGoals) {
            this.winner = awayTeam;
        } else {
            this.winner = null; // Empate
        }
    }


    public void registerTopScorer(PlayerComponent player, Team team, int goals) {
        this.topScorer = player;
        this.topScorerTeam = team;
        this.topScorerGoals = goals;
    }

    public boolean isDraw() {
        return state == MatchState.FINISHED && winner == null;
    }

    public boolean involvesTeam(Team team) {
        return homeTeam.equals(team) || awayTeam.equals(team);
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }

    public Team getHomeTeam() { return homeTeam; }
    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }

    public Team getAwayTeam() { return awayTeam; }
    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }

    public Team getWinner() { return winner; }
    public void setWinner(Team winner) { this.winner = winner; }

    public PlayerComponent getTopScorer() { return topScorer; }
    public void setTopScorer(PlayerComponent topScorer) { this.topScorer = topScorer; }

    public Team getTopScorerTeam() { return topScorerTeam; }
    public void setTopScorerTeam(Team topScorerTeam) { this.topScorerTeam = topScorerTeam; }

    public int getHomeTeamGoals() { return homeTeamGoals; }
    public void setHomeTeamGoals(int homeTeamGoals) { this.homeTeamGoals = homeTeamGoals; }

    public int getAwayTeamGoals() { return awayTeamGoals; }
    public void setAwayTeamGoals(int awayTeamGoals) { this.awayTeamGoals = awayTeamGoals; }

    public int getTopScorerGoals() { return topScorerGoals; }
    public void setTopScorerGoals(int topScorerGoals) { this.topScorerGoals = topScorerGoals; }

    public LocalDateTime getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDateTime matchDate) { this.matchDate = matchDate; }

    public String getRound() { return round; }
    public void setRound(String round) { this.round = round; }

    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }

    public MatchState getState() { return state; }
    public void setState(MatchState state) { this.state = state; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return id != null && id.equals(match.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        String scorerName = topScorer != null ? topScorer.getName() : "N/A";
        return String.format("Match[%s vs %s | %s | %d-%d | Goleador: %s | %s]",
            homeTeam.getName(), awayTeam.getName(),
            round, homeTeamGoals, awayTeamGoals, scorerName, state);
    }
}