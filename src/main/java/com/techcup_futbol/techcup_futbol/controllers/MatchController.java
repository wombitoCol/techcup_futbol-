package com.techcup_futbol.techcup_futbol.controllers;
    
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.techcup_futbol.techcup_futbol.dto.Request.MatchRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.MatchResponseDTO;
import com.techcup_futbol.techcup_futbol.service.MatchService;
 
import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/matches")
public class MatchController {
 
    private final MatchService matchService;
 
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }
 
    // POST /api/matches
    // Crear un partido nuevo
    @PostMapping
    public ResponseEntity<MatchResponseDTO> createMatch(@Valid @RequestBody MatchRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.createMatch(request));
    }
 
    // GET /api/matches/{id}
    // Obtener un partido por id
    @GetMapping("/{id}")
    public ResponseEntity<MatchResponseDTO> getMatchById(@PathVariable Long id) {
        return ResponseEntity.ok(matchService.getMatchById(id));
    }
 
    // GET /api/matches/tournament/{tournamentId}
    // Obtener todos los partidos de un torneo
    @GetMapping("/tournament/{tournamentId}")
    public ResponseEntity<List<MatchResponseDTO>> getMatchesByTournament(@PathVariable Long tournamentId) {
        return ResponseEntity.ok(matchService.getMatchesByTournament(tournamentId));
    }
 
    // GET /api/matches/team/{teamId}
    // Obtener todos los partidos de un equipo (local o visitante)
    @GetMapping("/team/{teamId}")
    public ResponseEntity<List<MatchResponseDTO>> getMatchesByTeam(@PathVariable Long teamId) {
        return ResponseEntity.ok(matchService.getMatchesByTeam(teamId));
    }
 
    // PUT /api/matches/{id}/result
    // Registrar el resultado de un partido
    @PutMapping("/{id}/result")
    public ResponseEntity<MatchResponseDTO> registerResult(@PathVariable Long id,
                                                           @Valid @RequestBody MatchRequestDTO request) {
        return ResponseEntity.ok(matchService.registerResult(id, request));
    }
 
    // DELETE /api/matches/{id}
    // Eliminar un partido
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}