package model;

import java.util.Comparator;

/*
 * Comparator for restaurant ratings
 */

public class RestaurantRatingComparator implements Comparator<Restaurant> {

    // EFFECTS: compares the ratings of two restaurants in descending order
    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        if (r1.getRating() == r2.getRating()) {
            return 0;
        } else if (r1.getRating() > r2.getRating()) {
            return -1;
        } else {
            return 1;
        }
    }
}
