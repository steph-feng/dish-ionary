package persistence;

import model.ListOfRestaurant;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 * A writer that write JSON representation of ListOfRestaurant to file
 * Attribution: modelled on JsonSerializationDemo sample application
 */

public class JsonWriter {
    private int indentFactor;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer;
    //          throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ListOfRestaurant to file
    public void write(ListOfRestaurant restaurants) {
        JSONObject json = restaurants.toJson();
        saveToFile(json.toString(indentFactor));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }


}
