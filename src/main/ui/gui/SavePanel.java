package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SavePanel implements ActionListener {
    private RestaurantManagerApp app;
    private JSplitPane savePane;
    private JPanel savePanel;
    private JPanel homeButtonPanel;
    private JButton homeButton;

    public SavePanel(RestaurantManagerApp app) {
        this.app = app;
        savePanel = new JPanel();
        savePanel.add(new JLabel("Saved restaurants to file."));

        homeButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        homeButtonPanel.add(homeButton,constraints);

        savePane = new JSplitPane(SwingConstants.HORIZONTAL, homeButtonPanel, savePanel);
    }

    public JSplitPane getSavePane() {
        return savePane;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton actionSource = (JButton) e.getSource();
        if (actionSource == homeButton) {
            app.switchToMainPanel();
        }
    }
}
