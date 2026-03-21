package com.techcup_futbol.techcup_futbol.model.Tournament;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import com.techcup_futbol.techcup_futbol.model.Notification.ObservableSubject;
import com.techcup_futbol.techcup_futbol.model.Notification.Observer;

public class Team implements ObservableSubject,Observer{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToMany(mappedBy = "teams")
    private ArrayList<Tournament> tournaments;

    @ManyToMany
    private ArrayList<Observer> subscribers;
    
    @NotBlank
    @Column(nullable = false)
    private String name;



    

    public Team(String id){
        this.id = id;
        this.subscribers = new ArrayList<>();
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

    public void update(){
        subscribers.stream().forEach(s -> s.update());
    }

    public String getId() {
        return id;
    }

    public String setId(String newId) {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team that = (Team) o;

        return id.equals(that.getId());
    }
}
