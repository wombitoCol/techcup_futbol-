package com.techcup_futbol.techcup_futbol.dto.Request;
import java.time.LocalDateTime;
 
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
 
public class MatchRequestDTO {
 
 
    @NotNull(message = "El torneo es obligatorio")
    private Long tournamentId;
 
    @NotNull(message = "El equipo local es obligatorio")
    private Long homeTeamId;
 
    @NotNull(message = "El equipo visitante es obligatorio")
    private Long awayTeamId;
 
    @NotNull(message = "La fecha del partido es obligatoria")
    private LocalDateTime matchDate;
 
    private String round;
 
    private String fieldName;
  
    @Min(value = 0, message = "Los goles no pueden ser negativos")
    private Integer homeTeamGoals;
 
    @Min(value = 0, message = "Los goles no pueden ser negativos")
    private Integer awayTeamGoals;
 
    private String topScorerName;
 
    private Long topScorerTeamId;
 
    @Min(value = 0, message = "Los goles del goleador no pueden ser negativos")
    private int topScorerGoals;
 
 
    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }
 
    public Long getHomeTeamId() { return homeTeamId; }
    public void setHomeTeamId(Long homeTeamId) { this.homeTeamId = homeTeamId; }
 
    public Long getAwayTeamId() { return awayTeamId; }
    public void setAwayTeamId(Long awayTeamId) { this.awayTeamId = awayTeamId; }
 
    public LocalDateTime getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDateTime matchDate) { this.matchDate = matchDate; }
 
    public String getRound() { return round; }
    public void setRound(String round) { this.round = round; }
 
    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }
 
    public Integer getHomeTeamGoals() { return homeTeamGoals; }
    public void setHomeTeamGoals(Integer homeTeamGoals) { this.homeTeamGoals = homeTeamGoals; }
 
    public Integer getAwayTeamGoals() { return awayTeamGoals; }
    public void setAwayTeamGoals(Integer awayTeamGoals) { this.awayTeamGoals = awayTeamGoals; }
 
    public String getTopScorerName() { return topScorerName; }
    public void setTopScorerName(String topScorerName) { this.topScorerName = topScorerName; }
 
    public Long getTopScorerTeamId() { return topScorerTeamId; }
    public void setTopScorerTeamId(Long topScorerTeamId) { this.topScorerTeamId = topScorerTeamId; }
 
    public int getTopScorerGoals() { return topScorerGoals; }
    public void setTopScorerGoals(int topScorerGoals) { this.topScorerGoals = topScorerGoals; }
 
 
    public boolean hasResult() {
        return homeTeamGoals != null && awayTeamGoals != null;
    }
}
