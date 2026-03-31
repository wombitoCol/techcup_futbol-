package com.techcup_futbol.techcup_futbol.dto.Response;

import java.time.LocalDateTime;
 
public class MatchResponseDTO {
 
    private Long id;
    private Long tournamentId;
    private String tournamentName;
 
    private Long homeTeamId;
    private String homeTeamName;
    private int homeTeamGoals;
 
    private Long awayTeamId;
    private String awayTeamName;
    private int awayTeamGoals;
 
    private Long winnerId;
    private String winnerName;
 
    private String topScorerName;
    private String topScorerTeamName;
    private int topScorerGoals;
 
    private LocalDateTime matchDate;
    private String round;
    private String fieldName;
    private String state;
    private boolean draw;
  
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }
 
    public String getTournamentName() { return tournamentName; }
    public void setTournamentName(String tournamentName) { this.tournamentName = tournamentName; }
 
    public Long getHomeTeamId() { return homeTeamId; }
    public void setHomeTeamId(Long homeTeamId) { this.homeTeamId = homeTeamId; }
 
    public String getHomeTeamName() { return homeTeamName; }
    public void setHomeTeamName(String homeTeamName) { this.homeTeamName = homeTeamName; }
 
    public int getHomeTeamGoals() { return homeTeamGoals; }
    public void setHomeTeamGoals(int homeTeamGoals) { this.homeTeamGoals = homeTeamGoals; }
 
    public Long getAwayTeamId() { return awayTeamId; }
    public void setAwayTeamId(Long awayTeamId) { this.awayTeamId = awayTeamId; }
 
    public String getAwayTeamName() { return awayTeamName; }
    public void setAwayTeamName(String awayTeamName) { this.awayTeamName = awayTeamName; }
 
    public int getAwayTeamGoals() { return awayTeamGoals; }
    public void setAwayTeamGoals(int awayTeamGoals) { this.awayTeamGoals = awayTeamGoals; }
 
    public Long getWinnerId() { return winnerId; }
    public void setWinnerId(Long winnerId) { this.winnerId = winnerId; }
 
    public String getWinnerName() { return winnerName; }
    public void setWinnerName(String winnerName) { this.winnerName = winnerName; }
 
    public String getTopScorerName() { return topScorerName; }
    public void setTopScorerName(String topScorerName) { this.topScorerName = topScorerName; }
 
    public String getTopScorerTeamName() { return topScorerTeamName; }
    public void setTopScorerTeamName(String topScorerTeamName) { this.topScorerTeamName = topScorerTeamName; }
 
    public int getTopScorerGoals() { return topScorerGoals; }
    public void setTopScorerGoals(int topScorerGoals) { this.topScorerGoals = topScorerGoals; }
 
    public LocalDateTime getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDateTime matchDate) { this.matchDate = matchDate; }
 
    public String getRound() { return round; }
    public void setRound(String round) { this.round = round; }
 
    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }
 
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
 
    public boolean isDraw() { return draw; }
    public void setDraw(boolean draw) { this.draw = draw; }
}
