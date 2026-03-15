package com.techcup_futbol.techcup_futbol;

import java.time.LocalDateTime;
import java.util.List;

public class TournamentLightning implements ObservableSubject{

    private final String id;
    private final LocalDateTime startDate;
    private final LocalDateTime finishDate;
    private final LocalDateTime finishDateToRegister;
    private final LocalDateTime importantDates;
    private final double costPerTeam;
    private final String description;
    private int teamsNumber;
    private List<Team> teams;
    private List<Observer> subscribers;
    private List<Team> currentTeams;
    private Team winner;

    public TournamentLightning(String id, LocalDateTime startDate, LocalDateTime finishDate, LocalDateTime finishDateToRegister, 
        LocalDateTime importantDates, double costPerTeam, String description){

        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.finishDateToRegister = finishDateToRegister;
        this.importantDates= importantDates;
        this.costPerTeam = costPerTeam;
        this.description = description;
        teamsNumber = 0;
        teams = null;
        currentTeams = null;
        winner = null;
    }

    public void addTeam(Team team){
        teams.add(team);
        subscribe((Observer)team);
    }

    public void subscribe(Observer observer){
        subscribers.add(observer);
    }


    public void unsubscribe(Observer observer){
        subscribers.remove(observer);
    }

    public void notifyObservers(){
        subscribers.stream().forEach(s -> s.update());
    }
    
}