package com.techcup_futbol.techcup_futbol.model.Tournament;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import com.techcup_futbol.techcup_futbol.model.Payment.Invoice;

import com.techcup_futbol.techcup_futbol.model.Match.Match;

import jakarta.persistence.CascadeType;

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
import jakarta.persistence.OneToMany;

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


    @OneToMany(mappedBy = "tournament")
    protected List<Invoice> invoice;


    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> matches = new ArrayList<>();

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
        if (teams == null || teams.size() < 2) {
            throw new IllegalStateException("Se necesitan al menos 2 equipos para generar partidos.");
        }
 
        matches.clear();
 
        List<Team> shuffled = new ArrayList<>(teams);
        Collections.shuffle(shuffled);
 
        switch (type.toLowerCase()) {
            case "robin":
            case "round_robin":
                makeRoundRobinMatches(shuffled);
                break;
 
            case "elimination":
            case "knockout":
                makeEliminationMatches(shuffled);
                break;
 
            case "groups":
            case "groups_elimination":
                makeGroupsAndEliminationMatches(shuffled);
                break;
 
            default:
                throw new IllegalArgumentException("Tipo de torneo no reconocido: " + type);
        }
    }

    private void makeRoundRobinMatches(List<Team> shuffled) {
        LocalDateTime matchDate = startDate;
 
        for (int i = 0; i < shuffled.size(); i++) {
            for (int j = i + 1; j < shuffled.size(); j++) {
                matches.add(new Match(this, shuffled.get(i), shuffled.get(j), matchDate, "Round Robin"));
                matchDate = matchDate.plusDays(1);
            }
        }
    }
 

    private void makeEliminationMatches(List<Team> shuffled) {
        int size = shuffled.size();
        if ((size & (size - 1)) != 0) {
            throw new IllegalStateException(
                "La eliminación directa requiere equipos en potencia de 2 (4, 8, 16...).");
        }
 
        List<Team> currentRound = new ArrayList<>(shuffled);
        int roundNumber = 1;
        LocalDateTime matchDate = startDate;
 
        while (currentRound.size() > 1) {
            String roundName = getRoundName(currentRound.size());
            List<Team> nextRound = new ArrayList<>();
 
            for (int i = 0; i < currentRound.size(); i += 2) {
                matches.add(new Match(this, currentRound.get(i), currentRound.get(i + 1), matchDate, roundName));
                matchDate = matchDate.plusDays(2);
                nextRound.add(currentRound.get(i)); 
            }
 
            currentRound = nextRound;
            roundNumber++;
            matchDate = matchDate.plusDays(3);
        }
    }
 

    private void makeGroupsAndEliminationMatches(List<Team> shuffled) {
        int groupSize = 4;
        if (shuffled.size() < groupSize * 2) {
            throw new IllegalStateException(
                "Se necesitan al menos " + (groupSize * 2) + " equipos para fase de grupos.");
        }
 
        LocalDateTime matchDate = startDate;
        int totalGroups = shuffled.size() / groupSize;
        List<Team> groupWinners = new ArrayList<>();
 
        for (int g = 0; g < totalGroups; g++) {
            String groupName = "Grupo " + (char) ('A' + g);
            List<Team> groupTeams = shuffled.subList(g * groupSize, Math.min((g + 1) * groupSize, shuffled.size()));
 
            for (int i = 0; i < groupTeams.size(); i++) {
                for (int j = i + 1; j < groupTeams.size(); j++) {
                    matches.add(new Match(this, groupTeams.get(i), groupTeams.get(j), matchDate, groupName));
                    matchDate = matchDate.plusDays(1);
                }
            }
 
            groupWinners.add(groupTeams.get(0));
            groupWinners.add(groupTeams.get(1));
        }
 
        matchDate = matchDate.plusDays(3);
        List<Team> currentRound = new ArrayList<>(groupWinners);
 
        while (currentRound.size() > 1) {
            String roundName = getRoundName(currentRound.size());
            List<Team> nextRound = new ArrayList<>();
 
            for (int i = 0; i < currentRound.size(); i += 2) {
                Team away = (i + 1 < currentRound.size()) ? currentRound.get(i + 1) : currentRound.get(i);
                matches.add(new Match(this, currentRound.get(i), away, matchDate, roundName));
                matchDate = matchDate.plusDays(2);
                nextRound.add(currentRound.get(i));
            }
 
            currentRound = nextRound;
            matchDate = matchDate.plusDays(3);
        }
    }
 
    private String getRoundName(int teamsLeft) {
        switch (teamsLeft) {
            case 2:  return "Final";
            case 4:  return "Semifinal";
            case 8:  return "Cuartos de final";
            default: return "Ronda de " + teamsLeft;
        }
    }
 
}