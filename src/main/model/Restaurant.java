package model;

// class-level comment?

public class Restaurant {
    private String name;
    private int rating;
    private String cuisine;
    private String pricing;
    // open hours field

    // EFFECTS: create a new restaurant with 0 rating, no cuisine, and no pricing
    public Restaurant(String name) {
        this.name = name;
        this.rating = 0;
        this.cuisine = "";
        this.pricing = "";
    }

    // REQUIRES: [0,10]
    // MODIFIES: this
    // EFFECTS: sets object's rating to be given rating
    public void setRating(int r) {
        rating = r;
    }

    // MODIFIES: this
    // EFFECTS: sets object's cuisine to be given cuisine
    public void setCuisine(String c) {
        cuisine = c;
    }

    // REQUIRES: one of $, $$, $$$, $$$$
    // MODIFIES: this
    // EFFECTS: sets object's pricing to be given pricing
    public void setPricing(String p) {
        pricing = p;
    }

    // EFFECTS: get rating of this
    public int getRating() {
        return rating;
    }

    // EFFECTS: get cuisine of this
    public String getCuisine() {
        return cuisine;
    }

    // EFFECTS: get pricing of this
    public String getPricing() {
        return pricing;
    }

    // EFFECTS: get name of this
    public String getName() {
        return name;
    }

}
