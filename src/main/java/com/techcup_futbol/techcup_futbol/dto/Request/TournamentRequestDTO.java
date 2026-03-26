package com.techcup_futbol.techcup_futbol.dto.Request;
import java.time.LocalDateTime;

public class TournamentRequestDTO {

    private String name;
    private LocalDateTime startDate;
    private LocalDateTime finishDateToRegister;
    private double costPerTeam;
    private String description;

    // void constructor
    public TournamentRequestDTO() {}

    // getters
    public String getName() {
        return name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getFinishDateToRegister() {
        return finishDateToRegister;
    }

    public double getCostPerTeam() {
        return costPerTeam;
    }

    public String getDescription() {
        return description;
    }

    // setters 
    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setFinishDateToRegister(LocalDateTime finishDateToRegister) {
        this.finishDateToRegister = finishDateToRegister;
    }

    public void setCostPerTeam(double costPerTeam) {
        this.costPerTeam = costPerTeam;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}