package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {
    private Game testGame;
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
        testGame = new Game(team1, team2);
    }

    @Test
    void testConstructor() {
        assertEquals(team1, testGame.getTeam1());
        assertEquals(team2, testGame.getTeam2());
        assertEquals("", testGame.getEventName());
    }

    @Test
    void testSetEventName() {
        testGame.setEventName("NBA");
        assertEquals("NBA", testGame.getEventName());
    }

    @Test
    void testPlayMatchTeamOneWin() {
        team1.addPlayer(p1);
        assertEquals("Team 1 won", testGame.playMatch());
        String result;
        result = team1.getTeamName() + " won against " + team2.getTeamName() + " in " + testGame.getEventName();
        assertEquals(result, team1.getResults().get(0));
        result = team2.getTeamName() + " lost to " + team1.getTeamName() + " in " + testGame.getEventName();
        assertEquals(result, team2.getResults().get(0));
    }

    @Test
    void testPlayMatchTeamTwoWin() {
        team2.addPlayer(p1);
        team2.addPlayer(p2);
        assertEquals("Team 2 won", testGame.playMatch());
        String result;
        result = team2.getTeamName() + " won against " + team1.getTeamName() + " in " + testGame.getEventName();
        assertEquals(result, team2.getResults().get(0));
        result = team1.getTeamName() + " lost to " + team2.getTeamName() + " in " + testGame.getEventName();
        assertEquals(result, team1.getResults().get(0));
    }

    @Test
    void testPlayMatchTie() {
        team1.addPlayer(p1);
        team2.addPlayer(p1);
        assertEquals("Team 1 and Team 2 tied", testGame.playMatch());
        String result;
        result = team2.getTeamName() + " tie to " + team1.getTeamName() + " in " + testGame.getEventName();
        assertEquals(result, team2.getResults().get(0));
        result = team1.getTeamName() + " tie to " + team2.getTeamName() + " in " + testGame.getEventName();
        assertEquals(result, team1.getResults().get(0));
    }
}