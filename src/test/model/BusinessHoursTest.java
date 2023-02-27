package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static java.time.DayOfWeek.MONDAY;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the BusinessHours class
 */

class BusinessHoursTest {
    BusinessHours testHours;

    @BeforeEach
    public void setUp() {
        testHours = new BusinessHours(1, 12, 0, 0, 0);
    }

    @Test
    public void constructorTest() {
        assertEquals(MONDAY, testHours.getDayOfWeek());
        assertEquals(LocalTime.NOON,testHours.getOpeningHours());
        assertEquals(LocalTime.MIDNIGHT, testHours.getClosingHours());
    }

    }