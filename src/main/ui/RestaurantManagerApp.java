package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RestaurantManagerApp {
    private JFrame mainFrame;
    private JPanel panel;

    public RestaurantManagerApp() {
        mainFrame = new JFrame("Restaurant Manager");
        panel = new JPanel(new GridLayout(2, 2, 20, 20));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(100, 100));
        JButton findButton = new JButton("Find");
        findButton.setPreferredSize(new Dimension(100, 100));
        JButton loadButton = new JButton("Load");
        loadButton.setPreferredSize(new Dimension(100, 100));
        JButton saveButton = new JButton("Save");
        saveButton.setPreferredSize(new Dimension(100, 100));

        panel.add(addButton);
        panel.add(findButton);
        panel.add(loadButton);
        panel.add(saveButton);

        mainFrame.add(panel);
        mainFrame.setSize(new Dimension(400, 300));
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


}
