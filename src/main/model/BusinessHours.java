package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.DayOfWeek;
import java.time.LocalTime;

/*
 * A day of the week's business hours for a restaurant
 */

public class BusinessHours implements Writable {
    private DayOfWeek dayOfWeek;
    private LocalTime openingHours;
    private LocalTime closingHours;

    // EFFECTS: constructs business hours with a day, opening time, and closing time
    public BusinessHours(int day, String openingHour, String closingHour) {
        this.dayOfWeek = dayOfWeek.of(day);
        this.openingHours = openingHours.parse(openingHour);
        this.closingHours = closingHours.parse(closingHour);
    }

    // EFFECTS: gets the business hour's day of the week
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    // EFFECTS: gets the business hour's opening time
    public LocalTime getOpeningHours() {
        return openingHours;
    }

    // EFFECTS: gets the business hour's closing time
    public LocalTime getClosingHours() {
        return closingHours;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("day", dayOfWeek.getValue());
        json.put("open", openingHours.toString());
        json.put("close", closingHours.toString());
        return json;
    }
}