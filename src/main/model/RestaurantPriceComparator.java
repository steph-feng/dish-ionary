package model;

import java.util.Comparator;

public class RestaurantPriceComparator implements Comparator<Restaurant> {

    @Override
    public int compare(Restaurant r1, Restaurant r2) {
        int r1Price = setPrice(r1);
        int r2Price = setPrice(r2);
        if (r1Price == r2Price) {
            return 0;
        } else if (r1Price > r2Price) {
            return 1;
        } else {
            return -1;
        }
    }

    private int setPrice(Restaurant r) {
        if (r.getPricing().equals("$")) {
            return 1;
        } else if (r.getPricing().equals("$$")) {
            return 2;
        } else if (r.getPricing().equals("$$$")) {
            return 3;
        } else {
            return 4;
        }
    }
}
