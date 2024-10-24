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
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: Opens the writer and throws a FileNotFoundException
    // if the destination file cannot be accessed for writing.
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer.
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: saves the given string to the file.
    private void save(String json) {
        writer.print(json);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of checklist to file.
    public void writeChecklist(Checklist checklist) {
        JSONObject json = checklist.toJson();
        save(json.toString(TAB));
    }
    

    // MODIFIES: this
    // EFFECTS: writes JSON representation of trips to file.
    public void writeTrips(Trips trip) {
        JSONObject json = trip.toJson();
        save(json.toString(TAB));
    }

}

