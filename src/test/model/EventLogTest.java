package model;

import model.persistence.JsonReader;
import model.persistence.JsonWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventLogTest {
    private Team testTeam;
    private Team testTeam2;
    private Game testGame;
    private Player testPlayer;

    @BeforeEach
    void setUp() {
        EventLog.getInstance().clear();
        testTeam = new Team("team 1");
        testTeam2 = new Team("team 2");
        testGame = new Game(testTeam,testTeam2);
        testPlayer = new Player("Ethan", 12);
    }

    @Test
    void createAddPlayerEventTest() {
        testTeam.addPlayer(testPlayer);
        EventLog log = EventLog.getInstance();
        Iterator<Event> e = log.iterator();
        assertEquals("Event log cleared.", e.next().getDescription());
        assertEquals("Player Ethan is created with rating 12", e.next().getDescription());
        assertEquals("Player Ethan is added to the team", e.next().getDescription());
    }

    @Test
    void playMatchTieEventTest() {
        testGame.playMatch();
        EventLog log = EventLog.getInstance();
        Iterator<Event> e = log.iterator();
        assertEquals("Event log cleared.", e.next().getDescription());
        assertEquals("Player Ethan is created with rating 12", e.next().getDescription());
        assertEquals("Match is played: Tie", e.next().getDescription());
    }

    @Test
    void playMatchT1WinEventTest() {
        testTeam.addPlayer(testPlayer);
        testGame.playMatch();
        EventLog log = EventLog.getInstance();
        Iterator<Event> e = log.iterator();
        assertEquals("Event log cleared.", e.next().getDescription());
        assertEquals("Player Ethan is created with rating 12", e.next().getDescription());
        assertEquals("Player Ethan is added to the team", e.next().getDescription());
        assertEquals("Match is played: Team One Win", e.next().getDescription());
    }

    @Test
    void playMatchT2WinEventTest() {
        testTeam2.addPlayer(testPlayer);
        testGame.playMatch();
        EventLog log = EventLog.getInstance();
        Iterator<Event> e = log.iterator();
        assertEquals("Event log cleared.", e.next().getDescription());
        assertEquals("Player Ethan is created with rating 12", e.next().getDescription());
        assertEquals("Player Ethan is added to the team", e.next().getDescription());
        assertEquals("Match is played: Team Two Win", e.next().getDescription());
    }

    @Test
    void loadTeamEventTest() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTeam.json");
        try {
            Team tm = reader.read("team 1");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        EventLog log = EventLog.getInstance();
        Iterator<Event> e = log.iterator();
        assertEquals("Event log cleared.", e.next().getDescription());
        assertEquals("Player Ethan is created with rating 12", e.next().getDescription());
        assertEquals("File loaded for team 1", e.next().getDescription());
    }

    @Test
    void saveTeamEventTest() {
        try {
            Team tm = new Team("My Team");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTeam.json");
            writer.open();
            writer.write(tm);
            writer.close();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

        EventLog log = EventLog.getInstance();
        Iterator<Event> e = log.iterator();
        assertEquals("Event log cleared.", e.next().getDescription());
        assertEquals("Player Ethan is created with rating 12", e.next().getDescription());
        assertEquals("File saved for My Team", e.next().getDescription());
    }







}
