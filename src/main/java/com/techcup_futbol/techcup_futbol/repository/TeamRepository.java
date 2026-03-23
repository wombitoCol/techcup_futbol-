package com.techcup_futbol.techcup_futbol.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techcup_futbol.techcup_futbol.model.Tournament.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByNameContainingIgnoreCase(String name);
}