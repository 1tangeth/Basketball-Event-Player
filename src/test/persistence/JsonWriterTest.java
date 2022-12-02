package persistence;

import model.Player;
import model.Team;
import model.persistence.JsonReader;
import model.persistence.JsonWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Team tm = new Team("My Team");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTeam() {
        try {
            Team tm = new Team("My Team");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTeam.json");
            writer.open();
            writer.write(tm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTeam.json");
            tm = reader.read("team 1");
            assertEquals("My Team", tm.getTeamName());
            assertEquals(0, tm.getPlayers().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Team tm = new Team("Lakers");
            Player lebron = new Player("Lebron James");
            lebron.setRating(30);
            tm.addPlayer(lebron);
            Player kobe = new Player("Kobe Bryant");
            kobe.setRating(40);
            tm.addPlayer(kobe);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTeam.json");
            writer.open();
            writer.write(tm);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTeam.json");
            tm = reader.read("team 1");
            assertEquals("Lakers", tm.getTeamName());
            List<Player> players = tm.getPlayers();
            assertEquals(2, players.size());
            checkPlayer("Lebron James", 30, players.get(0));
            checkPlayer("Kobe Bryant", 40, players.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }





}
