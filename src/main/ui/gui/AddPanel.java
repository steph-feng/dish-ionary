package ui.gui;

import model.Restaurant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPanel implements ActionListener {
    private JSplitPane split;
    protected JSplitPane detailsSplit;
    protected JPanel detailsPanel;
    protected RestaurantManagerApp app;
    protected BusinessHoursPanel businessHoursPanel;
    protected JButton saveRestaurantDetailsButton;
    private JButton homeButton;
    private JPanel homeButtonPanel;
    protected JTextField nameField;
    protected JComboBox ratingField;
    protected JTextField cuisineField;
    protected JComboBox pricingField;
    protected Restaurant addedRestaurant;


    public AddPanel(RestaurantManagerApp app) {
        this.app = app;

        detailsPanel = new JPanel(new GridLayout(5, 1));
        detailsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        businessHoursPanel = new BusinessHoursPanel();

        nameField = new JTextField();
        JLabel nameLabel = new JLabel("Name of Restaurant:");
        nameLabel.setLabelFor(nameField);

        String[] ratingOptions = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ratingField = new JComboBox(ratingOptions);
        JLabel ratingLabel = new JLabel("Rating:");
        ratingLabel.setLabelFor(ratingField);

        cuisineField = new JTextField();
        JLabel cuisineLabel = new JLabel("Cuisine:");
        cuisineLabel.setLabelFor(cuisineField);

        String[] pricingOptions = { "", "$", "$$", "$$$", "$$$$"};
        pricingField = new JComboBox(pricingOptions);
        JLabel pricingLabel = new JLabel("Pricing: ");
        pricingLabel.setLabelFor(pricingField);

        addElementsToPanel(nameLabel, ratingLabel, cuisineLabel, pricingLabel);

        detailsSplit = new JSplitPane(SwingConstants.HORIZONTAL, detailsPanel,
                businessHoursPanel.getBusinessHoursPanel());

        split = new JSplitPane(SwingConstants.HORIZONTAL, homeButtonPanel,detailsSplit);
    }

    private void addElementsToPanel(JLabel nameLabel, JLabel ratingLabel, JLabel cuisineLabel, JLabel pricingLabel) {
        detailsPanel.add(nameLabel);
        detailsPanel.add(nameField);
        detailsPanel.add(ratingLabel);
        detailsPanel.add(ratingField);
        detailsPanel.add(cuisineLabel);
        detailsPanel.add(cuisineField);
        detailsPanel.add(pricingLabel);
        detailsPanel.add(pricingField);

        JLabel blank1 = new JLabel();
        JLabel blank2 = new JLabel();
        saveRestaurantDetailsButton = new JButton("Save Details");
        saveRestaurantDetailsButton.addActionListener(this);
        businessHoursPanel.getBusinessHoursPanel().add(blank1);
        businessHoursPanel.getBusinessHoursPanel().add(blank2);
        businessHoursPanel.getBusinessHoursPanel().add(saveRestaurantDetailsButton);

        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        homeButton.setPreferredSize(new Dimension(75, 40));
        homeButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        homeButtonPanel.add(homeButton, constraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource == saveRestaurantDetailsButton) {
            Restaurant newestRestaurant = new Restaurant(nameField.getText());
            nameField.setText("");

            Integer chosenRating = Integer.parseInt((String) ratingField.getSelectedItem());
            newestRestaurant.setRating(chosenRating);
            ratingField.setSelectedIndex(0);

            newestRestaurant.setCuisine(cuisineField.getText());
            cuisineField.setText("");

            String chosenPricing = (String) pricingField.getSelectedItem();
            newestRestaurant.setPricing(chosenPricing);
            pricingField.setSelectedIndex(0);

            addBusinessHours(newestRestaurant);
            addedRestaurant = newestRestaurant;

            app.addRestaurantToCollection(newestRestaurant);
            app.switchToMainPanel();
        }
        if (actionSource == homeButton) {
            app.switchToMainPanel();
        }
    }

    private void addBusinessHours(Restaurant r) {
        r.addHours(businessHoursPanel.getMondayHours());
        r.addHours(businessHoursPanel.getTuesdayHours());
        r.addHours(businessHoursPanel.getWednesdayHours());
        r.addHours(businessHoursPanel.getThursdayHours());
        r.addHours(businessHoursPanel.getFridayHours());
        r.addHours(businessHoursPanel.getSaturdayHours());
        r.addHours(businessHoursPanel.getSundayHours());
    }

    public JSplitPane getDetailsSplit() {
        return detailsSplit;
    }

    public JSplitPane getSplit() {
        return split;
    }

}
