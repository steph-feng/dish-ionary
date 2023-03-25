package ui;

import model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RestaurantManagerApp {
    private JFrame mainFrame;
    private MainPanel mainPanel;
    private AddPanel addPanel;
    private ArrayList<Restaurant> restaurantCollection;

    public RestaurantManagerApp() {
        mainFrame = new JFrame("Restaurant Manager");

        mainPanel = new MainPanel(this);
        addPanel = new AddPanel(this);

        mainFrame.add(mainPanel.getMainPanel());

        mainFrame.setSize(new Dimension(400, 300));
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void switchToAddPanel() {
        mainFrame.setContentPane(addPanel.getSplit());
        mainFrame.validate();
        mainFrame.repaint();
    }

    public void switchToMainPanel() {
        mainFrame.setContentPane(mainPanel.getMainPanel());
        mainFrame.validate();
        mainFrame.repaint();
    }
}