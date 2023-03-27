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
        savePanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 0;
        savePanel.setPreferredSize(new Dimension(600,350));
        savePanel.add(new JLabel("Saved restaurants to file!"), c);

        c.gridx = 0;
        c.gridy = 1;
        ImageIcon gif = new ImageIcon("./data/gif3.gif");
        JLabel gifLabel = new JLabel(gif);
        savePanel.add(gifLabel, c);

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
