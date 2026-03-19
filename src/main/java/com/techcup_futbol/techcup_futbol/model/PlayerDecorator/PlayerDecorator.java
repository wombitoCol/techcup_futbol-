package com.techcup_futbol.techcup_futbol.PlayerDecorator;

import java.util.List;

public abstract class PlayerDecorator implements PlayerComponent {

    protected PlayerComponent player;

    public PlayerDecorator(PlayerComponent player) {
        this.player = player;
    }

    @Override
    public String getName() {
        return player.getName();
    }

    @Override
    public List<String> getActions() {
        return player.getActions();
    }

    @Override
    public boolean canPerformAction(String action) {
        return player.canPerformAction(action);
    }
}