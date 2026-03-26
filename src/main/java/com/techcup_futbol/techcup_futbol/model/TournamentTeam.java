package com.techcup_futbol.techcup_futbol.model;

public class TournamentTeam {
    private Long id;
    private String name;
    private double points;
    private int goalsFor;
    private int goalsAgainst;
    private double fairPlayPoints; 

    public TournamentTeam() {}

    public TournamentTeam(Long id, String name, double points, int goalsFor, int goalsAgainst, double fairPlayPoints) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.fairPlayPoints = fairPlayPoints;
    }

    public double getPoints() { return points; }
    public int getGoalDifference() { return goalsFor - goalsAgainst; }
    public int getGoalsFor() { return goalsFor; } 
    public double getFairPlayPoints() { return fairPlayPoints; } 

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void setGoalsFor(int goalsFor) { this.goalsFor = goalsFor; }
    public int getGoalsAgainst() { return goalsAgainst; }
    public void setGoalsAgainst(int goalsAgainst) { this.goalsAgainst = goalsAgainst; }
    public void setFairPlayPoints(double fairPlayPoints) { this.fairPlayPoints = fairPlayPoints; }
}