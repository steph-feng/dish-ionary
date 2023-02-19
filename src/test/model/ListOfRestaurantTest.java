package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ListOfRestaurantTest {
    ListOfRestaurant testList;

    @BeforeEach
    public void setUp() {
        testList = new ListOfRestaurant();
    }

    @Test
    public void constructorTest() {
        ArrayList emptyList = new ArrayList<>();
        assertEquals(emptyList, testList.getRestaurants());
    }

    @Test
    public void addNewRestaurantTest() {
        testList.addNewRestaurant("Nook");
        testList.addNewRestaurant("OEB Breakfast");
        assertEquals("Nook", testList.getRestaurants().get(0).getName());
        assertEquals("OEB Breakfast", testList.getRestaurants().get(1).getName());
    }

    @Test
    public void addExistingRestaurantTest() {
        Restaurant r = new Restaurant("Nook");
        testList.addExistingRestaurant(r);
        assertEquals(r, testList.getRestaurants().get(0));
    }

    @Test
    public void removeRestaurantSuccessfulTest() {
        testList.addNewRestaurant("Nook");
        testList.addNewRestaurant("OEB Breakfast");
        testList.removeRestaurant("Nook");
        assertEquals(1, testList.getRestaurants().size());
        assertEquals("OEB Breakfast", testList.getRestaurants().get(0).getName());
    }

    @Test
    public void removeRestaurantUnsuccessfulTest() {
        testList.addNewRestaurant("Nook");
        testList.addNewRestaurant("OEB Breakfast");
        testList.removeRestaurant("Sura");
        assertEquals(2, testList.getRestaurants().size());
        assertEquals("Nook", testList.getRestaurants().get(0).getName());
    }

    @Test
    public void getRestaurantNamesTest() {
        Restaurant r1 = new Restaurant("Nook");
        Restaurant r2 = new Restaurant("OEB Breakfast");
        Restaurant r3 = new Restaurant("Sura");
        testList.addExistingRestaurant(r1);
        testList.addExistingRestaurant(r2);
        testList.addExistingRestaurant(r3);
        ArrayList<String> newList = new ArrayList<>();
        newList.add("Nook");
        newList.add("OEB Breakfast");
        newList.add("Sura");
        assertEquals(newList, testList.getRestaurantNames());
    }

    @Test
    public void sortRestaurantRatingsTest() {
        Restaurant r1 = new Restaurant("Nook");
        r1.setRating(8);
        Restaurant r2 = new Restaurant("OEB Breakfast");
        r2.setRating(7);
        Restaurant r3 = new Restaurant("Sura");
        r3.setRating(9);
        Restaurant r4 = new Restaurant("Miku");
        r4.setRating(8);
        testList.addExistingRestaurant(r1);
        testList.addExistingRestaurant(r2);
        testList.addExistingRestaurant(r3);
        testList.addExistingRestaurant(r4);
        testList.sortRatings();
        assertEquals(r3, testList.getRestaurants().get(0));
        assertEquals(r1, testList.getRestaurants().get(1));
        assertEquals(r4, testList.getRestaurants().get(2));
        assertEquals(r2, testList.getRestaurants().get(3));
    }

    @Test
    public void sortRestaurantPricesTest() {
        Restaurant r1 = new Restaurant("Nook");
        r1.setPricing("$");
        Restaurant r2 = new Restaurant("OEB Breakfast");
        r2.setPricing("$$");
        Restaurant r3 = new Restaurant("Sura");
        r3.setPricing("$$$");
        Restaurant r4 = new Restaurant("McDonald's");
        r4.setPricing("$");
        Restaurant r5 = new Restaurant("Miku");
        r5.setPricing("$$$$");
        testList.addExistingRestaurant(r1);
        testList.addExistingRestaurant(r2);
        testList.addExistingRestaurant(r3);
        testList.addExistingRestaurant(r4);
        testList.addExistingRestaurant(r5);
        testList.sortPrices();
        assertEquals(r1, testList.getRestaurants().get(0));
        assertEquals(r4, testList.getRestaurants().get(1));
        assertEquals(r2, testList.getRestaurants().get(2));
        assertEquals(r3, testList.getRestaurants().get(3));
        assertEquals(r5, testList.getRestaurants().get(4));
    }

    @Test
    public void filterPricesTest() {
        Restaurant r1 = new Restaurant("Nook");
        r1.setPricing("$$");
        Restaurant r2 = new Restaurant("OEB Breakfast");
        r2.setPricing("$$$");
        Restaurant r3 = new Restaurant("Sura");
        r3.setPricing("$$");
        testList.addExistingRestaurant(r1);
        testList.addExistingRestaurant(r2);
        testList.addExistingRestaurant(r3);
        ArrayList<Restaurant> filteredList = testList.filterPrices("$$");
        assertEquals(2, filteredList.size());
        assertEquals(r1, filteredList.get(0));
        assertEquals(r3, filteredList.get(1));

    }

    @Test
    public void filterRatingsTest() {
        Restaurant r1 = new Restaurant("Nook");
        r1.setRating(8);
        Restaurant r2 = new Restaurant("OEB Breakfast");
        r2.setRating(7);
        Restaurant r3 = new Restaurant("Sura");
        r3.setRating(9);
        testList.addExistingRestaurant(r1);
        testList.addExistingRestaurant(r2);
        testList.addExistingRestaurant(r3);
        ArrayList<Restaurant> filteredList = testList.filterRatings(8);
        assertEquals(2, filteredList.size());
        assertEquals(r1, filteredList.get(0));
        assertEquals(r3, filteredList.get(1));
    }

    @Test
    public void filterCuisineTest() {
        Restaurant r1 = new Restaurant("Nook");
        r1.setCuisine("Italian");
        Restaurant r2 = new Restaurant("OEB Breakfast");
        r2.setCuisine("Breakfast");
        Restaurant r3 = new Restaurant("Sura");
        r3.setCuisine("Korean");
        testList.addExistingRestaurant(r1);
        testList.addExistingRestaurant(r2);
        testList.addExistingRestaurant(r3);
        ArrayList<Restaurant> filteredList = testList.filterCuisine("Breakfast");
        assertEquals(1, filteredList.size());
        assertEquals(r2, filteredList.get(0));
    }

    @Test
    public void filterOpenRestaurantsTest() {
        Restaurant r1 = new Restaurant("Nook");
        BusinessHours r1Hour =
                new BusinessHours(1, 9, 30, 15, 0);
        r1.addHours(r1Hour);
        Restaurant r2 = new Restaurant("OEB Breakfast");
        BusinessHours r2Hour =
                new BusinessHours(6, 9, 30, 20, 0);
        r2.addHours(r2Hour);
        testList.addExistingRestaurant(r1);
        testList.addExistingRestaurant(r2);
        LocalDateTime time = LocalDateTime.of(2023, 2, 18, 17, 30);
        ArrayList<Restaurant> filteredList = testList.filterOpenHours(time);
        assertEquals(1, filteredList.size());
        assertEquals(r2, filteredList.get(0));
    }

    @Test
    public void randomRestaurantTest() {
        testList.addNewRestaurant("Nook");
        testList.addNewRestaurant("OEB Breakfast");
        testList.removeRestaurant("Nook");
        Restaurant random = testList.randomRestaurant();
        assertTrue(testList.getRestaurants().contains(random));
    }

}