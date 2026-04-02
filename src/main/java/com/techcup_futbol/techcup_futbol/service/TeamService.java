package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.dto.Request.TeamRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TeamResponseDTO;
 
public interface TeamService {
 
    TeamResponseDTO createTeam(TeamRequestDTO dto);
 
    TeamResponseDTO getTeamById(Long id);
}

