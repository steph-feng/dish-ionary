package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * Unit tests for the RestaurantPriceComparator class
 */

class RestaurantPriceComparatorTest {
    RestaurantPriceComparator testComparator;
    Restaurant r1;
    Restaurant r2;

    @BeforeEach
    public void setUp() {
        r1 = new Restaurant("Nook");
        r2 = new Restaurant("Miku");
        testComparator = new RestaurantPriceComparator();
    }

    @Test
    public void compareEqualTest() {
        r1.setPricing("$$");
        r2.setPricing("$$");
        assertEquals(0, testComparator.compare(r1, r2));
    }

    @Test
    public void compareGreaterThanTest() {
        r1.setPricing("$$$");
        r2.setPricing("$$");
        assertEquals(1, testComparator.compare(r1, r2));
    }

    @Test
    public void compareLessThanTest() {
        r1.setPricing("$");
        r2.setPricing("$$$$");
        assertEquals(-1, testComparator.compare(r1, r2));
    }

    }