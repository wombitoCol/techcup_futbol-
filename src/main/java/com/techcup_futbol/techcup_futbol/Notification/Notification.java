package com.techcup_futbol.techcup_futbol.Notification;
import java.time.LocalDateTime;

public class Notification  {

    private String id;
    private String message;
    private LocalDateTime date;

    public Notification(String id, String message, LocalDateTime date) {
        this.id = id;
        this.message = message;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}