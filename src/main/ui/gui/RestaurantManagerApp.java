package ui.gui;

import model.Event;
import model.EventLog;
import model.ListOfRestaurant;
import model.Restaurant;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/*
 * Displays home window and performs its loading, saving, and adding functions
 */

public class RestaurantManagerApp extends WindowAdapter {
    private JFrame mainFrame;
    private MainPanel mainPanel;
    private AddPanel addPanel;
    private FindPanel findPanel;
    private LoadPanel loadPanel;
    private SavePanel savePanel;
    private ListOfRestaurant restaurantCollection;
    private static final String JSON_STORE = "./data/restaurantCollection.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initializes fields
    public RestaurantManagerApp() {
        mainFrame = new JFrame("Restaurant Manager");
        restaurantCollection = new ListOfRestaurant();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        mainPanel = new MainPanel(this);
        addPanel = new AddPanel(this);
        findPanel = new FindPanel(this);
        loadPanel = new LoadPanel(this);
        savePanel = new SavePanel(this);

        mainFrame.add(mainPanel.getMainPanel());
        mainFrame.pack();
        mainFrame.setSize(new Dimension(400, 300));
        mainFrame.setVisible(true);
        mainFrame.addWindowListener(this);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    // MODIFIES: this
    // EFFECTS: sets frame to display the SavePanel
    public void switchToSavePanel() {
        mainFrame.setContentPane(savePanel.getSavePane());
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    // MODIFIES: this
    // EFFECTS: sets frame to display the LoadPanel
    public void switchToLoadPanel() {
        mainFrame.setContentPane(loadPanel.getLoadPane());
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    // MODIFIES: this
    // EFFECTS: sets frame to display the FindPanel
    public void switchToFindPanel() {
        mainFrame.setContentPane(findPanel.getFindPanel());
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    // MODIFIES: this
    // EFFECTS: sets frame to display the AddPanel
    public void switchToAddPanel() {
        mainFrame.setContentPane(addPanel.getSplit());
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    // MODIFIES: this
    // EFFECTS: sets frame to display the MainPanel
    public void switchToMainPanel() {
        mainFrame.setContentPane(mainPanel.getMainPanel());
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    // MODIFIES: this
    // EFFECTS: adds given restaurant to the collection
    public void addRestaurantToCollection(Restaurant r) {
        restaurantCollection.addExistingRestaurant(r);
    }


    // EFFECTS: loads restaurant collection from file
    public void loadRestaurantCollection() {
        try {
            restaurantCollection = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Failed to load file");
        }
    }

    // EFFECTS: saves restaurant collection to file
    public void saveRestaurantCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(restaurantCollection);
            jsonWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to save to file");
        }
    }

    // EFFECTS: returns restaurant collection
    public ListOfRestaurant getRestaurantCollection() {
        return restaurantCollection;
    }

    // EFFECTS: returns mainFrame
    public JFrame getMainFrame() {
        return mainFrame;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.getDescription());
        }
    }
}