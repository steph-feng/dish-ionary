package ui.gui;

import model.ListOfRestaurant;
import model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyPanel extends AddPanel implements ActionListener {
    private RestaurantManagerApp app;
    private ListOfRestaurant restaurants;
    private JSplitPane modifyPane;
    private JSplitPane detailsPane;
    private JSplitPane noDetailsPane;
    private JPanel modifyPanel;
    private JPanel homeButtonPanel;
    private JSplitPane restaurantDetailsPanel;
    private JPanel noRestaurantPanel;
    private JButton homeButton;
    private JButton searchButton;
    private JTextField searchField;

    public ModifyPanel(RestaurantManagerApp app) {
        super(app);
        this.app = app;
        restaurants = app.getRestaurantCollection();
        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        modifyPanel = new JPanel(new FlowLayout());

        searchField = new JTextField(20);
        JLabel nameLabel = new JLabel("Name of Restaurant to Modify:");
        nameLabel.setLabelFor(searchField);
        modifyPanel.add(nameLabel);
        modifyPanel.add(searchField);
        modifyPanel.add(searchButton);

        homeButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        homeButtonPanel.add(homeButton, constraints);

        modifyPane = new JSplitPane(SwingConstants.HORIZONTAL, homeButtonPanel, modifyPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource == homeButton) {
            app.switchToMainPanel();
        }
        if (actionSource == searchButton) {
            processNameField();
        }
        if (actionSource == saveRestaurantDetailsButton) {
            for (Restaurant r : app.getRestaurantCollection().getRestaurants()) {
                if (r.getName().equals(nameField.getText())) {
                    r.setRating(Integer.parseInt((String) ratingField.getSelectedItem()));
                    r.setCuisine(cuisineField.getText());
                    String chosenPricing = (String) pricingField.getSelectedItem();
                    r.setPricing(chosenPricing);
                    addBusinessHours(r);
                }
            }
            nameField.setText("");
            ratingField.setSelectedIndex(0);
            cuisineField.setText("");
            pricingField.setSelectedIndex(0);

            app.switchToMainPanel();
        }

    }

    private void addBusinessHours(Restaurant r) {
        r.getHours().clear();
        r.addHours(businessHoursPanel.getMondayHours());
        r.addHours(businessHoursPanel.getTuesdayHours());
        r.addHours(businessHoursPanel.getWednesdayHours());
        r.addHours(businessHoursPanel.getThursdayHours());
        r.addHours(businessHoursPanel.getFridayHours());
        r.addHours(businessHoursPanel.getSaturdayHours());
        r.addHours(businessHoursPanel.getSundayHours());
    }


    private Boolean processNameField() {
        for (Restaurant r : restaurants.getRestaurants()) {
            if (searchField.getText().equals(r.getName())) {
                nameField.setText(r.getName());
                cuisineField.setText(r.getCuisine());
                pricingField.setSelectedItem(r.getPricing());
                ratingField.setSelectedItem(Integer.toString(r.getRating()));
                modifyBusinessHours(r);
                restaurantDetailsPanel = getDetailsSplit();
                detailsPane = new JSplitPane(SwingConstants.HORIZONTAL, homeButtonPanel, restaurantDetailsPanel);
                switchToDetailsPane();
                return true;
            }
        }
        noRestaurantPanel = new JPanel();
        JLabel noRestaurantLabel = new JLabel("No restaurant with that name is in the collection.");
        noRestaurantPanel.add(noRestaurantLabel);
        noDetailsPane = new JSplitPane(SwingConstants.HORIZONTAL, homeButtonPanel, noRestaurantPanel);
        switchToNoDetailsPane();
        return false;

    }

    private void modifyBusinessHours(Restaurant r) {
        businessHoursPanel.getMondayOpen().setSelectedItem(r.getHours().get(0).getOpeningHours().toString());
        businessHoursPanel.getMondayClose().setSelectedItem(r.getHours().get(0).getClosingHours().toString());
        businessHoursPanel.getTuesdayOpen().setSelectedItem(r.getHours().get(1).getOpeningHours().toString());
        businessHoursPanel.getTuesdayClose().setSelectedItem(r.getHours().get(1).getClosingHours().toString());
        businessHoursPanel.getWednesdayOpen().setSelectedItem(r.getHours().get(2).getOpeningHours().toString());
        businessHoursPanel.getWednesdayClose().setSelectedItem(r.getHours().get(2).getClosingHours().toString());
        businessHoursPanel.getThursdayOpen().setSelectedItem(r.getHours().get(3).getOpeningHours().toString());
        businessHoursPanel.getThursdayClose().setSelectedItem(r.getHours().get(3).getClosingHours().toString());
        businessHoursPanel.getFridayOpen().setSelectedItem(r.getHours().get(4).getOpeningHours().toString());
        businessHoursPanel.getFridayClose().setSelectedItem(r.getHours().get(4).getClosingHours().toString());
        businessHoursPanel.getSaturdayOpen().setSelectedItem(r.getHours().get(5).getOpeningHours().toString());
        businessHoursPanel.getSaturdayClose().setSelectedItem(r.getHours().get(5).getClosingHours().toString());
        businessHoursPanel.getSundayOpen().setSelectedItem(r.getHours().get(6).getOpeningHours().toString());
        businessHoursPanel.getSundayClose().setSelectedItem(r.getHours().get(6).getClosingHours().toString());
    }

    private void switchToDetailsPane() {
        app.getMainFrame().setContentPane(detailsPane);
        app.getMainFrame().validate();
        app.getMainFrame().repaint();
        app.getMainFrame().pack();
    }

    private void switchToNoDetailsPane() {
        app.getMainFrame().setContentPane(noDetailsPane);
        app.getMainFrame().validate();
        app.getMainFrame().repaint();
        app.getMainFrame().pack();
    }


    public JSplitPane getModifyPane() {
        return modifyPane;
    }
}
