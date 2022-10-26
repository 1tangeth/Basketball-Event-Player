package persistence;

import org.json.JSONObject;

//TODO INCLUDE CITATION
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
