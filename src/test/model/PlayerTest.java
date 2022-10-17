package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    Player testPlayer;

    @BeforeEach
    void runBefore() {
        testPlayer = new Player("Ethan");
    }

    @Test
    void testConstructor() {
        assertEquals("Ethan", testPlayer.getPlayerName());
    }

    @Test
    void testRandomRatingRange(){
        // Test the range between [0-100]
        assertTrue(testPlayer.getPlayerRating() <= 100);
        assertTrue(testPlayer.getPlayerRating() > 0);
    }

    @Test
    void testAddRating(){
        assertEquals(testPlayer.getPlayerRating() + 100, testPlayer.addRating(100));
    }


}