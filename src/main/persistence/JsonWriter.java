package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.json.JSONObject;

import model.Checklist;
import model.Trips;

/*
Represents a JSONWriter that will help in writing the JSON representation of Trip Itinerary and checklist into a file.
*/
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a writer that will help to write into a required
    // destination.
    public JsonWriter(String destination) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: Opens the writer and throws a FileNotFoundException
    // if the destination file cannot be accessed for writing.
    public void open() throws FileNotFoundException {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: closes the writer.
    public void close() {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: saves the given string to the file.
    private void save(String json) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of checklist to file.
    public void writeChecklist(Checklist checklist) {
        // stub
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of trips to file.
    public void writeTrips(Trips trip) {
        // stub
    }
}
