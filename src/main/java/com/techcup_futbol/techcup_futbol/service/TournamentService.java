package com.techcup_futbol.techcup_futbol.service;

import com.techcup_futbol.techcup_futbol.model.Tournament;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TournamentService {
    private static final Logger log = LoggerFactory.getLogger(TournamentService.class);
    private final List<Tournament> tournaments = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Tournament create(Tournament tournament) {
        tournament.setId(idGenerator.getAndIncrement());
        tournament.setState("Draft");
        tournaments.add(tournament);
        log.info("Tournament created: {}", tournament.getName());
        return tournament;
    }

    public Optional<Tournament> update(Long id, Tournament newTournament) {
        for (int i = 0; i < tournaments.size(); i++) {
            Tournament current = tournaments.get(i);
            if (current.getId().equals(id) && !current.getState().equals("Finished")) {
                newTournament.setId(id);
                tournaments.set(i, newTournament);
                log.info("Tournament updated ID: {}", id);
                return Optional.of(newTournament);
            }
        }
        log.warn("Could not update tournament ID: {}. It may not exist or is already Finished.", id);
        return Optional.empty();
    }

    public boolean delete(Long id) {
        boolean removed = tournaments.removeIf(t -> t.getId().equals(id) && t.getState().equals("Draft"));
        if (removed) {
            log.info("Tournament deleted ID: {}", id);
        } else {
            log.warn("Could not delete tournament ID: {}. It can only be deleted in Draft state.", id);
        }
        return removed;
    }
}