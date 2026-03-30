package com.techcup_futbol.techcup_futbol.service;


import org.springframework.stereotype.Service;

import com.mappers.TournamentMapper;
import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;
import com.techcup_futbol.techcup_futbol.repository.TournamentRepository;
import com.techcup_futbol.techcup_futbol.service.TournamentService;

import jakarta.transaction.Transactional;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    public TournamentServiceImpl(TournamentRepository tournamentRepository,
                                 TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    @Override
    @Transactional
    public TournamentResponseDTO createTournament(TournamentRequestDTO dto) {

        Tournament tournament = new Tournament() {
            @Override
            public void makeMatches() {
            }
        };

        tournament.setName(dto.getName());
        tournament.setStartDate(dto.getStartDate());
        tournament.setFinishDateToRegister(dto.getFinishDateToRegister());
        tournament.setCostPerTeam(dto.getCostPerTeam());
        tournament.setDescription(dto.getDescription());
        tournament.setTeamsNumber(0);
        tournament.setState(TournamentState.DRAFT);

        Tournament saved = tournamentRepository.save(tournament);

        return tournamentMapper.toDto(saved);
    }

    @Override
    @Transactional
    public TournamentResponseDTO updateTournament(Long id, TournamentRequestDTO dto) {

        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        if (tournament.getState() == TournamentState.FINISHED) {
            throw new RuntimeException("Cannot update finished tournament");
        }

        tournament.setName(dto.getName());
        tournament.setStartDate(dto.getStartDate());
        tournament.setFinishDateToRegister(dto.getFinishDateToRegister());
        tournament.setCostPerTeam(dto.getCostPerTeam());
        tournament.setDescription(dto.getDescription());

        Tournament updated = tournamentRepository.save(tournament);

        return tournamentMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void deleteTournament(Long id) {

        Tournament tournament = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        if (tournament.getState() != TournamentState.DRAFT) {
            throw new RuntimeException("Only Draft tournaments can be deleted");
        }

        tournamentRepository.deleteById(id);
    }

}
