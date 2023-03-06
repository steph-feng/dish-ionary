package persistence;

import org.json.JSONObject;

/*
 * Enables persistence for classes in Model
 */

public interface Writable {

    // EFFECTS: returns this as a JSONObject
    JSONObject toJson();
}
