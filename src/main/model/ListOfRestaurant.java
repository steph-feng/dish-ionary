package model;

/*
 * A collection of Restaurant
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ListOfRestaurant {
    private ArrayList<Restaurant> restaurants;

    public ListOfRestaurant() {
        restaurants = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: creates restaurant with given name and adds to list
    public void addNewRestaurant(String restaurantName) {
        Restaurant r = new Restaurant(restaurantName);
        restaurants.add(r);
    }

    // MODIFIES: this
    // EFFECTS: adds given restaurant to list
    public void addExistingRestaurant(Restaurant r) {
        restaurants.add(r);
    }

    // MODIFIES: this
    // EFFECTS: removes given restaurant from this
    public void removeRestaurant(String restaurantName) {
        int counter = 0;
        for (Restaurant r : restaurants) {
            if (r.getName() == restaurantName) {
                restaurants.remove(counter);
            }
            counter = counter + 1;
        }
    }

    // EFFECTS: sorts restaurant ratings in descending order
    public ArrayList<Restaurant> sortRatings() {
        Collections.sort(restaurants, new RestaurantRatingComparator());
        return restaurants;
    }


    // EFFECTS: sorts restaurant prices from $ -> $$ -> $$$ -> $$$$
    public ArrayList<Restaurant> sortPrices() {
        Collections.sort(restaurants, new RestaurantPriceComparator());
        return restaurants;
    }

    // REQUIRES: one of $, $$, $$$, $$$$
    // EFFECTS: filter restaurant prices to only show restaurants that are at given price
    public ArrayList<Restaurant> filterPrices(String s) {
        ArrayList<Restaurant> filteredList = new ArrayList<>();
        for (Restaurant r: restaurants) {
            if (r.getPricing() == s) {
                filteredList.add(r);
            }
        }
        return filteredList;
    }

    // REQUIRES: [0,10]
    // EFFECTS: filter restaurant ratings to only show restaurants that have rating >= given rating
    public ArrayList<Restaurant> filterRatings(int rating) {
        ArrayList<Restaurant> filteredList = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.getRating() >= rating) {
                filteredList.add(r);
            }
        }
        return filteredList;
    }

    // EFFECTS: filter restaurant ratings to only show restaurant names that meet given cuisine
    public ArrayList<Restaurant> filterCuisine(String s) {
        ArrayList<Restaurant> filteredList = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.getCuisine() == s) {
                filteredList.add(r);
            }
        }
        return filteredList;
    }

    // EFFECTS: filter restaurants, only returning restaurants that are open at the time given
    public ArrayList<Restaurant> filterOpenHours(LocalDateTime currentTime) {
        ArrayList<Restaurant> filteredList = new ArrayList<>();
        for (Restaurant r : restaurants) {
            if (r.isOpen(currentTime)) {
                filteredList.add(r);
            }
        }
        return filteredList;
    }


    // EFFECTS: return random restaurant from list
    public Restaurant randomRestaurant() {
        Random rand = new Random();
        int randomNumberUpperBound = restaurants.size();
        int randomNumber = rand.nextInt(randomNumberUpperBound);
        return restaurants.get(randomNumber);
    }

    // EFFECTS: returns all restaurants in list
    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    // EFFECTS: gets names of all restaurants in list
    public ArrayList<String> getRestaurantNames() {
        ArrayList<String> restaurantNames = new ArrayList<>();
        for (Restaurant r : restaurants) {
            restaurantNames.add(r.getName());
        }
        return restaurantNames;
    }

}
