package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamTest {
    private Team testTeam;
    private Player player1;
    private Player player2;

    @BeforeEach
    void runBefore() {
        testTeam = new Team("Lakers");
        player1 = new Player("Ethan");
        player2 = new Player("Kobe");
    }

    @Test
    void testConstructor() {
        assertEquals("Lakers", testTeam.getTeamName());
        assertEquals(0, testTeam.getOverallRating());
    }

    @Test
    void testAddPlayer(){
        testTeam.addPlayer(player1);
        testTeam.addPlayer(player2);
        assertEquals(2, testTeam.getTeam().size());
        assertEquals(player1, testTeam.getTeam().get(0));
        assertEquals(player2, testTeam.getTeam().get(1));

    }

    @Test
    void testGetOverallRating(){
        testTeam.addPlayer(player1);
        testTeam.addPlayer(player2);
        int rating = player1.getPlayerRating() + player2.getPlayerRating();
        assertEquals(rating, testTeam.getOverallRating());
    }

    @Test
    void testAddResult(){
        testTeam.addResult("Team 1 defeat Team 2");
        testTeam.addResult("Team 1 defeat Team 2");
        testTeam.addResult("Team 2 defeat Team 1");

        assertEquals(3, testTeam.getResults().size());
        assertEquals("Team 1 defeat Team 2", testTeam.getResults().get(0));
        assertEquals("Team 1 defeat Team 2", testTeam.getResults().get(1));
        assertEquals("Team 2 defeat Team 1", testTeam.getResults().get(2));
    }


}