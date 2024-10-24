package persistence;

import java.io.IOException;

import org.json.JSONObject;

import model.Checklist;
import model.DestinationItinerary;
import model.Item;
import model.Trips;

// Represents a reader that reads the trip itinerary and checklist from JSON data stored in file.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file.
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS:  Fetches a checklist from the specified file and returns it as a trip object;
    // throws IOException if an error occurs while reading the file.
    public Trips readTrips() throws IOException {
       return null;
    }

    // EFFECTS:  Fetches a checklist from the specified file and returns it as a checklist object;
    // throws IOException if an error occurs while reading the file.
    public Checklist readChecklist() throws IOException {
        return null;
     }

     // EFFECTS: Reads the content of the given source file and returns it as a string.
     // throws IOException if an error occurs while reading the file.
    private String readFile(String source) throws IOException {
        return null;
    }
    
    // EFFECTS: parses trip from JSON object and returns it.
    private Trips parseTrips(JSONObject jsonObject) {
        return null;
    }

    // MODIFIES: trip
    // EFFECTS: parses itinerary from the JSON object and adds it to the provided trip.
    private void addItinerary(Trips trip, JSONObject jsonObject) {
        // stub
    }

    // EFFECTS: parses a DestinationItinerary  from the given JSON object and returns it.
    private DestinationItinerary parseDestinationItinerary(JSONObject jsonObject) {
        return null;
    }

    // EFFECTS: parses a Checklist object from the provided JSON object and returns it.
    private Checklist parseChecklist(JSONObject jsonObject) {
       return null;
    }

    // EFFECTS: parses the items of the checklist from the provided JSON object and returns it.
    private Item parseChecklistItem(JSONObject jsonObject) {
        return null;
    }

}
