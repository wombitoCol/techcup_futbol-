package com.techcup_futbol.techcup_futbol.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techcup_futbol.techcup_futbol.model.Tournament.Team;
import com.techcup_futbol.techcup_futbol.model.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.model.Tournament.TournamentState;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {


    Optional<Tournament> findByName(String name);

    List<Tournament> findByStartDate(LocalDateTime time);
    List<Tournament> findByFinishDate(LocalDateTime time);
    List<Tournament> findByFinishDateToRegister(LocalDateTime time);
    List<Tournament> findByCostPerTeam( double costPerTeam);
    List<Tournament> findByDescriptionContainingIgnoreCase(String description);
    List<Tournament> findByTeamsNumber( int teamsNumber); 
    List<Tournament> findByTeams(List<Team> teams);
    List<Tournament> findByWiner(Team winner);
    List<Tournament> findByState( TournamentState state);

}

