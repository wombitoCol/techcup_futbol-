package com.techcup_futbol.techcup_futbol.model;

public class TournamentBracket {
    private String stage; // Ejemplo: "Round of 16" (8vos), "Quarterfinals" (4tos)
    private TournamentTeam team1;
    private TournamentTeam team2;

    // Empty constructor
    public TournamentBracket() {}

    // Constructor
    public TournamentBracket(String stage, TournamentTeam team1, TournamentTeam team2) {
        this.stage = stage;
        this.team1 = team1;
        this.team2 = team2;
    }

    // Getters and Setters
    public String getStage() { return stage; }
    public void setStage(String stage) { this.stage = stage; }

    public TournamentTeam getTeam1() { return team1; }
    public void setTeam1(TournamentTeam team1) { this.team1 = team1; }

    public TournamentTeam getTeam2() { return team2; }
    public void setTeam2(TournamentTeam team2) { this.team2 = team2; }
}