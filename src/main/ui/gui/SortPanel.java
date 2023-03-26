package ui.gui;

import model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SortPanel implements ActionListener {
    private JSplitPane split;
    private JPanel buttonPanel;
    private JPanel restaurantPanel;
    private ShowRestaurantsPanel showRestaurantPanel;
    private JComboBox sortOptions;
    private JButton homeButton;
    private RestaurantManagerApp app;

    public SortPanel(RestaurantManagerApp app) {
        this.app = app;

        String[] options = {"", "Sort by Rating", "Sort by Price"};
        sortOptions = new JComboBox(options);
        sortOptions.addActionListener(this);
        JLabel sortLabel = new JLabel("Sorting Options:");
        sortLabel.setLabelFor(sortOptions);

        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        homeButton.setPreferredSize(new Dimension(75, 40));

        buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(homeButton);
        buttonPanel.add(new JLabel());
        buttonPanel.add(sortLabel);
        buttonPanel.add(sortOptions);

        restaurantPanel = new JPanel();

        split = new JSplitPane(SwingConstants.HORIZONTAL, buttonPanel, restaurantPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            app.switchToMainPanel();
        }

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

    private void switchToSortedPane(JSplitPane pane) {
        app.getMainFrame().setContentPane(pane);
        app.getMainFrame().validate();
        app.getMainFrame().repaint();
        app.getMainFrame().pack();
    }

    public JSplitPane getSplit() {
        return split;
    }
}
