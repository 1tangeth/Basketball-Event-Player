package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Player;
import model.Team;
import org.json.*;

// Represents a reader that reads team from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Team from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Team read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeam(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    // EFFECTS: parses team from JSON object and returns it
    private Team parseTeam(JSONObject jsonObject) {
        String name = jsonObject.getString("team name");
        Team wr = new Team(name);
        addPlayers(wr, jsonObject);
        return wr;
    }

    // MODIFIES: tm
    // EFFECTS: parses players from JSON object and adds them to team
    private void addPlayers(Team tm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("players");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addPlayer(tm, nextThingy);
        }
    }

    // MODIFIES: tm
    // EFFECTS: parses player from JSON object and adds it to team
    private void addPlayer(Team tm, JSONObject jsonObject) {
        String name = jsonObject.getString("player name");
        int rating = jsonObject.getInt("rating");
        Player p = new Player(name);
        p.setRating(rating);
        tm.addPlayer(p);
    }
}