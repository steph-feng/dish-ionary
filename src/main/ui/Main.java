package ui;

/*
 * Runs RestaurantManagerApp
 */

import java.io.FileNotFoundException;

public class Main {
    // EFFECTS: initializes new restaurant manager application
    public static void main(String[] args) {
        try {
            new RestaurantManagerApp();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, unable to run application.");
        }
    }
}
