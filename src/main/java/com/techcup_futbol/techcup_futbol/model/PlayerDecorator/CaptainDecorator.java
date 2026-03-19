package com.techcup_futbol.techcup_futbol.PlayerDecorator;

import java.util.List;
import com.techcup_futbol.techcup_futbol.model.Player; 

public class CaptainDecorator extends PlayerDecorator {

    public CaptainDecorator(PlayerComponent player) {
        super(player);
    }

    @Override
    public List<String> getActions() {
        return null; 
    }

    public void createTeam(String tournamentId, Object data) {
    }

    public void defineLineup(String matchId, Object lineup) {
    }
}