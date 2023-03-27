package ui.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Displays the panel with add, find, load, and save buttons
 */

public class MainPanel implements ActionListener {
    private JPanel mainPanel;
    private RestaurantManagerApp app;
    private JButton addButton;
    private JButton findButton;
    private JButton loadButton;
    private JButton saveButton;

    // EFFECTS: initializes fields and panel
    public MainPanel(RestaurantManagerApp app) {
        this.app = app;

        mainPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        initializeButtons();

    }

    // MODIFIES: this
    // EFFECTS: sets properties for the buttons
    private void initializeButtons() {
        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100, 100));
        findButton = new JButton("Find");
        findButton.setPreferredSize(new Dimension(100, 100));
        loadButton = new JButton("Load");
        loadButton.setPreferredSize(new Dimension(100, 100));
        saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 100));

        mainPanel.add(addButton);
        addButton.addActionListener(this);
        mainPanel.add(findButton);
        findButton.addActionListener(this);
        mainPanel.add(loadButton);
        loadButton.addActionListener(this);
        mainPanel.add(saveButton);
        saveButton.addActionListener(this);
    }


    // EFFECTS: switches to AddPanel if addButton is pressed
    //          switches to FindPanel if findButton is pressed
    //          switches to LoadPanel if loadButton is pressed
    //          switches to SavePanel if saveButton is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource == addButton) {
            app.switchToAddPanel();
        }
        if (actionSource == findButton) {
            app.switchToFindPanel();
        }
        if (actionSource == loadButton) {
            app.loadRestaurantCollection();
            app.switchToLoadPanel();
        }
        if (actionSource == saveButton) {
            app.saveRestaurantCollection();
            app.switchToSavePanel();
        }
    }


    // EFFECTS: returns mainPanel
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
