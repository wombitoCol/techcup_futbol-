package com.techcup_futbol.techcup_futbol.controllers;

import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

@RestController
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    // POST: Para crear un torneo y guardarlo en PostgreSQL
    @PostMapping
    public ResponseEntity<TournamentResponseDTO> create(
            @RequestBody TournamentRequestDTO dto) {

        TournamentResponseDTO created = tournamentService.createTournament(dto);
    }

    // GET: Para ver todos los torneos guardados
    @PutMapping("/{id}")
    public ResponseEntity<TournamentResponseDTO> update(
            @PathVariable Long id,
            @RequestBody TournamentRequestDTO dto) {

        TournamentResponseDTO updated = tournamentService.updateTournament(id, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE: Para borrar un torneo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        tournamentService.deleteTournament(id);
}