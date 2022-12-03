package model;

import org.json.JSONArray;
import org.json.JSONObject;
import model.persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a team having a team name, a list of players, a list of all match results and an overall-rating of
// all players in the team list.
public class Team implements Writable {
    private String teamName;
    private List<Player> players = new ArrayList<>();
    private List<String> results = new ArrayList<>();
    private int overAllRating;
    private Event event;

    // REQUIRES: teamName has non-zero length
    // EFFECTS: name of the team is set to teamName; the overall-prating of the team is initialized to zero and
    //          set to overAllRating
    public Team(String teamName) {
        this.teamName = teamName;
        this.overAllRating = 0;
    }

    // MODIFIES: this
    // EFFECTS: add a Player object to the initialized list of Players.
    public void addPlayer(Player p) {
        if (this.players.size() < 5) {
            players.add(p);
            event = new Event("Player " + p.getPlayerName() + " is added to the team");
            EventLog.getInstance().logEvent(event);
        }
    }


    public List<Player> getPlayers() {
        return players;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public int getOverallRating() {
        this.overAllRating = getPlayersRatings();
        return this.overAllRating;
    }

    // MODIFIES: this
    // EFFECTS: remove all players inside the player list
    public void clearPlayer() {
        this.players.clear();
    }


    // EFFECTS: return a sum of all players' rating inside 'team'
    public int getPlayersRatings() {
        int rating = 0;
        for (int i = 0; i < players.size(); i++) {
            rating += players.get(i).getPlayerRating();
        }
        return rating;
    }

    // MODIFIES: this
    // EFFECTS: add a String of result to the list of results
    public void addResult(String result) {
        this.results.add(result);
    }

    public List<String> getResults() {
        return this.results;
    }

    // EFFECTS: team is serialized into JSON, return the Json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("team name", teamName);
        json.put("players", playerToTeam());
        return json;
    }

    // EFFECTS: players in the team are serialized into JSON, return the Json object
    private JSONArray playerToTeam() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : players) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
