package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.dto.Request.TeamRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TeamResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.repository.TeamRepository;
import org.springframework.stereotype.Service;
 
@Service
public class TeamServiceImpl implements TeamService {
 
    private final TeamRepository teamRepository;
 
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
 
    @Override
    public TeamResponseDTO createTeam(TeamRequestDTO dto) {
        Team team = new Team();
        team.setName(dto.getName());
 
        Team saved = teamRepository.save(team);
 
        return TeamResponseDTO.builder()
            .id(saved.getId())
            .name(saved.getName())
            .build();
    }
 
    @Override
    public TeamResponseDTO getTeamById(Long id) {
        Team team = teamRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Team not found with id: " + id));
 
        return TeamResponseDTO.builder()
            .id(team.getId())
            .name(team.getName())
            .build();
    }
}
