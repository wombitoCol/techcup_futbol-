package com.techcup_futbol.techcup_futbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {

}

