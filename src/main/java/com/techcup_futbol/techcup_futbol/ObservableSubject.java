package com.techcup_futbol.techcup_futbol;

import java.time.LocalDateTime;


public interface ObservableSubject {
    void subscribe(Observer observer);
    void unsubscribe(Observer observer);
    void notifyObservers();
}