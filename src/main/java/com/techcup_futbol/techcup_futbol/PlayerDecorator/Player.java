package com.techcup_futbol.techcup_futbol.PlayerDecorator;

import java.util.List;

public class Player implements PlayerComponent {

    private User user;
    private SportsProfile sportsProfile;
    private boolean available;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public List<String> getActions() {
        return null;
    }

    @Override
    public boolean canPerformAction(String action) {
        return false;
    }

}
