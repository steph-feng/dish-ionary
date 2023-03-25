package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FindPanel implements ActionListener {
    private JSplitPane findPanel;
    private JPanel buttonPanel;
    private JPanel homeButtonPanel;
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
        buttonPanel = new JPanel(new GridLayout(3, 3));
        addElementsToButtonPanel();

        homeButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        homeButtonPanel.add(homeButton,constraints);

        findPanel = new JSplitPane(SwingConstants.HORIZONTAL, homeButtonPanel, buttonPanel);

    }

    private void addElementsToButtonPanel() {
        modifyButton = new JButton("Modify Existing Restaurant in Collection");
        modifyButton.addActionListener(this);
        modifyButton.setPreferredSize(new Dimension(300,100));
        buttonPanel.add(modifyButton);
        displayButton = new JButton("Display Restaurants in Collection");
        displayButton.addActionListener(this);
        displayButton.setPreferredSize(new Dimension(300,100));
        buttonPanel.add(displayButton);
        filterButton = new JButton("Filter Restaurant Collection");
        filterButton.addActionListener(this);
        filterButton.setPreferredSize(new Dimension(300,100));
        buttonPanel.add(filterButton);
        sortButton = new JButton("Sort Restaurant Collection");
        sortButton.addActionListener(this);
        sortButton.setPreferredSize(new Dimension(300,100));
        buttonPanel.add(sortButton);
        removeButton = new JButton("Remove a Restaurant from the Collection");
        removeButton.addActionListener(this);
        removeButton.setPreferredSize(new Dimension(300,100));
        buttonPanel.add(removeButton);
        randomButton = new JButton("Get a Random Restaurant Recommendation");
        randomButton.addActionListener(this);
        randomButton.setPreferredSize(new Dimension(300,100));
        buttonPanel.add(randomButton);
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
        app.getMainFrame().setContentPane(displayPanel.getDisplayPanel());
        app.getMainFrame().validate();
        app.getMainFrame().repaint();
        app.getMainFrame().pack();
    }

    public JSplitPane getFindPanel() {
        return findPanel;
    }
}
