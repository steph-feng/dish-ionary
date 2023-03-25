package ui;

/*
 * Runs RestaurantManagerApp
 */

import ui.gui.RestaurantManagerApp;

//public class Main {
//    // EFFECTS: initializes new restaurant manager application
//    public static void main(String[] args) {
//        try {
//            new ConsoleRestaurantManagerApp();
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found, unable to run application.");
//        }
//    }
//}

public class Main {
    // EFFECTS: initializes new restaurant manager application
    public static void main(String[] args) {
        new RestaurantManagerApp();
    }
}
