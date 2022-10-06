package model;

public class Event {
    private Team team1;
    private Team team2;
    private String eventName;

    public Event(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void playMatch() {
        if (team1.getOverallRating() < team2.getOverallRating()) {
            team1.addResult(team1.getTeamName() + " lost to " + team2.getTeamName() + " in " + this.eventName);
            team2.addResult(team2.getTeamName() + " won against " + team1.getTeamName() + " in " + this.eventName);
        } else if (team1.getOverallRating() > team2.getOverallRating()) {
            team1.addResult(team1.getTeamName() + " won against " + team2.getTeamName() + " in " + this.eventName);
            team2.addResult(team2.getTeamName() + " lost to " + team1.getTeamName() + " in " + this.eventName);
        } else {
            // randomize result because tie
        }
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
