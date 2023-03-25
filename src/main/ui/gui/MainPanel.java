package ui.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainPanel implements ActionListener {
    private JPanel mainPanel;
    private RestaurantManagerApp app;
    private JButton addButton;
    private JButton findButton;
    private JButton loadButton;
    private JButton saveButton;

    public MainPanel(RestaurantManagerApp app) {
        this.app = app;

        mainPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        initializeButtons();

    }


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


    public JPanel getMainPanel() {
        return mainPanel;
    }
}
