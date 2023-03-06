package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/*
 * A restaurant with a name, rating, cuisine, pricing, and business hours.
 */

public class Restaurant implements Writable {
    private String name;
    private int rating;
    private String cuisine;
    private String pricing;
    private List<BusinessHours> hours;

    // EFFECTS: create a new restaurant with 0 rating, no cuisine, no pricing, and an empty business hours list
    public Restaurant(String name) {
        this.name = name;
        this.rating = 0;
        this.cuisine = "";
        this.pricing = "";
        this.hours = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a day of the week's open hours
    public void addHours(BusinessHours newHour) {
        hours.add(newHour);
    }

    // EFFECTS: return true if the restaurant is open at the time given
    public Boolean isOpen(LocalDateTime currentTime) {
        DayOfWeek dayOfWeek = currentTime.getDayOfWeek();
        LocalTime time = currentTime.toLocalTime();
        for (BusinessHours h : hours) {
            if (dayOfWeek.equals(h.getDayOfWeek())) {
                return time.isAfter(h.getOpeningHours()) && time.isBefore(h.getClosingHours());
            }
        }
        return false;
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

    // EFFECTS: get list of BusinessHours of this
    public List<BusinessHours> getHours() {
        return hours;
    }

    // EFFECTS: returns Restaurant as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("rating", rating);
        json.put("cuisine", cuisine);
        json.put("pricing", pricing);
        json.put("hours", hoursToJson());
        return json;
    }

    // EFFECTS: returns BusinessHours in list as JSONArray
    private JSONArray hoursToJson() {
        JSONArray jsonArray = new JSONArray();
        for (BusinessHours h : hours) {
            jsonArray.put(h.toJson());
        }
        return jsonArray;
    }
}
