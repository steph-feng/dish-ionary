package persistence;

import model.BusinessHours;
import model.ListOfRestaurant;
import model.Restaurant;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


/*
 * A reader that reads JSON interpretation of ListOfRestaurant to file
 * Attribution: modelled on JsonSerializationDemo sample application
 */

public class JsonReader {
    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfRestaurant from file and returns it;
    //          throws IOException if an error occurs while reading the file
    public ListOfRestaurant read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfRestaurant(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfRestaurant from JSON object and returns it
    private ListOfRestaurant parseListOfRestaurant(JSONObject jsonObject) {
        ListOfRestaurant list = new ListOfRestaurant();
        addListOfRestaurant(list, jsonObject);
        return list;
    }

    // MODIFIES: list
    // EFFECTS: parses restaurants from JSON object and adds them to list
    private void addListOfRestaurant(ListOfRestaurant list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("restaurants");
        for (Object json : jsonArray) {
            JSONObject nextRestaurant = (JSONObject) json;
            addRestaurant(list, nextRestaurant);
        }
    }

    // MODIFIES: list
    // EFFECTS: parses restaurant from JSON object and adds it to list
    private void addRestaurant(ListOfRestaurant list, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int rating = jsonObject.getInt("rating");
        String cuisine = jsonObject.getString("cuisine");
        String pricing = jsonObject.getString("pricing");
        Restaurant r = new Restaurant(name);
        r.setRating(rating);
        r.setCuisine(cuisine);
        r.setPricing(pricing);
        addRestaurantHours(r, jsonObject);
        list.addExistingRestaurant(r);
    }

    // EFFECTS: parses List<BusinessHours> from JSON object
    private void addRestaurantHours(Restaurant r, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("hours");
        for (Object json : jsonArray) {
            JSONObject nextHour = (JSONObject) json;
            addRestaurantHour(r, nextHour);
        }
    }

    // MODIFIES: r
    // EFFECTS: parses BusinessHours from JSON object and adds it to r
    private void addRestaurantHour(Restaurant r, JSONObject nextHour) {
        int day = nextHour.getInt("day");
        String open = nextHour.getString("open");
        String close = nextHour.getString("close");
        BusinessHours hour = new BusinessHours(day, open, close);
        r.addHours(hour);
    }
}
