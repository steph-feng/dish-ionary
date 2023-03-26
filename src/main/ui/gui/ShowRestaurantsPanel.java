package ui.gui;

import model.BusinessHours;
import model.Restaurant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class ShowRestaurantsPanel {
    private JScrollPane display;
    private JPanel restaurantPanel;
    private RestaurantManagerApp app;

    public ShowRestaurantsPanel(RestaurantManagerApp app, ArrayList<Restaurant> restaurants) {
        this.app = app;

        restaurantPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        restaurantPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        displayRestaurants(restaurants);

        display = new JScrollPane(restaurantPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private void displayRestaurants(ArrayList<Restaurant> restaurants) {
        for (Restaurant r : restaurants) {
            JPanel newPanel = new JPanel(new GridLayout(12, 1));
            newPanel.setBackground(new Color(201, 235, 211));
            newPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
            JLabel name = new JLabel("Restaurant Name: " + r.getName());
            JLabel rating = new JLabel("Rating: " + Integer.toString(r.getRating()));
            JLabel cuisine = new JLabel("Cuisine: " + r.getCuisine());
            JLabel pricing = new JLabel("Pricing: " + r.getPricing());
            JLabel businessHours = new JLabel("Business Hours: ");
            newPanel.add(name);
            newPanel.add(rating);
            newPanel.add(cuisine);
            newPanel.add(pricing);
            newPanel.add(businessHours);
            for (BusinessHours b : r.getHours()) {
                JLabel hour = new JLabel("      " + b.getDayOfWeek() + ": " + b.getOpeningHours()
                        + " - " + b.getClosingHours());
                newPanel.add(hour);
            }

            restaurantPanel.add(newPanel);
        }
    }

    public JScrollPane getDisplay() {
        return display;
    }
}


