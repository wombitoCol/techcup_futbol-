package com.techcup_futbol.techcup_futbol.controllers;

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

    @Autowired
    private TournamentService tournamentService;

    // POST: Para crear un torneo y guardarlo en PostgreSQL
    @PostMapping
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
        Tournament created = tournamentService.create(tournament);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // GET: Para ver todos los torneos guardados
    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        return ResponseEntity.ok(tournamentService.getAll());
    }

    // DELETE: Para borrar un torneo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        tournamentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}