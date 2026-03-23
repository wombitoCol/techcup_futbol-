package com.techcup_futbol.techcup_futbol.controllers;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.repository.TeamRepository;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    
    private final TeamRepository teamRepository;
    
    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }
    
    @GetMapping
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
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
