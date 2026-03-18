package com.techcup_futbol.techcup_futbol.TournamentBuilder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import com.techcup_futbol.techcup_futbol.Observer.ObservableSubject;
import com.techcup_futbol.techcup_futbol.Observer.Observer;
import com.techcup_futbol.techcup_futbol.Observer.Team;

public abstract class Tournament implements ObservableSubject{
    protected final String id;
    protected final LocalDateTime startDate;
    protected final LocalDateTime finishDate;
    protected final LocalDateTime finishDateToRegister;
    protected final LocalDateTime importantDates;
    protected final double costPerTeam;
    protected final String description;
    protected int teamsNumber;
    protected TreeMap<String, Team> teams;
    protected List<Observer> subscribers;
    protected List<Team> currentTeams;
    protected Team winner;

    public Tournament(String id, LocalDateTime startDate, LocalDateTime finishDate,
                      LocalDateTime finishDateToRegister, LocalDateTime importantDates,
                      double costPerTeam, String description) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.finishDateToRegister = finishDateToRegister;
        this.importantDates = importantDates;
        this.costPerTeam = costPerTeam;
        this.description = description;

        this.teamsNumber = 0;
        this.teams = new TreeMap<>();
        this.subscribers = new ArrayList<>();
        this.currentTeams = new ArrayList<>();
        this.winner = null;
    }

//======Modifications of lists======

    public void addTeam(Team team){
        teams.put(team.getId(),team);
        subscribe((Observer)team);
        addCurrentTeam(team);
        teamsNumber++;
    }

    public void removeTeam(Team team){
        teams.remove(team.getId());
        unsubscribe((Observer)team);
        removeCurrentTeam(team);
        teamsNumber--;
    }

    public void subscribe(Observer observer){
        subscribers.add(observer);
    }

    public void unsubscribe(Observer observer){
        subscribers.remove(observer);
    }

    public void addCurrentTeam(Team team){
        currentTeams.add(team);
    }

    public void removeCurrentTeam(Team team){
        currentTeams.remove(team);
    }

    public void notifyObservers(){
        subscribers.stream().forEach(s -> s.update());
    }

    abstract void makeMatches();



//========getters=======//

    public String getId() {
        return id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public LocalDateTime getFinishDateToRegister() {
        return finishDateToRegister;
    }

    public LocalDateTime getImportantDates() {
        return importantDates;
    }

    public double getCostPerTeam() {
        return costPerTeam;
    }

    public String getDescription() {
        return description;
    }

    public int getTeamsNumber() {
        return teamsNumber;
    }

    public TreeMap<String,Team> getTeams() {
        return teams;
    }

    public List<Observer> getSubscribers() {
        return subscribers;
    }

    public List<Team> getCurrentTeams() {
        return currentTeams;
    }
    public Team getTeamSubscribed(String idTeam){
        return subscribers.stream()
            .map(observer -> (Team) observer)
            .filter(team -> team.getId().equals(idTeam))
            .findFirst()
            .orElse(null);
    }

    public Team getTeam(String id){
        return teams.get(id);
    }

    public Team getWinner() {
        return winner;
    }

//=======Setters=======

    public void setTeamsNumber(int teamsNumber) {
        this.teamsNumber = teamsNumber;
    }


    public void setWinner(Team winner) {
        this.winner = winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Tournament that = (Tournament) o;

        return id.equals(that.getId());
    }

}