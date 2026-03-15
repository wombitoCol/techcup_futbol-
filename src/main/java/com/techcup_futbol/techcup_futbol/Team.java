package com.techcup_futbol.techcup_futbol;

import java.time.LocalDateTime;
import java.util.List;

public class Team implements ObservableSubject,Observer{

    private List<Observer> subscribers;

    

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
}
