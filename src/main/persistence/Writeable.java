package persistence;

import org.json.JSONObject;

/*
 * Represents an interface which helps in serialization of objects to json.Class implementing
 * this interface should provide the method to convert its state into json.
 */
public interface Writeable {
    // Referenced from the JsonSerialization Demo
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

    // EFFECTS: returns this as JSON object
    JSONObject toJson();

}
