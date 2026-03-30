package com.techcup_futbol.techcup_futbol.controllers;

import com.techcup_futbol.techcup_futbol.dto.Request.TournamentRequestDTO;
import com.techcup_futbol.techcup_futbol.dto.Response.TournamentResponseDTO;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.service.TournamentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    // POST: Para crear un torneo y guardarlo en PostgreSQL
    @PostMapping
    public ResponseEntity<TournamentResponseDTO> create(
            @Valid @RequestBody TournamentRequestDTO dto) {

        TournamentResponseDTO created = tournamentService.createTournament(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET: Para ver todos los torneos guardados
    @PutMapping("/{id}")
    public ResponseEntity<TournamentResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody TournamentRequestDTO dto) {

        TournamentResponseDTO updated = tournamentService.updateTournament(id, dto);
        return ResponseEntity.ok(updated);
    }

    // DELETE: Para borrar un torneo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        tournamentService.deleteTournament(id);
        return ResponseEntity.noContent().build();
    }
}