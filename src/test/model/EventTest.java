package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {
    private Event testEvent;
    private Team team1;
    private Team team2;
    private Player p1;
    private Player p2;

    @BeforeEach
    void runBefore() {
        team1 = new Team("Lakers");
        team2 = new Team("Red Bulls");
        p1 = new Player("Kobe");
        p2 = new Player("Michael");
        testEvent = new Event(team1, team2);
    }

    @Test
    void testConstructor() {
        assertEquals(team1, testEvent.getTeam1());
        assertEquals(team2, testEvent.getTeam2());
        assertEquals("", testEvent.getEventName());
    }

    @Test
    void testSetEventName() {
        testEvent.setEventName("NBA");
        assertEquals("NBA", testEvent.getEventName());
    }

    @Test
    void testPlayMatchTeamOneWin() {
        team1.addPlayer(p1);
        assertEquals("Team 1 won", testEvent.playMatch());
        String result;
        result = team1.getTeamName() + " won against " + team2.getTeamName() + " in " + testEvent.getEventName();
        assertEquals(result, team1.getResults().get(0));
        result = team2.getTeamName() + " lost to " + team1.getTeamName() + " in " + testEvent.getEventName();
        assertEquals(result, team2.getResults().get(0));
    }

    @Test
    void testPlayMatchTeamTwoWin() {
        team2.addPlayer(p1);
        team2.addPlayer(p2);
        assertEquals("Team 2 won", testEvent.playMatch());
        String result;
        result = team2.getTeamName() + " won against " + team1.getTeamName() + " in " + testEvent.getEventName();
        assertEquals(result, team2.getResults().get(0));
        result = team1.getTeamName() + " lost to " + team2.getTeamName() + " in " + testEvent.getEventName();
        assertEquals(result, team1.getResults().get(0));
    }

    @Test
    void testPlayMatchTie() {
        team1.addPlayer(p1);
        team2.addPlayer(p1);
        assertEquals("Team 1 and Team 2 tied", testEvent.playMatch());
        String result;
        result = team2.getTeamName() + " tie to " + team1.getTeamName() + " in " + testEvent.getEventName();
        assertEquals(result, team2.getResults().get(0));
        result = team1.getTeamName() + " tie to " + team2.getTeamName() + " in " + testEvent.getEventName();
        assertEquals(result, team1.getResults().get(0));
    }
}