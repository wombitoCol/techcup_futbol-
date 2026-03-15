package com.techcup_futbol.techcup_futbol;

import java.time.LocalDateTime;
import java.util.List;

public class TournamentLightningBuilder implements TournamentBuilder {
    
    private String id;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private LocalDateTime finishDateToRegister;
    private LocalDateTime importantDates;
    private double costPerTeam;
    private String description;

    public TournamentLightning build(){
        return new TournamentLightning(id, startDate, finishDate, finishDateToRegister, importantDates,
            costPerTeam, description);
    } 

    public TournamentLightningBuilder id(String id){
        this.id = id;
        return this;
    } 

    public TournamentLightningBuilder startDate(LocalDateTime startDate){
        this.startDate = startDate;
        return this;
    } 

    public TournamentLightningBuilder finishDate(LocalDateTime finishDate){
        this.finishDate = finishDate;
        return this;
    } 

    public TournamentLightningBuilder finishDateToRegister(LocalDateTime finishDateToRegister){
        this.finishDateToRegister = finishDateToRegister;
        return this;
    } 

    public TournamentLightningBuilder importantDates(LocalDateTime importantDates){
        this.importantDates = importantDates;
        return this;
    } 

    public TournamentLightningBuilder costPerTeam(double costPerTeam){
        this.costPerTeam = costPerTeam;
        return this;
    } 

    public TournamentLightningBuilder description(String description){
        this.description = description;
        return this;
    } 


}
