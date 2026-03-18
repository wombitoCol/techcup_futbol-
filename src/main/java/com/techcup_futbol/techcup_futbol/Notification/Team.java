package com.techcup_futbol.techcup_futbol.Notification;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Team implements ObservableSubject,Observer{


    private String id;
    private ArrayList<Observer> subscribers;

    

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
