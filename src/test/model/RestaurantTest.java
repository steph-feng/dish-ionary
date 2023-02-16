package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant testRestaurant;

    @BeforeEach
    public void setUp() {
        testRestaurant = new Restaurant("Sura");
    }

    @Test
    public void constructorTest() {
        assertEquals("Sura", testRestaurant.getName());
        assertEquals(0, testRestaurant.getRating());
        assertEquals("", testRestaurant.getCuisine());
        assertEquals("", testRestaurant.getPricing());
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
}