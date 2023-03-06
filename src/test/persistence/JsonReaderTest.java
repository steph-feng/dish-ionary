package persistence;

import model.ListOfRestaurant;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * Unit tests for the JsonReader class
 */

class JsonReaderTest {
    @Test
    public void nonExistentFileReaderTest() {
        JsonReader testReader = new JsonReader("./data/myFile.json");
        try {
            ListOfRestaurant restaurants = testReader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void emptyFileReaderTest() {
        JsonReader testReader = new JsonReader("./data/testReaderEmptyFile.json");
        try {
            ListOfRestaurant list = testReader.read();
            assertEquals(0, list.getRestaurants().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void generalFileReaderTest() {
        JsonReader testReader = new JsonReader("./data/testReaderFile.json");
        try {
            ListOfRestaurant list = testReader.read();
            assertEquals(1, list.getRestaurants().size());
            assertEquals("Nook", list.getRestaurants().get(0).getName());
            assertEquals(8, list.getRestaurants().get(0).getRating());
            assertEquals("$$", list.getRestaurants().get(0).getPricing());
            assertEquals("Italian", list.getRestaurants().get(0).getCuisine());
            assertEquals(7, list.getRestaurants().get(0).getHours().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}



