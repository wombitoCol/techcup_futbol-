package com.techcup_futbol.techcup_futbol.model;

import com.techcup_futbol.techcup_futbol.Observer.ObservableSubject;
import com.techcup_futbol.techcup_futbol.Observer.Observer;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tournament_teams")
public class TournamentTeam implements ObservableSubject, Observer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private double points;
    private int goalsFor;
    private int goalsAgainst;
    private double fairPlayPoints;

    // Relación con el Torneo 
    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    // @Transient le dice a la base de datos que ignore esta lista al guardar
    @Transient
    private List<Observer> subscribers = new ArrayList<>();

    public TournamentTeam() {
        this.subscribers = new ArrayList<>();
    }

    public TournamentTeam(Long id, String name, double points, int goalsFor, int goalsAgainst, double fairPlayPoints) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.fairPlayPoints = fairPlayPoints;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(Observer observer) {
        subscribers.add(observer);
    }

    @Override
    public void unsubscribe(Observer observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        subscribers.forEach(s -> s.update());
    }

    @Override
    public void update() {
        subscribers.forEach(s -> s.update());
    }

    public int getGoalDifference() { 
        return goalsFor - goalsAgainst; 
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public double getPoints() { return points; }
    public void setPoints(double points) { this.points = points; }
    
    public int getGoalsFor() { return goalsFor; }
    public void setGoalsFor(int goalsFor) { this.goalsFor = goalsFor; }
    
    public int getGoalsAgainst() { return goalsAgainst; }
    public void setGoalsAgainst(int goalsAgainst) { this.goalsAgainst = goalsAgainst; }
    
    public double getFairPlayPoints() { return fairPlayPoints; }
    public void setFairPlayPoints(double fairPlayPoints) { this.fairPlayPoints = fairPlayPoints; }
    
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TournamentTeam that = (TournamentTeam) o;

        return id != null && id.equals(that.getId());
    }
}