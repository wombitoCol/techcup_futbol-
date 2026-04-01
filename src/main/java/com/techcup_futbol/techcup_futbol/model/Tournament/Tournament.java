package com.techcup_futbol.techcup_futbol.model.Tournament;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.techcup_futbol.techcup_futbol.model.Payment.Invoice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String name;

    @Column(nullable = false)
    protected LocalDateTime startDate;

    protected LocalDateTime finishDate;

    @Column(nullable = false)
    protected LocalDateTime finishDateToRegister;

    @Column(nullable = false)
    protected double costPerTeam;

    protected String description;

    @Column(nullable = false)
    protected int teamsNumber;

    @ManyToMany
    @JoinTable(
        name = "tournaments_teams",
        joinColumns = @JoinColumn(name = "tournament_id"),
        inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    protected List<Team> teams = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "winner_id")
    protected Team winner;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected TournamentState state;

    @Column(nullable = false)
    protected String type;

    @OneToMany(mappedBy = "tournament")
    protected List<Invoice> invoice;


    public Tournament() {}

    public Long getId() { return id; }

    public String getName() { return name; }

    public LocalDateTime getStartDate() { return startDate; }

    public LocalDateTime getFinishDate() { return finishDate; }

    public LocalDateTime getFinishDateToRegister() { return finishDateToRegister; }

    public double getCostPerTeam() { return costPerTeam; }

    public String getDescription() { return description; }

    public int getTeamsNumber() { return teamsNumber; }

    public List<Team> getTeams() { return teams; }

    public Team getWinner() { return winner; }

    public TournamentState getState() { return state; }

    public void setId(Long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public void setFinishDate(LocalDateTime finishDate) { this.finishDate = finishDate; }

    public void setFinishDateToRegister(LocalDateTime finishDateToRegister) { this.finishDateToRegister = finishDateToRegister; }

    public void setCostPerTeam(double costPerTeam) { this.costPerTeam = costPerTeam; }

    public void setDescription(String description) { this.description = description; }

    public void setTeamsNumber(int teamsNumber) { this.teamsNumber = teamsNumber; }

    public void setTeams(List<Team> teams) { this.teams = teams; }

    public void setWinner(Team winner) { this.winner = winner; }

    public void setState(TournamentState state) { this.state = state; }

    public void addTeam(Team team){
        teams.add(team);
        teamsNumber++;
    }

    public void removeTeam(Team team){
        teams.remove(team);
        teamsNumber--;
    }

    public void changeState(TournamentState state) {
        this.state = state;
    }

    public abstract void makeMatches();
}