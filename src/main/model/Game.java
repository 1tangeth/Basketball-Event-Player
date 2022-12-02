package model;

// Represents an Game having two teams and event name
public class Game {
    private Team team1;
    private Team team2;
    private String eventName;

    // EFFECTS: Set the teams to team1 and team2, initialize the event name to an empty string and set to eventName
    public Game(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
        eventName = "";
    }

    // EFFECTS: Return string results of the match base on 3 cases:
    //          1. team1's sum of rating < team2's sum of rating, then return "Team 2 wins"
    //          2. team1's sum of rating > team2's sum of rating, then return "Team 1 wins"
    //          3. team1's sum of rating = team2's sum of rating, then return "Team1 and Team 2 tied"
    public String playMatch() {
        if (team1.getOverallRating() < team2.getOverallRating()) {
            Event x = new Event("Match is played: Team Two Win");
            EventLog.getInstance().logEvent(x);
            return teamTwoWin();
        } else if (team1.getOverallRating() > team2.getOverallRating()) {
            Event x = new Event("Match is played: Team One Win");
            EventLog.getInstance().logEvent(x);
            return teamOneWin();
        } else {
            Event x = new Event("Match is played: Tie");
            EventLog.getInstance().logEvent(x);
            return tie();
        }
    }

    // EFFECTS: return the result message of team1 ties to team2
    private String tie() {
        addResult(team1, team2, " tie to ");
        addResult(team2, team1, " tie to ");
        return "Team 1 and Team 2 tied";
    }

    // EFFECT: return the result message of team 1 winning
    private String teamOneWin() {
        addResult(team1, team2, " won against ");
        addResult(team2, team1, " lost to ");
        return "Team 1 won";
    }

    // EFFECT: return the result message of team 2 winning
    private String teamTwoWin() {
        addResult(team2, team1, " won against ");
        addResult(team1, team2, " lost to ");
        return "Team 2 won";
    }

    // MODIFIES: Team
    // EFFECTS: add the result message to the list of result in Team Class
    private void addResult(Team team1, Team team2, String result) {
        team1.addResult(team1.getTeamName() + result + team2.getTeamName() + " in " + this.eventName);
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }
}
