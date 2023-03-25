package ui;

import model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RestaurantManagerApp {
    private JFrame mainFrame;
    private MainPanel mainPanel;
    private AddPanel addPanel;
    private FindPanel findPanel;
    private ArrayList<Restaurant> restaurantCollection;

    public RestaurantManagerApp() {
        mainFrame = new JFrame("Restaurant Manager");
        restaurantCollection = new ArrayList<>();

        mainPanel = new MainPanel(this);
        addPanel = new AddPanel(this);
        findPanel = new FindPanel(this);

        mainFrame.add(mainPanel.getMainPanel());
        mainFrame.pack();
        mainFrame.setSize(new Dimension(400, 300));
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void switchToFindPanel() {
        mainFrame.setContentPane(findPanel.getFindPanel());
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    public void switchToAddPanel() {
        mainFrame.setContentPane(addPanel.getSplit());
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    public void switchToMainPanel() {
        mainFrame.setContentPane(mainPanel.getMainPanel());
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    public void addRestaurantToCollection() {
        restaurantCollection.add(addPanel.getNewestRestaurant());
    }


    public ArrayList<Restaurant> getRestaurantCollection() {
        return restaurantCollection;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}