package ui;

import model.BusinessHours;
import model.Restaurant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayPanel implements ActionListener {
    private JScrollPane display;
    private JPanel displayPanel;
    private JButton homeButton;
    private RestaurantManagerApp app;
    private ArrayList<Restaurant> restaurants;

    public DisplayPanel(RestaurantManagerApp app) {
        displayPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        displayPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        display = new JScrollPane(displayPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.app = app;
        restaurants = this.app.getRestaurantCollection();
        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        displayPanel.add(homeButton, BorderLayout.NORTH);
        displayRestaurantPanels();
    }

    private void displayRestaurantPanels() {
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

            displayPanel.add(newPanel);
        }
    }

    public JScrollPane getDisplay() {
        return display;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource == homeButton) {
            app.switchToMainPanel();
        }
    }
}
