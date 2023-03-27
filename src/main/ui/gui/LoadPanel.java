package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Displays loadPanel and message to indicate successful loading
 */

public class LoadPanel implements ActionListener {
    private RestaurantManagerApp app;
    private JSplitPane loadPane;
    private JPanel loadPanel;
    private JPanel homeButtonPanel;
    private JButton homeButton;

    // EFFECTS: initializes panels, buttons, and labels
    public LoadPanel(RestaurantManagerApp app) {
        this.app = app;
        loadPanel = new JPanel();
        loadPanel.setPreferredSize(new Dimension(500,300));
        loadPanel.add(new JLabel("Restaurants loaded from file."));

        homeButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        homeButtonPanel.add(homeButton,constraints);

        loadPane = new JSplitPane(SwingConstants.HORIZONTAL, homeButtonPanel, loadPanel);
    }

    // EFFECTS: returns loadPane
    public JSplitPane getLoadPane() {
        return loadPane;
    }

    // EFFECTS: switches to MainPanel when homeButton is pressed
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource == homeButton) {
            app.switchToMainPanel();
        }

    }
}
