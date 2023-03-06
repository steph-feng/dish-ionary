package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/*
 * Unit tests for the Restaurant class
 */


class RestaurantTest {
    Restaurant testRestaurant;

    @BeforeEach
    public void setUp() {
        testRestaurant = new Restaurant("Sura");
    }

    @Test
    public void constructorTest() {
        List<BusinessHours> emptyList = new ArrayList<>();
        assertEquals("Sura", testRestaurant.getName());
        assertEquals(0, testRestaurant.getRating());
        assertEquals("", testRestaurant.getCuisine());
        assertEquals("", testRestaurant.getPricing());
        assertEquals(emptyList, testRestaurant.getHours());
    }

    @Test
    public void settersTest() {
        testRestaurant.setCuisine("Korean");
        assertEquals("Korean", testRestaurant.getCuisine());

        testRestaurant.setPricing("$$");
        assertEquals("$$", testRestaurant.getPricing());

        testRestaurant.setRating(8);
        assertEquals(8, testRestaurant.getRating());
    }

    @Test
    public void addHoursTest() {
        BusinessHours newHour = new BusinessHours(1, "09:30", "17:00");
        testRestaurant.addHours(newHour);
        assertEquals(newHour, testRestaurant.getHours().get(0));

    }

    @Test
    public void isOpenSuccessTest() {
        LocalDateTime time = LocalDateTime.of(2023, 2, 18, 17, 30);
        BusinessHours mondayHour =
                new BusinessHours(1, "09:30", "15:00");
        BusinessHours saturdayHour =
                new BusinessHours(6, "09:30", "20:00");
        testRestaurant.addHours(mondayHour);
        testRestaurant.addHours(saturdayHour);
        assertTrue(testRestaurant.isOpen(time));
    }

    @Test
    public void isOpenNotOpenAfterTimeTest() {
        LocalDateTime time = LocalDateTime.of(2023, 2, 6, 17, 30);
        BusinessHours mondayHour =
                new BusinessHours(1, "09:30", "15:00");
        BusinessHours saturdayHour =
                new BusinessHours(6, "09:30", "20:00");
        testRestaurant.addHours(mondayHour);
        testRestaurant.addHours(saturdayHour);
        assertFalse(testRestaurant.isOpen(time));
    }

    @Test
    public void isOpenNotOpenAfterBoundaryTimeTest() {
        LocalDateTime time = LocalDateTime.of(2023, 2, 6, 15, 00);
        BusinessHours mondayHour =
                new BusinessHours(1, "09:30", "15:00");
        BusinessHours saturdayHour =
                new BusinessHours(6, "09:30", "20:00");
        testRestaurant.addHours(mondayHour);
        testRestaurant.addHours(saturdayHour);
        assertFalse(testRestaurant.isOpen(time));
    }

    @Test
    public void isOpenNotOpenBeforeTimeTest() {
        LocalDateTime time = LocalDateTime.of(2023, 2, 18, 8, 30);
        BusinessHours mondayHour =
                new BusinessHours(1, "09:30", "15:00");
        BusinessHours saturdayHour =
                new BusinessHours(6, "09:30", "20:00");
        testRestaurant.addHours(mondayHour);
        testRestaurant.addHours(saturdayHour);
        assertFalse(testRestaurant.isOpen(time));
    }

    @Test
    public void isOpenNotOpenBeforeBoundaryTimeTest() {
        LocalDateTime time = LocalDateTime.of(2023, 2, 18, 9, 30);
        BusinessHours mondayHour =
                new BusinessHours(1, "09:30", "15:00");
        BusinessHours saturdayHour =
                new BusinessHours(6, "09:30", "20:00");
        testRestaurant.addHours(mondayHour);
        testRestaurant.addHours(saturdayHour);
        assertFalse(testRestaurant.isOpen(time));
    }

    @Test
    public void isOpenNotOpenDayTest() {
        LocalDateTime time = LocalDateTime.of(2023, 2, 7, 17, 30);
        BusinessHours mondayHour =
                new BusinessHours(1, "09:30", "15:00");
        BusinessHours saturdayHour =
                new BusinessHours(6, "09:30", "20:00");
        testRestaurant.addHours(mondayHour);
        testRestaurant.addHours(saturdayHour);
        assertFalse(testRestaurant.isOpen(time));
    }
}