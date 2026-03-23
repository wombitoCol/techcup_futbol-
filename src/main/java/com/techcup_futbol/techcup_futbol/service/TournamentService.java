package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
public interface TournamentService {

    TournamentResponseDTO createTournament(TournamentRequestDTO dto);

    TournamentResponseDTO updateTournament(Long id, TournamentRequestDTO dto);

    void deleteTournament(Long id);
}