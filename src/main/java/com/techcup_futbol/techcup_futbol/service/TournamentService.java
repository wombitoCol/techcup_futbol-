package com.techcup_futbol.techcup_futbol.service;


import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
public interface TournamentService {


    void deleteTournament(Long id);
    TournamentResponseDTO updateTournament(Long id, TournamentRequestDTO dto);
    TournamentResponseDTO createTournament(TournamentRequestDTO dto);
}