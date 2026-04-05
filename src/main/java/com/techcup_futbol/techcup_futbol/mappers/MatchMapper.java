package com.techcup_futbol.techcup_futbol.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.techcup_futbol.techcup_futbol.dto.Response.MatchResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Match.Match;
@Mapper(componentModel = "spring")
public class MatchMapper {
 
    public MatchResponseDTO toDto(Match match) {
        if (match == null) return null;
 
        MatchResponseDTO dto = new MatchResponseDTO();
 
        dto.setId(match.getId());
        dto.setMatchDate(match.getMatchDate());
        dto.setRound(match.getRound());
        dto.setFieldName(match.getFieldName());
        dto.setState(match.getState().name());
        dto.setHomeTeamGoals(match.getHomeTeamGoals());
        dto.setAwayTeamGoals(match.getAwayTeamGoals());
        dto.setTopScorerGoals(match.getTopScorerGoals());
        dto.setDraw(match.isDraw());
 
        // Torneo
        if (match.getTournament() != null) {
            dto.setTournamentId(match.getTournament().getId());
            dto.setTournamentName(match.getTournament().getName());
        }
 
        // Equipo local
        if (match.getHomeTeam() != null) {
            dto.setHomeTeamId(match.getHomeTeam().getId());
            dto.setHomeTeamName(match.getHomeTeam().getName());
        }
 
        // Equipo visitante
        if (match.getAwayTeam() != null) {
            dto.setAwayTeamId(match.getAwayTeam().getId());
            dto.setAwayTeamName(match.getAwayTeam().getName());
        }
 
        // Ganador (puede ser null si es empate)
        if (match.getWinner() != null) {
            dto.setWinnerId(match.getWinner().getId());
            dto.setWinnerName(match.getWinner().getName());
        }
 
        // Goleador
        if (match.getTopScorer() != null) {
            dto.setTopScorerName(match.getTopScorer().getName());
        }
        if (match.getTopScorerTeam() != null) {
            dto.setTopScorerTeamName(match.getTopScorerTeam().getName());
        }
 
        return dto;
    }
}
 
