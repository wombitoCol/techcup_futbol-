package com.techcup_futbol.techcup_futbol.model.Tournament;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime finishDate;

    @Column(nullable = false)
    private LocalDateTime finishDateToRegister;

    @Column(nullable = false)
    private double costPerTeam;

    private String description;

    @Column(nullable = false)
    private int teamsNumber;

    @Column(nullable = false)
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TournamentState state;

    @ManyToMany
    @JoinTable(
        name = "tournaments_teams",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private Team winner;

    public Tournament() {}

    public Tournament(String name, LocalDateTime startDate, LocalDateTime finishDate,
                      LocalDateTime finishDateToRegister, double costPerTeam,
                      String description, String type, TournamentState state) {
        this.name = name;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.finishDateToRegister = finishDateToRegister;
        this.costPerTeam = costPerTeam;
        this.description = description;
        this.type = type;
        this.state = state;
        this.teamsNumber = 0;
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getFinishDate() { return finishDate; }
    public LocalDateTime getFinishDateToRegister() { return finishDateToRegister; }
    public double getCostPerTeam() { return costPerTeam; }
    public String getDescription() { return description; }
    public int getTeamsNumber() { return teamsNumber; }
    public String getType() { return type; }
    public TournamentState getState() { return state; }
    public List<Team> getTeams() { return teams; }
    public Team getWinner() { return winner; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }
    public void setFinishDate(LocalDateTime finishDate) { this.finishDate = finishDate; }
    public void setFinishDateToRegister(LocalDateTime finishDateToRegister) { this.finishDateToRegister = finishDateToRegister; }
    public void setCostPerTeam(double costPerTeam) { this.costPerTeam = costPerTeam; }
    public void setDescription(String description) { this.description = description; }
    public void setTeamsNumber(int teamsNumber) { this.teamsNumber = teamsNumber; }
    public void setType(String type) { this.type = type; }
    public void setState(TournamentState state) { this.state = state; }
    public void setTeams(List<Team> teams) { this.teams = teams; }
    public void setWinner(Team winner) { this.winner = winner; }

    // Métodos de negocio
    public void addTeam(Team team) {
        teams.add(team);
        teamsNumber++;
    }

    public void removeTeam(Team team) {
        teams.remove(team);
        teamsNumber--;
    }

    public void changeState(TournamentState state) {
        this.state = state;
    }

    public void makeMatches() {
        // Implementar lógica de partidos según el tipo de torneo
    }
}