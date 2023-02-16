package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        testList.addExistingRestaurant(r1);
        testList.addExistingRestaurant(r2);
        testList.addExistingRestaurant(r3);
        assertEquals(r3, testList.getRestaurants().get(0));
        assertEquals(r1, testList.getRestaurants().get(1));
        assertEquals(r2, testList.getRestaurants().get(2));
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

}