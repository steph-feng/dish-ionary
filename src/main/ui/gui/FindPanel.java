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
    private ModifyPanel modifyPanel;
    private SortPanel sortPanel;
    private RandomPanel randomPanel;
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
        setButtonCharacteristics(modifyButton);

        displayButton = new JButton("Display Restaurants in Collection");
        setButtonCharacteristics(displayButton);

        filterButton = new JButton("Filter Restaurant Collection");
        setButtonCharacteristics(filterButton);

        sortButton = new JButton("Sort Restaurant Collection");
        setButtonCharacteristics(sortButton);

        removeButton = new JButton("Remove a Restaurant from the Collection");
        setButtonCharacteristics(removeButton);

        randomButton = new JButton("Get a Random Restaurant Recommendation");
        setButtonCharacteristics(randomButton);
    }

    private void setButtonCharacteristics(JButton b) {
        b.addActionListener(this);
        b.setPreferredSize(new Dimension(300,100));
        buttonPanel.add(b);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource == homeButton) {
            app.switchToMainPanel();
        }
        if (actionSource == modifyButton) {
            switchToModifyPanel();
        }
        if (actionSource == displayButton) {
            switchToDisplayPanel();
        }
        if (actionSource == sortButton) {
            switchToSortPanel();
        }
        if (actionSource == randomButton) {
            switchToRandomPanel();
        }
    }

    private void switchToRandomPanel() {
        randomPanel = new RandomPanel(app);
        app.getMainFrame().setContentPane(randomPanel.getRandomPane());
        app.getMainFrame().validate();
        app.getMainFrame().repaint();
        app.getMainFrame().pack();
    }

    private void switchToSortPanel() {
        sortPanel = new SortPanel(app);
        app.getMainFrame().setContentPane(sortPanel.getSplit());
        app.getMainFrame().validate();
        app.getMainFrame().repaint();
        app.getMainFrame().pack();
    }

    private void switchToModifyPanel() {
        modifyPanel = new ModifyPanel(app);
        app.getMainFrame().setContentPane(modifyPanel.getModifyPane());
        app.getMainFrame().validate();
        app.getMainFrame().repaint();
        app.getMainFrame().pack();
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
