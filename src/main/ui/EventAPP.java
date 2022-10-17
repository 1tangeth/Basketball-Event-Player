package ui;

import model.Event;
import model.Player;
import model.Team;
import java.util.Scanner;

// Basketball Event Application with Console User Interface
public class EventAPP {

    private Scanner input;
    private Team team1;
    private Team team2;
    private Player player;
    private Event event;
    private boolean keepGoing;

    // run the basketball application
    public EventAPP() {
        runEvent();
    }

    // MODIFIES: this
    // EFFECTS: processes user input and compute the event
    public void runEvent() {
        keepGoing = true;
        String command = "";
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println("\nWelcome to Basketball Team Simulator");
        enterTeamName();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else if (command.equals("m")) {
                checkMatchHistory();;
            } else if (command.equals("s")) {
                processTeamCreation(command);
                playEvent(team1, team2);
                // skip a line
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: process match results
    private void checkMatchHistory() {
        if (team1.getResults().isEmpty()) {
            System.out.println("Team 1 has not played any games");
        } else {
            System.out.println("Here are Team 1 Match History: ");
            for (int i = 0; i < team1.getResults().size(); i++) {
                System.out.println(team1.getResults().get(i));
            }
        }

        if (team2.getResults().isEmpty()) {
            System.out.println("Team 2 has not played any games");
        } else {
            System.out.println("Here are Team 2 Match History: ");
            for (int i = 0; i < team2.getResults().size(); i++) {
                System.out.println(team2.getResults().get(i));
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Select From: ");
        System.out.println("\t s ----> Start Match!");
        System.out.println("\t m ----> Match History");
        System.out.println("\t q ----> Quit");
    }

    // MODIFIES: this
    // EFFECTS: process the team creation process
    private void processTeamCreation(String command) {
        if (command.equals("s")) {
            addPlayerToTeams(team1);
            addPlayerToTeams(team2);
            System.out.println("\nTeam Creation Complete! ");
        }
    }

    // MODIFIES: this
    // EFFECTS: process the match result
    private void playEvent(Team t1, Team t2) {
        System.out.println(event.playMatch());
    }

    // MODIFIES: this
    // EFFECTS: read user command and add players to a team
    private void addPlayerToTeams(Team team) {
        System.out.println("\nPlease Add 5 Players to " + team.getTeamName());
        for (int i = 1; i < 6; i++) {
            System.out.println("Enter Player " + i + "'s Name");
            addPlayerInfo(team);
        }
    }

    // MODIFIES: this
    // EFFECTS: add Player's name and create Player to Team
    private void addPlayerInfo(Team team) {
        String name = input.next();
        player = new Player(name);
        System.out.println(name + "'s skill rating is: " + player.getPlayerRating());
        team.addPlayer(player);
    }

    // MODIFIES: this
    // EFFECTS: Construct the Team information
    private void enterTeamName() {
        String command = null;
        System.out.println("Start By Naming Your Basketball Event");
        String eventName = input.next();
        System.out.println("\nNow name each team participating in " + eventName);
        System.out.println("Team 1:");
        command = input.next();
        team1 = new Team(command);
        System.out.println("Team 2:");
        command = input.next();
        team2 = new Team(command);
        event = new Event(team1, team2);
        event.setEventName(eventName);
    }


}
