package ui;

import model.BusinessHours;
import model.ListOfRestaurant;
import model.Restaurant;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Restaurant manager application
 * Attribution: class structure inspired by TellerApp, data persistence inspired by JsonSerializationDemo
 */

public class RestaurantManagerApp {
    private static final String JSON_STORE = "./data/restaurantCollection.json";
    private Scanner input;
    private ListOfRestaurant restaurantCollection;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the application
    public RestaurantManagerApp() throws FileNotFoundException {
        restaurantCollection = new ListOfRestaurant();
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        boolean continueRunning = true;
        String userInput = null;

        System.out.println("Welcome!");

        while (continueRunning) {
            displayMenu();
            userInput = input.next();

            if (userInput.equals("quit")) {
                continueRunning = false;
            } else {
                processInitialInput(userInput);
            }
        }

        System.out.println("See you next time!");
    }

    // EFFECTS: displays options to user
    public void displayMenu() {
        System.out.println("\nSelect one of the following:");
        System.out.println("\t\"add\" to add a restaurant to your collection");
        System.out.println("\t\"find\" to access your restaurant collection");
        System.out.println("\t\"load\" to load previous restaurant collection from file");
        System.out.println("\t\"save\" to save current restaurant collection to file");
        System.out.println("\t\"quit\" to exit the application");
    }

    // EFFECTS: processes the user's input
    public void processInitialInput(String userInput) {
        if (userInput.equals("add")) {
            addRestaurant();
        } else if (userInput.equals("find")) {
            System.out.println("How can I help you today?");
            System.out.println("\t\"m\" to modify a restaurant already in the collection");
            System.out.println("\t\"v\" to view all the restaurants in the collection");
            System.out.println("\t\"f\" to filter the list based on cuisine, rating, pricing, or business hours");
            System.out.println("\t\"s\" to sort the list based on rating or pricing");
            System.out.println("\t\"r\" to remove a restaurant from the collection");
            System.out.println("\t\"random\" to get a random restaurant recommendation");
            String nextInput = input.next();
            processAccessRestaurantsInput(nextInput);
        } else if (userInput.equals("load")) {
            loadRestaurantCollection();
        } else if (userInput.equals("save")) {
            saveRestaurantCollection();
        } else {
            System.out.println("Selection invalid, please try again.");
        }
    }


    // EFFECTS: adds a restaurant to the list
    public void addRestaurant() {
        System.out.println("What is the name of the restaurant?");
        String name = input.next();
        Restaurant newestRestaurant = new Restaurant(name);
        restaurantCollection.addExistingRestaurant(newestRestaurant);
        addRestaurantDetails(newestRestaurant);
    }

    // EFFECTS: adds cuisine, rating, pricing, or hours to the restaurant
    public void addRestaurantDetails(Restaurant newestRestaurant) {
        boolean addDetails = true;
        while (addDetails) {
            restaurantDetails();
            String selection = input.next();
            if (selection.equals("r")) {
                addRestaurantRating(newestRestaurant);
            } else if (selection.equals("c")) {
                addRestaurantCuisine(newestRestaurant);
            } else if (selection.equals("p")) {
                addRestaurantPricing(newestRestaurant);
            } else if (selection.equals("h")) {
                addRestaurantHours(newestRestaurant);
            } else if (selection.equals("no")) {
                addDetails = false;
            } else {
                System.out.println("Invalid selection, please try again.");
            }
        }
    }

    // EFFECTS: displays options to edit restaurant to user
    public void restaurantDetails() {
        System.out.println("To add more details, input:");
        System.out.println("\"r\" for rating");
        System.out.println("\"c\" for cuisine");
        System.out.println("\"p\" for pricing");
        System.out.println("\"h\" for business hours");
        System.out.println("\"no\" if not");
    }

    // EFFECTS: user adds rating to restaurant
    public void addRestaurantRating(Restaurant r) {
        System.out.println("Enter a rating between 0 and 10.");
        String rating = input.next();
        int numberRating = Integer.parseInt(rating);
        r.setRating(numberRating);
    }

    // EFFECTS: user adds cuisine to restaurant
    public void addRestaurantCuisine(Restaurant r) {
        System.out.println("Enter the cuisine of the restaurant!");
        String cuisine = input.next();
        r.setCuisine(cuisine);
    }

    // EFFECTS: user adds pricing to restaurant
    public void addRestaurantPricing(Restaurant r) {
        System.out.println("Enter one of: \"$\", \"$$\", \"$$$\", or \"$$$$\"");
        String price = input.next();
        r.setPricing(price);
    }

    // EFFECTS: user adds hours of week to restaurant
    public void addRestaurantHours(Restaurant r) {
        r.addHours(addHoursOfDay("Monday"));
        r.addHours(addHoursOfDay("Tuesday"));
        r.addHours(addHoursOfDay("Wednesday"));
        r.addHours(addHoursOfDay("Thursday"));
        r.addHours(addHoursOfDay("Friday"));
        r.addHours(addHoursOfDay("Saturday"));
        r.addHours(addHoursOfDay("Sunday"));
    }

    // EFFECTS: helper to get user input for the hours each day
    private BusinessHours addHoursOfDay(String s) {
        System.out.println("At what time does the restaurant open on " + s + "?");
        System.out.println("Enter a number in the format XX:YY, where XX is between [00,23] and YY is between [00,60]."
                + " XX and YY must both be two digits long.");
        String openHour = input.next();
        System.out.println("At what hour does the restaurant close on " + s + "?");
        System.out.println("Enter a number in the format XX:YY, where XX is between [00,23] and YY is between [00,60]."
                + " XX and YY must both be two digits long.");
        String closeHour = input.next();

        int day = dayToInt(s);
        BusinessHours hoursOfDay = new BusinessHours(day, openHour, closeHour);

        return hoursOfDay;
    }

    // EFFECTS: helper to get corresponding int from day of week
    private int dayToInt(String s) {
        if (s.equals("Monday")) {
            return 1;
        } else if (s.equals("Tuesday")) {
            return 2;
        } else if (s.equals("Wednesday")) {
            return 3;
        } else if (s.equals("Thursday")) {
            return 4;
        } else if (s.equals("Friday")) {
            return 5;
        } else if (s.equals("Saturday")) {
            return 6;
        } else {
            return 7;
        }
    }

    // EFFECTS: processes further input to access restaurant collection
    public void processAccessRestaurantsInput(String userInput) {
        if (userInput.equals("m")) {
            modifyExistingRestaurant();
        } else if (userInput.equals("v")) {
            viewRestaurants();
        } else if (userInput.equals("f")) {
            filterRestaurants();
        } else if (userInput.equals("s")) {
            sortRestaurants();
        } else if (userInput.equals("r")) {
            doRemoveRestaurant();
        } else if (userInput.equals("random")) {
            randomizeRestaurant();
        }
    }

    // EFFECTS: user can edit restaurant already in list
    public void modifyExistingRestaurant() {
        System.out.println("Please enter the name of the restaurant you would like to edit");
        String nextCommand = input.next();
        Restaurant givenRestaurant = null;
        for (Restaurant r : restaurantCollection.getRestaurants()) {
            if (r.getName().equals(nextCommand)) {
                givenRestaurant = r;
            }
        }
        if (!(givenRestaurant == null)) {
            addRestaurantDetails(givenRestaurant);
        } else {
            System.out.println("No restaurant with that name found, please try again");
        }
    }

    // EFFECTS: displays all restaurant names and details in the collection
    public void viewRestaurants() {
        if (restaurantCollection.getRestaurants().size() == 0) {
            System.out.println("There are no restaurants in the collection.");
        } else {
            for (Restaurant r : restaurantCollection.getRestaurants()) {
                System.out.println("Restaurant: " + r.getName());
                System.out.println("\tRating: " + r.getRating());
                System.out.println("\tPricing: " + r.getPricing());
                System.out.println("\tCuisine: " + r.getCuisine());
                System.out.println("\tBusiness Hours: ");
                for (BusinessHours b : r.getHours()) {
                    System.out.println("\t\t" + b.getDayOfWeek() + ": "
                            + b.getOpeningHours() + " - " + b.getClosingHours());
                }
            }
        }
    }

    // EFFECTS: processes input to filter restaurant collection
    public void filterRestaurants() {
        System.out.println("Enter to filter by");
        System.out.println("\t\"r\" for rating");
        System.out.println("\t\"p\" for pricing");
        System.out.println("\t\"h\" for hours");
        System.out.println("\t\"c\" for cuisine");
        String nextCommand = input.next();
        if (nextCommand.equals("r")) {
            filterByRating();
        } else if (nextCommand.equals("p")) {
            filterByPricing();
        } else if (nextCommand.equals("h")) {
            filterByHours();
        } else if (nextCommand.equals("c")) {
            filterByCuisine();
        } else {
            System.out.println("Invalid input, please try again");
        }

    }

    // EFFECTS: helper to display restaurant names and details of given list
    private void displayRestaurants(ArrayList<Restaurant> restaurants) {
        if (restaurants.size() == 0) {
            System.out.println("There are no restaurants that meet the given criteria.");
        } else {
            for (Restaurant r : restaurants) {
                System.out.println("Restaurant: " + r.getName());
                System.out.println("\tRating: " + r.getRating());
                System.out.println("\tPricing: " + r.getPricing());
                System.out.println("\tCuisine: " + r.getCuisine());
                System.out.println("\tBusiness Hours: ");
                for (BusinessHours b : r.getHours()) {
                    System.out.println("\t\t" + b.getDayOfWeek() + ": " + b.getOpeningHours()
                            + " - " + b.getClosingHours());
                }
            }
        }
    }

    // EFFECTS: filters collection by user's input rating
    private void filterByRating() {
        System.out.println("Find restaurants that have a rating greater than or equal"
                + " to an input rating between 0 and 10");
        int rating = Integer.parseInt(input.next());
        displayRestaurants(restaurantCollection.filterRatings(rating));
    }

    // EFFECTS: filters collection by user's input pricing
    private void filterByPricing() {
        System.out.println("Find restaurants that are at the given price point, "
                + "one of \"$\", \"$$\", \"$$$\", or \"$$$$\"");
        String price = input.next();
        displayRestaurants(restaurantCollection.filterPrices(price));
    }

    // EFFECTS: filters collection by user's input time
    private void filterByHours() {
        System.out.println("Enter");
        System.out.println("\t\"now\" to find restaurants that are open now");
        System.out.println("\t\"later\" to find restaurants that are open at a personalized time");
        String selection = input.next();
        if (selection.equals("now")) {
            LocalDateTime currentTime = LocalDateTime.now();
            displayRestaurants(restaurantCollection.filterOpenHours(currentTime));
        } else if (selection.equals("later")) {
            System.out.println("Enter the date and time, beginning with the year");
            int year = Integer.parseInt(input.next());
            System.out.println("Enter the month as a number between 1 and 12");
            int month = Integer.parseInt(input.next());
            System.out.println("Enter the date as a number between 1 and 31");
            int date = Integer.parseInt(input.next());
            System.out.println("Enter the hour as a number between 0 and 23");
            int hour = Integer.parseInt(input.next());
            System.out.println("Enter the minute as a number between 0 and 60");
            int minute = Integer.parseInt(input.next());
            LocalDateTime givenTime = LocalDateTime.of(year, month, date, hour, minute);
            displayRestaurants(restaurantCollection.filterOpenHours(givenTime));
        } else {
            System.out.println("Invalid input, please try again");
        }
    }

    // EFFECTS: filters collection by user's input cuisine
    private void filterByCuisine() {
        System.out.println("Find restaurants that serve the given cuisine");
        String cuisine = input.next();
        displayRestaurants(restaurantCollection.filterCuisine(cuisine));
    }

    // EFFECTS: sorts restaurant collection by user request
    private void sortRestaurants() {
        System.out.println("Enter");
        System.out.println("\t\"p\" to sort the restaurants by price in ascending order");
        System.out.println("\t\"r\" to sort the restaurants by rating in descending order");
        String nextCommand = input.next();
        if (nextCommand.equals("p")) {
            displayRestaurants(restaurantCollection.sortPrices());
        } else if (nextCommand.equals("r")) {
            displayRestaurants(restaurantCollection.sortRatings());
        } else {
            System.out.println("Invalid input, please try again");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes restaurant and displays the restaurants left in the collection
    //          does nothing if input is invalid
    public void doRemoveRestaurant() {
        System.out.println("Enter the name of the restaurant you would like to remove");
        String removedRestaurant = input.next();
        int sizeBeforeRemoval = restaurantCollection.getRestaurants().size();
        restaurantCollection.removeRestaurant(removedRestaurant);
        int sizeAfterRemoval = restaurantCollection.getRestaurants().size();
        if (sizeAfterRemoval < sizeBeforeRemoval) {
            System.out.println(removedRestaurant + " has been removed.");
            if (restaurantCollection.getRestaurants().size() == 0) {
                System.out.println("There are no restaurants left in the collection.");
            } else {
                System.out.println("The restaurants left in the collection are:");
                viewRestaurants();
            }
        } else {
            System.out.println("There is no restaurant with that name in the collection, no restaurant was removed.");
        }
    }

    // EFFECTS: displays a random restaurant from the list
    public void randomizeRestaurant() {
        System.out.println("Your random restaurant recommendation is: "
                + restaurantCollection.randomRestaurant().getName());

    }

    // MODIFIES: restaurantCollection
    // EFFECTS: loads restaurant collection from file
    public void loadRestaurantCollection() {
        try {
            restaurantCollection = jsonReader.read();
            System.out.println("Loaded restaurants from: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Failed to load file");
        }
    }

    // EFFECTS: saves RestaurantCollection to file
    public void saveRestaurantCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(restaurantCollection);
            jsonWriter.close();
            System.out.println("Successfully saved restaurant collection to: " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Failed to save to file");
        }
    }
}
