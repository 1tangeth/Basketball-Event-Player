package ui;

import model.Event;
import model.Player;
import model.Team;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


// Basketball Event Application with Console User Interface
public class EventAPP {
    private static final String JSON_STORE_ONE = "./data/team1.json";
    private static final String JSON_STORE_TWO = "./data/team2.json";
    private JsonWriter jsonWriterOne;
    private JsonReader jsonReaderOne;
    private JsonWriter jsonWriterTwo;
    private JsonReader jsonReaderTwo;

    private Scanner input;
    private Team team1;
    private Team team2;
    private Player player;
    private Event event;
    private boolean keepGoing;
    private String eventName;

    // EFFECTS: constructs event and runs application
    public EventAPP() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriterOne = new JsonWriter(JSON_STORE_ONE);
        jsonWriterTwo = new JsonWriter(JSON_STORE_TWO);
        jsonReaderOne = new JsonReader(JSON_STORE_ONE);
        jsonReaderTwo = new JsonReader(JSON_STORE_TWO);
        runEvent();
    }

    // MODIFIES: this
    // EFFECTS: processes user input and compute the event
    public void runEvent() {
        keepGoing = true;
        String command;
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
                checkMatchHistory();
            } else if (command.equals("s")) {
                processTeamCreation(command);
                printResult();
            } else if (command.equals("e")) {
                playCurrentPlayers();
            } else if (command.equals("t")) {
                teamMenu(command);
            }
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("Select From: ");
        System.out.println("\t s ----> Start Match with New Players!");
        System.out.println("\t e ----> Start Match with Current Players!");
        System.out.println("\t t ----> My Teams");
        System.out.println("\t m ----> Match History");
        System.out.println("\t q ----> Quit");
    }

    private void playCurrentPlayers() {
        if (team1.getPlayers().isEmpty() && team2.getPlayers().isEmpty()) {
            System.out.println("There are no current players, please start a match with new players or load team");
        } else {
            event = new Event(team1, team2);
            event.setEventName(eventName);
            printResult();
        }
    }

    private void teamMenu(String command) {
        boolean keepGoing2 = true;
        while (keepGoing2) {
            teamInterface();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("v")) {
                if (team1.getPlayers().isEmpty() && team2.getPlayers().isEmpty()) {
                    System.out.println("No Teams have been created");
                    break;
                } else {
                    viewTeam();
                }
            } else if (command.equals("l")) {
                loadTeams();
            } else if (command.equals("s")) {
                saveTeams();
            } else if (command.equals("b")) {
                break;
            }
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveTeams() {
        try {
            jsonWriterOne.open();
            jsonWriterOne.write(team1);
            jsonWriterOne.close();
            System.out.println("Saved " + team1.getTeamName() + " to " + JSON_STORE_ONE);
            jsonWriterTwo.open();
            jsonWriterTwo.write(team2);
            jsonWriterTwo.close();
            System.out.println("Saved " + team2.getTeamName() + " to " + JSON_STORE_TWO);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_ONE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadTeams() {
        try {
            team1 = jsonReaderOne.read();
            System.out.println("Loaded " + team1.getTeamName() + " from " + JSON_STORE_ONE);
            team2 = jsonReaderTwo.read();
            System.out.println("Loaded " + team2.getTeamName() + " from " + JSON_STORE_TWO);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_ONE + "and" + JSON_STORE_TWO);
        }
    }

    //TODO Documentation
    private void viewTeam() {
        System.out.println("Team 1: ");
        for (Player p : team1.getPlayers()) {
            System.out.println("\nName: " + p.getPlayerName() + "\tRating: " + p.getPlayerRating());
        }
        System.out.println("Team 2: ");
        for (Player p : team2.getPlayers()) {
            System.out.println("\nName: " + p.getPlayerName() + "\tRating: " + p.getPlayerRating());
        }
    }

    //TODO Documentation
    private void teamInterface() {
        System.out.println("Select From: ");
        System.out.println("\t v ----> View Teams!");
        System.out.println("\t s ----> Save Teams!");
        System.out.println("\t l ----> Load Teams");
        System.out.println("\t b ----> Back to Menu");

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

    // MODIFIES: this
    // EFFECTS: process the team creation process
    private void processTeamCreation(String command) {
        if (command.equals("s")) {
            clearPlayers(team1,team2);
            addPlayerToTeams(team1);
            addPlayerToTeams(team2);
            System.out.println("Team Creation Complete!");
        }
    }

    private void clearPlayers(Team t1, Team t2) {
        t1.clearPlayer();
        t2.clearPlayer();
    }

    private void printResult() {
        System.out.println("Team One's Overall Rating:" + team1.getOverallRating());
        System.out.println("Team Two's Overall Rating:" + team2.getOverallRating());
        System.out.println(matchResult());
    }


    // MODIFIES: this
    // EFFECTS: process the match result
    private String matchResult() {
        return event.playMatch();
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
        String command;
        System.out.println("Start By Naming Your Basketball Event");
        eventName = input.next();
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
