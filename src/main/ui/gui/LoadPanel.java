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

        loadPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 0;
        loadPanel.setPreferredSize(new Dimension(600,400));
        loadPanel.add(new JLabel("Restaurants loaded from file!"), c);

        c.gridx = 0;
        c.gridy = 1;
        ImageIcon gif = new ImageIcon("./data/gif2.gif");
        JLabel gifLabel = new JLabel(gif);
        loadPanel.add(gifLabel, c);

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
