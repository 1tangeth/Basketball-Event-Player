package model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private List<Player> team = new ArrayList<>();
    private List<String> results = new ArrayList<>();

    public Team(String teamName, List<Player> team) {
        this.teamName = teamName;
        this.team = team;
    }

    public String getTeamName() {
        return this.teamName;
    }

    public int getOverallRating() {
        int overallRating = 0;
        for (int i = 0; i < team.size(); i++) {
            overallRating += team.get(i).getPlayerRating();
        }
        return overallRating;
    }

    public void addResult(String result) {
        this.results.add(result);
    }

    public List<String> getResults() {
        return this.results;
    }

}
