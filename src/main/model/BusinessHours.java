package model;

import java.time.DayOfWeek;
import java.time.LocalTime;

/*
 * A day of the week's business hours for a restaurant
 */

public class BusinessHours {
    private DayOfWeek dayOfWeek;
    private LocalTime openingHours;
    private LocalTime closingHours;

    // EFFECTS: constructs business hours with a day, opening time, and closing time
    public BusinessHours(int day, int openingHour, int openingMinute, int closingHour, int closingMinute) {
        this.dayOfWeek = dayOfWeek.of(day);
        this.openingHours = openingHours.of(openingHour, openingMinute);
        this.closingHours = closingHours.of(closingHour, closingMinute);
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

}