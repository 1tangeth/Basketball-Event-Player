package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.Random;

// Represents a Player having a player name and player's individual rating
public class Player implements Writable {

    private String name;
    private int rating;

    // REQUIRES: name has non-zero length
    // EFFECTS: name of the player is set to name; initialize the rating of the player to a random value
    public Player(String name) {
        this.name = name;
        setRandomRating();
    }

    // REQUIRES: rating > 0
    // MODIFIES: this
    // EFFECTS: value is added to the existing player rating and returned
    public int addRating(int rating) {
        this.rating += rating;
        return this.rating;
    }

    // EFFECTS: set player's rating  to a randomized integer from [1,100]
    public void setRandomRating() {
        Random random = new Random();
        int randomRating = random.nextInt(100) + 1;
        this.rating = randomRating;
    }

    public String getPlayerName() {
        return this.name;
    }

    public void setRating(int r) {
        this.rating = r;
    }

    public int getPlayerRating() {
        return this.rating;
    }

    // EFFECTS: Player object's name and associated rating is serialized into json, return the json object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("player name", name);
        json.put("rating", rating);
        return json;
    }
}
