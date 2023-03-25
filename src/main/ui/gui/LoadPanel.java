package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadPanel implements ActionListener {
    private RestaurantManagerApp app;
    private JSplitPane loadPane;
    private JPanel loadPanel;
    private JPanel homeButtonPanel;
    private JButton homeButton;

    public LoadPanel(RestaurantManagerApp app) {
        this.app = app;
        loadPanel = new JPanel();
        loadPanel.add(new JLabel("Restaurants loaded from file."));

        homeButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        homeButtonPanel.add(homeButton,constraints);

        loadPane = new JSplitPane(SwingConstants.HORIZONTAL, homeButtonPanel, loadPanel);
    }

    public JSplitPane getLoadPane() {
        return loadPane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource == homeButton) {
            app.switchToMainPanel();
        }

    }
}
