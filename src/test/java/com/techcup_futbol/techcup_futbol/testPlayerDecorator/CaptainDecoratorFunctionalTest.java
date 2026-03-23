/* 
package com.techcup_futbol.techcup_futbol.testPlayerDecorator;

import com.techcup_futbol.techcup_futbol.PlayerDecorator.CaptainDecorator;
import com.techcup_futbol.techcup_futbol.PlayerDecorator.Player;
import com.techcup_futbol.techcup_futbol.PlayerDecorator.PlayerComponent;
import com.techcup_futbol.techcup_futbol.PlayerDecorator.PlayerDecorator;
import com.techcup_futbol.techcup_futbol.PlayerDecorator.SportsProfile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class CaptainDecoratorFunctionalTest {

    @Test
    void shouldCreatePlayer() {
        Player player = new Player();
        assertNotNull(player);
    }

    @Test
    void shouldWrapPlayerWithCaptainDecorator() {
        PlayerComponent player = new Player();
        PlayerComponent captain = new CaptainDecorator(player);

        assertNotNull(captain);
    }

    @Test
    void decoratorShouldDelegateGetName() {
        PlayerComponent player = new Player();
        PlayerDecorator decorator = new CaptainDecorator(player);

        assertDoesNotThrow(() -> {
            decorator.getName();
        });
    }

    @Test
    void captainShouldReturnActionsList() {
        PlayerComponent player = new Player();
        CaptainDecorator captain = new CaptainDecorator(player);

        assertDoesNotThrow(() -> {
            List<String> actions = captain.getActions();
        });
    }

    @Test
    void shouldCheckIfActionCanBePerformed() {
        PlayerComponent player = new Player();
        PlayerComponent captain = new CaptainDecorator(player);

        assertDoesNotThrow(() -> {
            captain.canPerformAction("create_team");
        });
    }

    @Test
    void captainShouldAllowCreateTeamCall() {
        PlayerComponent player = new Player();
        CaptainDecorator captain = new CaptainDecorator(player);

        assertDoesNotThrow(() -> {
            captain.createTeam("tournament1", new Object());
        });
    }

    @Test
    void captainShouldAllowDefineLineupCall() {
        PlayerComponent player = new Player();
        CaptainDecorator captain = new CaptainDecorator(player);

        assertDoesNotThrow(() -> {
            captain.defineLineup("match1", new Object());
        });
    }

    @Test
    void shouldCreateSportsProfile() {
        SportsProfile profile = new SportsProfile();
        assertNotNull(profile);
    }
}
*/