package persistence;

import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Team tm = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTeam.json");
        try {
            Team tm = reader.read();
            assertEquals("My Team", tm.getTeamName());
            assertEquals(0, tm.getPlayers().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTeam() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTeam.json");
        try {
            Team tm = reader.read();
            assertEquals("Lakers", tm.getTeamName());
            List<Player> players = tm.getPlayers();
            assertEquals(3, players.size());
            checkPlayer("Kobe Bryant", 54, players.get(0));
            checkPlayer("Lebron James", 83, players.get(1));
            checkPlayer("Tony Skr", 91, players.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }






}
