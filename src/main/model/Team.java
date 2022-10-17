package model;

import java.util.ArrayList;
import java.util.List;

// Represents a team having a team name, a list of players, a list of all match results and an overall-rating of
// all players in the team list.
public class Team {
    private String teamName;
    private List<Player> team = new ArrayList<>();
    private List<String> results = new ArrayList<>();
    private int overAllRating;

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
        team.add(p);
    }


    public List<Player> getTeam() {
        return team;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public int getOverallRating() {
        this.overAllRating = getPlayersRatings();
        return this.overAllRating;
    }

    // EFFECTS: return a sum of all players' rating inside 'team'
    public int getPlayersRatings() {
        int rating = 0;
        for (int i = 0; i < team.size(); i++) {
            rating += team.get(i).getPlayerRating();
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

}
