package ui.gui;

import model.BusinessHours;
import model.Restaurant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomPanel implements ActionListener {
    private JSplitPane randomPane;
    private RestaurantManagerApp app;
    private JPanel homeButtonPanel;
    private JPanel randomPanel;
    private JButton homeButton;

    public RandomPanel(RestaurantManagerApp app) {
        this.app = app;

        homeButton = new JButton("Home");
        homeButton.addActionListener(this);
        homeButton.setPreferredSize(new Dimension(75, 40));
        homeButtonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        homeButtonPanel.add(homeButton, constraints);

        randomPanel = new JPanel();

        ImageIcon gif = new ImageIcon("./data/gif.gif");
        JLabel gifLabel = new JLabel(gif);
        randomPanel.add(gifLabel);

        removeGifAfterTime(app, gifLabel);

        randomPane = new JSplitPane(SwingConstants.HORIZONTAL, homeButtonPanel, randomPanel);

    }

    private void removeGifAfterTime(RestaurantManagerApp app, JLabel gifLabel) {
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                randomPanel.remove(gifLabel);
                addRestaurantRecommendation(app);
                randomPanel.revalidate();
                randomPanel.repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void addRestaurantRecommendation(RestaurantManagerApp app) {
        Restaurant r = app.getRestaurantCollection().randomRestaurant();
        JPanel newPanel = new JPanel(new GridLayout(12, 1));
        newPanel.setBackground(new Color(201, 235, 211));
        newPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel name = new JLabel("Restaurant Name: " + r.getName());
        JLabel rating = new JLabel("Rating: " + Integer.toString(r.getRating()));
        JLabel cuisine = new JLabel("Cuisine: " + r.getCuisine());
        JLabel pricing = new JLabel("Pricing: " + r.getPricing());
        JLabel businessHours = new JLabel("Business Hours: ");
        newPanel.add(name);
        newPanel.add(rating);
        newPanel.add(cuisine);
        newPanel.add(pricing);
        newPanel.add(businessHours);
        for (BusinessHours b : r.getHours()) {
            JLabel hour = new JLabel("      " + b.getDayOfWeek() + ": " + b.getOpeningHours()
                    + " - " + b.getClosingHours());
            newPanel.add(hour);
        }
        randomPanel.add(newPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == homeButton) {
            app.switchToMainPanel();
        }
    }

    public JSplitPane getRandomPane() {
        return randomPane;
    }
}
