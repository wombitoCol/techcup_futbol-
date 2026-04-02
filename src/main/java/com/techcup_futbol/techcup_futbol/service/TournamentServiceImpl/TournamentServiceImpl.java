package com.techcup_futbol.techcup_futbol.service.TournamentServiceImpl;


import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
import com.techcup_futbol.techcup_futbol.mappers.TournamentMapper;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;
import com.techcup_futbol.techcup_futbol.repository.TournamentRepository;
import com.techcup_futbol.techcup_futbol.service.TournamentService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
 
@Service
@RequiredArgsConstructor
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;
 
 
    //Creo que falta definir el tipoi especifico de torneo
 
    @Override
    public TournamentResponseDTO createTournament(TournamentRequestDTO dto) {
        Tournament tournament = new Tournament(
            dto.getName(),
            dto.getStartDate(),
            null,
            dto.getFinishDateToRegister(),
            dto.getCostPerTeam(),
            dto.getDescription(),
            dto.getType(),
            dto.getState() != null ? dto.getState() : TournamentState.DRAFT
        );
 
        Tournament saved = tournamentRepository.save(tournament);
        return toResponseDTO(saved);
    }
 
    @Override
    public TournamentResponseDTO updateTournament(Long id, TournamentRequestDTO dto) {
        Tournament tournament = tournamentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Tournament not found with id: " + id));
 
        tournament.setName(dto.getName());
        tournament.setStartDate(dto.getStartDate());
        tournament.setFinishDateToRegister(dto.getFinishDateToRegister());
        tournament.setCostPerTeam(dto.getCostPerTeam());
        tournament.setDescription(dto.getDescription());
        tournament.setType(dto.getType());
        if (dto.getState() != null) {
            tournament.setState(dto.getState());
        }
 
        Tournament saved = tournamentRepository.save(tournament);
        return toResponseDTO(saved);
    }
 
    @Override
    public void deleteTournament(Long id) {
        if (!tournamentRepository.existsById(id)) {
            throw new RuntimeException("Tournament not found with id: " + id);
        }
        tournamentRepository.deleteById(id);
    }
 
    private TournamentResponseDTO toResponseDTO(Tournament t) {
        return TournamentResponseDTO.builder()
            .id(t.getId())
            .name(t.getName())
            .state(t.getState())
            .startDate(t.getStartDate())
            .finishDate(t.getFinishDate())
            .finishDateToRegister(t.getFinishDateToRegister())
            .type(t.getType())
            .costPerTeam(t.getCostPerTeam())
            .description(t.getDescription())
            .teamsNumber(t.getTeamsNumber())
            .teams(t.getTeams())
            .winner(t.getWinner())
            .build();
    }
}
 
