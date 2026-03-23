package com.techcup_futbol.techcup_futbol.model.Notification;

public interface ObservableSubject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers();
    void update(); 
}