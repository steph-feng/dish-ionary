package ui.gui;

import model.ListOfRestaurant;
import persistence.JsonReader;
import persistence.JsonWriter;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class RestaurantManagerApp {
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
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void switchToSavePanel() {
        mainFrame.setContentPane(savePanel.getSavePane());
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
    }

    public void switchToLoadPanel() {
        mainFrame.setContentPane(loadPanel.getLoadPane());
        mainFrame.validate();
        mainFrame.repaint();
        mainFrame.pack();
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
        restaurantCollection.addExistingRestaurant(addPanel.getNewestRestaurant());
    }

    public void loadRestaurantCollection() {
        try {
            restaurantCollection = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Failed to load file");
        }
    }

    public void saveRestaurantCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(restaurantCollection);
            jsonWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to save to file");
        }
    }

    public ListOfRestaurant getRestaurantCollection() {
        return restaurantCollection;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JsonReader getJsonReader() {
        return jsonReader;
    }

    public JsonWriter getJsonWriter() {
        return jsonWriter;
    }
}