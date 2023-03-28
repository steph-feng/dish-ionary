package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Displays a search bar and the restaurants left in the collection
 */

public class RemovePanel implements ActionListener {
    private JSplitPane split;
    private JPanel buttonPanel;
    private JPanel restaurantPanel;
    private DisplayPanel displayPanel;
    private JTextField searchField;
    private JButton searchButton;
    private JButton homeButton;
    private RestaurantManagerApp app;

    // EFFECTS: initializes panels and fields
    public RemovePanel(RestaurantManagerApp app) {
        this.app = app;

        JLabel searchLabel = new JLabel("Name of Restaurant to Remove:   ");
        searchField = new JTextField(12);
        searchButton = new JButton("Remove");
        searchButton.addActionListener(this);

        homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == homeButton) {
                    app.switchToMainPanel();
                }
            }
        });
        homeButton.setPreferredSize(new Dimension(75, 40));

        initializeButtonPanel(searchLabel);

        displayPanel = new DisplayPanel(app);

        split = new JSplitPane(SwingConstants.HORIZONTAL, buttonPanel, displayPanel.getDisplay());
    }

    // MODIFIES: this
    // EFFECTS: adds labels and buttons to the buttonPanel
    private void initializeButtonPanel(JLabel searchLabel) {
        buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        buttonPanel.add(homeButton, c);

        c.gridx = 3;
        c.gridy = 0;
        buttonPanel.add(searchLabel, c);

        c.gridx = 4;
        c.gridy = 0;
        buttonPanel.add(searchField, c);

        c.gridx = 5;
        c.gridy = 0;
        buttonPanel.add(searchButton, c);

        c.ipady = 40;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        buttonPanel.add(new JLabel("Restaurants Currently in the Collection:"), c);
    }


    // EFFECTS: processes removal or lack there of when searchButton is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            app.getRestaurantCollection().removeRestaurantByName(searchField.getText());
            displayPanel = new DisplayPanel(app);
            switchToRemovedRestaurantPane(displayPanel.getDisplay());
        }
    }

    // MODIFIES: this
    // EFFECTS: displays given pane on mainFrame
    private void switchToRemovedRestaurantPane(JScrollPane pane) {
        split = new JSplitPane(SwingConstants.HORIZONTAL, buttonPanel, pane);
        app.getMainFrame().setContentPane(split);
        app.getMainFrame().validate();
        app.getMainFrame().repaint();
        app.getMainFrame().pack();
    }

    // EFFECTS: returns split
    public JSplitPane getSplit() {
        return split;
    }
}
