package com.techcup_futbol.techcup_futbol.testTournament;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.techcup_futbol.techcup_futbol.Tournament.Active;
import com.techcup_futbol.techcup_futbol.Tournament.Draft;
import com.techcup_futbol.techcup_futbol.Tournament.InProgress;
import com.techcup_futbol.techcup_futbol.Tournament.Tournament;
import com.techcup_futbol.techcup_futbol.Tournament.TournamentState;

public class TournamentStateIntegrationTest {

    @Test
    void shouldInitializeTournamentWithoutState() {
        Tournament tournament = new Tournament();
        assertNull(tournament.getState());
    }

    @Test
    void shouldAssignDraftStateToTournament() {
        Tournament tournament = new Tournament();
        TournamentState state = new Draft();

        tournament.changeState(state);

        assertNotNull(tournament);
    }

    @Test
    void shouldCallEditOnDraftState() {
        Tournament tournament = new Tournament();
        TournamentState state = new Draft();

        state.edit(tournament);

        assertNotNull(tournament);
    }

    @Test
    void shouldCallStartOnActiveState() {
        Tournament tournament = new Tournament();
        TournamentState state = new Active();

        state.start(tournament);

        assertNotNull(tournament);
    }

    @Test
    void shouldCallFinishOnInProgressState() {
        Tournament tournament = new Tournament();
        TournamentState state = new InProgress();

        state.finish(tournament);

        assertNotNull(tournament);
    }
}