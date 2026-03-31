package com.techcup_futbol.techcup_futbol.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;

public interface TeamRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByNameContainingIgnoreCase(String name);

    Team findByName(String name);

    List<Team> findByTournaments(Tournament tournament);

    List<Team> findAll();

}