package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.dto.Request.TeamRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TeamResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team; 
import com.techcup_futbol.techcup_futbol.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    public TeamResponseDTO createTeam(TeamRequestDTO request) {
        
        // 1. Transformamos el DTO a la Entidad Team
        Team newTeam = new Team();
        newTeam.setName(request.getName()); 

        // 2. Guardamos en la base de datos
        Team savedTeam = teamRepository.save(newTeam);

        // 3. Preparamos el DTO de respuesta
        TeamResponseDTO response = new TeamResponseDTO();
        response.setId(savedTeam.getId());
        response.setName(savedTeam.getName());

        return response;
    }
}