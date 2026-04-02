package com.techcup_futbol.techcup_futbol.controllers;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.techcup_futbol.techcup_futbol.dto.Request.TeamRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TeamResponseDTO;
import com.techcup_futbol.techcup_futbol.service.TeamService;
 
import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/teams")
public class TeamController {
 
    private final TeamService teamService;
 
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }
 
    @PostMapping
    public ResponseEntity<TeamResponseDTO> createTeam(@Valid @RequestBody TeamRequestDTO dto) {
        TeamResponseDTO response = teamService.createTeam(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponseDTO> getTeamById(@PathVariable Long id) {
        TeamResponseDTO response = teamService.getTeamById(id);
        return ResponseEntity.ok(response);
    }
}
