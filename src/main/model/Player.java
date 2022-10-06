package model;

public class Player {

    private String name;
    private int rating;
    private String position;

    public Player(String name, int rating, String position) {
        this.name = name;
        this.rating = rating;
        this.position = position;
    }

    public void addRating(int rating) {
        this.rating += rating;
    }

    public String getPlayerName() {
        return this.name;
    }

    public int getPlayerRating() {
        return this.rating;
    }

    public String getPlayerPosition() {
        return this.position;
    }




}
