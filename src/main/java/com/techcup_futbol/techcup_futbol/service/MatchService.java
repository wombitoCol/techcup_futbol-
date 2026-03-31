package com.techcup_futbol.techcup_futbol.service;

import java.util.List;
 
import com.techcup_futbol.techcup_futbol.dto.Request.MatchRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.MatchResponseDTO;
 
public interface MatchService {
 
    MatchResponseDTO createMatch(MatchRequestDTO request);
 
    MatchResponseDTO getMatchById(Long id);
 
    List<MatchResponseDTO> getMatchesByTournament(Long tournamentId);
 
    List<MatchResponseDTO> getMatchesByTeam(Long teamId);
 
    MatchResponseDTO registerResult(Long matchId, MatchRequestDTO request);
 
    void deleteMatch(Long id);
}