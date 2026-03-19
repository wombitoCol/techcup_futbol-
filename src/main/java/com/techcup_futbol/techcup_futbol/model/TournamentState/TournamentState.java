package com.techcup_futbol.techcup_futbol.Tournament;

public interface TournamentState {

    void edit(Tournament tournament);

    void activate(Tournament tournament);

    void start(Tournament tournament);

    void finish(Tournament tournament);
}
