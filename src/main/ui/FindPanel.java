package ui;

import model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FindPanel implements ActionListener {
    private JPanel findPanel;
    private RestaurantManagerApp app;
    private JButton modifyButton;
    private JButton displayButton;
    private DisplayPanel displayPanel;
    private JButton filterButton;
    private JButton sortButton;
    private JButton removeButton;
    private JButton randomButton;
    private JButton homeButton;

    public FindPanel(RestaurantManagerApp app) {
        this.app = app;
        findPanel = new JPanel(new GridLayout(3, 3));

        JLabel blank1 = new JLabel();
        JLabel blank2 = new JLabel();
        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        findPanel.add(homeButton);
        findPanel.add(blank1);
        findPanel.add(blank2);
        addElementsToPanel();
    }

    private void addElementsToPanel() {
        modifyButton = new JButton("Modify Existing Restaurant in Collection");
        modifyButton.addActionListener(this);
        findPanel.add(modifyButton);
        displayButton = new JButton("Display Restaurants in Collection");
        displayButton.addActionListener(this);
        findPanel.add(displayButton);
        filterButton = new JButton("Filter Restaurant Collection");
        filterButton.addActionListener(this);
        findPanel.add(filterButton);
        sortButton = new JButton("Sort Restaurant Collection");
        sortButton.addActionListener(this);
        findPanel.add(sortButton);
        removeButton = new JButton("Remove a Restaurant from the Collection");
        removeButton.addActionListener(this);
        findPanel.add(removeButton);
        randomButton = new JButton("Get a Random Restaurant Recommendation");
        randomButton.addActionListener(this);
        findPanel.add(randomButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource == homeButton) {
            app.switchToMainPanel();
        }
        if (actionSource == displayButton) {
            switchToDisplayPanel();
        }
    }

    public void switchToDisplayPanel() {
        displayPanel = new DisplayPanel(app);
        app.getMainFrame().setContentPane(displayPanel.getDisplay());
        app.getMainFrame().validate();
        app.getMainFrame().repaint();
    }

    public JPanel getFindPanel() {
        return findPanel;
    }
}
