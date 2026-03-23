package com.techcup_futbol.techcup_futbol.model.Tournament;

import java.util.ArrayList;
import java.util.List;

import com.techcup_futbol.techcup_futbol.model.Notification.ObservableSubject;
import com.techcup_futbol.techcup_futbol.model.Notification.Observer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "teams")
public class Team implements ObservableSubject, Observer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;
    
    // Esta relación la manejas con Tournament (no la modifiques)
    @ManyToMany(mappedBy = "teams")
    private List<Tournament> tournaments = new ArrayList<>();
    
    // IMPORTANTE: @Transient evita que JPA intente guardar esto en BD
    @Transient
    private List<Observer> subscribers = new ArrayList<>();
    
    public Team() {}
    
    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Métodos de Observer
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
        subscribers.forEach(Observer::update);
    }
    
    @Override
    public void update() {
        notifyObservers();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public List<Tournament> getTournaments() { return tournaments; }
    public void setTournaments(List<Tournament> tournaments) { this.tournaments = tournaments; }
    
    public List<Observer> getSubscribers() { return subscribers; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return id != null && id.equals(team.id);
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}