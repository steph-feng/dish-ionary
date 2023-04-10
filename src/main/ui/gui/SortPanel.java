package ui.gui;

import model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * Displays the sorted restaurant collection
 */

public class SortPanel implements ActionListener {
    private JSplitPane split;
    private JPanel buttonPanel;
    private JPanel restaurantPanel;
    private ShowRestaurantsPanel showRestaurantPanel;
    private JComboBox sortOptions;
    private JButton homeButton;
    private RestaurantManagerApp app;

    // EFFECTS: initializes fields and buttons
    public SortPanel(RestaurantManagerApp app) {
        this.app = app;

        String[] options = {"", "Sort by Rating", "Sort by Price"};
        sortOptions = new JComboBox(options);
        sortOptions.addActionListener(this);
        JLabel sortLabel = new JLabel("Sorting Options:");
        sortLabel.setLabelFor(sortOptions);

        initializeHomeButton(app);

        buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(homeButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(sortLabel);
        buttonPanel.add(sortOptions);

        restaurantPanel = new JPanel();

        split = new JSplitPane(SwingConstants.HORIZONTAL, buttonPanel, restaurantPanel);

    }

    // EFFECTS: creates home button and adds listener
    private void initializeHomeButton(RestaurantManagerApp app) {
        homeButton = new JButton("Home");
        ActionListener homeButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == homeButton) {
                    app.switchToMainPanel();
                }
            }
        };
        homeButton.addActionListener(homeButtonListener);
        homeButton.setPreferredSize(new Dimension(75, 40));
    }

    // EFFECTS: sorts and displays restaurant by pricing or rating depending on ActionEvent
    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox action = (JComboBox) e.getSource();
        String selectedOption = (String) action.getSelectedItem();
        if (selectedOption.equals("Sort by Rating")) {
            ArrayList<Restaurant> sorted = app.getRestaurantCollection().sortRatings();
            showRestaurantPanel = new ShowRestaurantsPanel(app, sorted);
            split = new JSplitPane(SwingConstants.HORIZONTAL, buttonPanel, showRestaurantPanel.getDisplay());
            switchToSortedPane(split);
        }
        if (selectedOption.equals("Sort by Price")) {
            ArrayList<Restaurant> sorted = app.getRestaurantCollection().sortPrices();
            showRestaurantPanel = new ShowRestaurantsPanel(app, sorted);
            split = new JSplitPane(SwingConstants.HORIZONTAL, buttonPanel, showRestaurantPanel.getDisplay());
            switchToSortedPane(split);
        }
    }

    // MODIFIES: this
    // EFFECTS: displays given pane on mainFrame
    private void switchToSortedPane(JSplitPane pane) {
        app.getMainFrame().setContentPane(pane);
        app.getMainFrame().validate();
        app.getMainFrame().repaint();
        app.getMainFrame().pack();
    }

    // EFFECTS: returns split pane
    public JSplitPane getSplit() {
        return split;
    }
}
