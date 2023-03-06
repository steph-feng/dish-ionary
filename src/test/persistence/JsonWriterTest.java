package persistence;

import model.BusinessHours;
import model.ListOfRestaurant;
import model.Restaurant;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/*
 * Unit tests for the JsonWriter class
 */

class JsonWriterTest {
    @Test
    public void nonExistentFileWriterTest() {
        try {
            JsonWriter testWriter = new JsonWriter("./data/x/json");
            testWriter.open();
            fail("Didn't throw IOException");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void emptyWriterTest() {
        try {
            ListOfRestaurant testList = new ListOfRestaurant();
            JsonWriter testWriter = new JsonWriter("./data/testWriterEmpty.json");
            testWriter.open();
            testWriter.write(testList);
            testWriter.close();

            JsonReader testReader = new JsonReader("./data/testWriterEmpty.json");
            testList = testReader.read();
            assertEquals(0, testList.getRestaurants().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void generalWriterTest() {
        try {
            ListOfRestaurant testList = new ListOfRestaurant();
            Restaurant r = new Restaurant("Sura");
            r.setPricing("$$$");
            r.setCuisine("Korean");
            r.setRating(9);
            r.addHours(new BusinessHours(3, "12:00", "21:00"));
            r.addHours(new BusinessHours(6, "11:00", "22:00"));
            testList.addExistingRestaurant(r);
            JsonWriter testWriter = new JsonWriter("./data/testWriterGeneral.json");
            testWriter.open();
            testWriter.write(testList);
            testWriter.close();

            JsonReader testReader = new JsonReader("./data/testWriterGeneral.json");
            ListOfRestaurant readList = testReader.read();
            assertEquals("Sura", readList.getRestaurants().get(0).getName());
            assertEquals("$$$", readList.getRestaurants().get(0).getPricing());
            assertEquals("Korean", readList.getRestaurants().get(0).getCuisine());
            assertEquals(9, readList.getRestaurants().get(0).getRating());
            assertEquals(2, readList.getRestaurants().get(0).getHours().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}