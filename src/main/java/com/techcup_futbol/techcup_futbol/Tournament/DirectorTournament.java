package com.techcup_futbol.techcup_futbol.Tournament;
import com.techcup_futbol.techcup_futbol.Tournament.TournamentBuilder;

public class DirectorTournament {
    
    public void buildTournament1(TournamentBuilder builder){
        builder.costPerTeam(100000)
               .description("Torneo relampago");
    }


}
