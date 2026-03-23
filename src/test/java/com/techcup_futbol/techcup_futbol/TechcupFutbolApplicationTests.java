/* 
package com.techcup_futbol.techcup_futbol;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.techcup_futbol.techcup_futbol.Notification.Team;
import com.techcup_futbol.techcup_futbol.Tournament.TournamentLightning;
import com.techcup_futbol.techcup_futbol.Tournament.TournamentLightningBuilder;

@SpringBootTest
class TechcupFutbolApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void ShouldNotifyPlayer(){

	}

	public void shouldSubscribeTeamWhenItIsAdded(){
		TournamentLightningBuilder builder = new TournamentLightningBuilder();
		TournamentLightning tournament = builder.id("1").startDate(LocalDateTime.of(2026, 4, 15, 10, 0)).finishDate(LocalDateTime.of(2026, 5, 15, 10, 0))
		.finishDate(LocalDateTime.of(2026, 4, 10, 10, 0)).costPerTeam(100000).build();

		Team team = new Team("1");

		tournament.addTeam(team);

		Team teamSubscribed = tournament.getTeamSubscribed("1");

		assertEquals(team, teamSubscribed);
	}

	public void teamShouldKeepBeingRegisteredWhenItUnsubscribes(){
		TournamentLightningBuilder builder = new TournamentLightningBuilder();
		TournamentLightning tournament = builder.id("1").startDate(LocalDateTime.of(2026, 4, 15, 10, 0)).finishDate(LocalDateTime.of(2026, 5, 15, 10, 0))
		.finishDate(LocalDateTime.of(2026, 4, 10, 10, 0)).costPerTeam(100000).build();

		Team team = new Team("1");

		tournament.addTeam(team);
		tournament.unsubscribe(team);

		Team teamSubscribed = tournament.getTeamSubscribed("1");
		Team teamVigent = tournament.getTeam(team.getId()); 

		assertNull(teamSubscribed);
		assertEquals(team, teamVigent);
	}
}
*/
