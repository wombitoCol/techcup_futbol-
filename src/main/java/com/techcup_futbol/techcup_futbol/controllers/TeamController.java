package com.techcup_futbol.techcup_futbol.controllers;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techcup_futbol.techcup_futbol.dto.Request.TeamRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TeamResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.repository.TeamRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    
    private final TeamRepository teamRepository;
    
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    
    @PostMapping("")
    public ResponseEntity<TeamResponseDTO> createTeam(@Valid @RequestBody TeamRequestDTO teamRequest) {
        TeamResponseDTO teamResponse = TeamService.createTeam(teamRequest);
        return ResponseEntity.status(HttpStatus.created).body(teamResponse);
    }
    
    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamRepository.save(team);
    }
    
    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Team not found"));
    }
}
