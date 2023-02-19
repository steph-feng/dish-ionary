package model;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class BusinessHours {
    private DayOfWeek dayOfWeek;
    private LocalTime openingHours;
    private LocalTime closingHours;

    public BusinessHours(int day, int openingHour, int openingMinute, int closingHour, int closingMinute) {
        this.dayOfWeek = dayOfWeek.of(day);
        this.openingHours = openingHours.of(openingHour, openingMinute);
        this.closingHours = closingHours.of(closingHour, closingMinute);
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getOpeningHours() {
        return openingHours;
    }

    public LocalTime getClosingHours() {
        return closingHours;
    }

}