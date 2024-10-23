package persistence;

import org.json.JSONObject;

public interface Writeable {
    // Referenced from the JsonSerialization Demo
    //https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    
     // EFFECTS: returns this as JSON object
    JSONObject toJson();
    
}
