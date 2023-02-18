package model;

import java.util.Comparator;

public class RestaurantRatingComparator implements Comparator<Restaurant> {

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
