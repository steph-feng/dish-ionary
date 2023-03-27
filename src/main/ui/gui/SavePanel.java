package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Displays savePanel and message to indicate successful saving
 */

public class SavePanel implements ActionListener {
    private RestaurantManagerApp app;
    private JSplitPane savePane;
    private JPanel savePanel;
    private JPanel homeButtonPanel;
    private JButton homeButton;

    // EFFECTS: initializes panels, labels, and buttons
    public SavePanel(RestaurantManagerApp app) {
        this.app = app;
        savePanel = new JPanel();
        savePanel.setPreferredSize(new Dimension(500, 300));
        savePanel.add(new JLabel("Saved restaurants to file."));

        homeButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        homeButtonPanel.add(homeButton,constraints);

        savePane = new JSplitPane(SwingConstants.HORIZONTAL, homeButtonPanel, savePanel);
    }

    // EFFECTS: returns savePane
    public JSplitPane getSavePane() {
        return savePane;
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
