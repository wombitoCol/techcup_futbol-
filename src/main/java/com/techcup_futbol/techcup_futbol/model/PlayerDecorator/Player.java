/*package com.techcup_futbol.techcup_futbol.model.PlayerDecorator;

import java.util.List;
import com.techcup_futbol.techcup_futbol.model.User.User;
import com.techcup_futbol.techcup_futbol.model.User.UserType;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@Entity
@Table(name = "players") // Es buena práctica darle nombre a la tabla hija en la BD
public class Player extends User implements PlayerComponent {

    private SportsProfile sportsProfile;
    
    private boolean available;


    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public List<String> getActions() {
        return null; 
    }

    @Override
    public boolean canPerformAction(String action) {
        return false; 
    }

    @Override
    public UserType getAffiliationType() {
        return UserType.STUDENT;
    }

    public SportsProfile getSportsProfile() {
        return sportsProfile;
    }

    public void setSportsProfile(SportsProfile sportsProfile) {
        this.sportsProfile = sportsProfile;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
*/