package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the RestaurantRatingComparator class
 */

class RestaurantRatingComparatorTest {
    RestaurantRatingComparator testComparator;
    Restaurant r1;
    Restaurant r2;

    @BeforeEach
    public void setUp() {
        r1 = new Restaurant("Nook");
        r2 = new Restaurant("Miku");
        testComparator = new RestaurantRatingComparator();
    }

    @Test
    public void compareEqualTest() {
        r1.setRating(8);
        r2.setRating(8);
        assertEquals(0, testComparator.compare(r1, r2));
    }

    @Test
    public void compareGreaterThanTest() {
        r1.setRating(6);
        r2.setRating(5);
        assertEquals(-1, testComparator.compare(r1, r2));
    }

    @Test
    public void compareLessThanTest() {
        r1.setRating(7);
        r2.setRating(9);
        assertEquals(1, testComparator.compare(r1, r2));
    }

    }