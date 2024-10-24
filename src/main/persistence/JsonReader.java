package persistence;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Checklist;
import model.DestinationItinerary;
import model.Item;
import model.Trips;
import model.Activity;
import model.Budget;

import java.util.ArrayList;
import java.util.List;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads the trip itinerary and checklist from JSON data stored in file.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file.
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: Fetches a checklist from the specified file and returns it as a trip
    // object;
    // throws IOException if an error occurs while reading the file.
    public Trips readTrips() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTrips(jsonObject);
    }

    // EFFECTS: Fetches a checklist from the specified file and returns it as a
    // checklist object;
    // throws IOException if an error occurs while reading the file.
    public Checklist readChecklist() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseChecklist(jsonObject);
    }

    // EFFECTS: Reads the content of the given source file and returns it as a
    // string.
    // throws IOException if an error occurs while reading the file.
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses trip from JSON object and returns it.
    private Trips parseTrips(JSONObject jsonObject) {
        String city = jsonObject.getString("City");
        String country = jsonObject.getString("Country");
        String tripType = jsonObject.getString("Trip Type");

        Trips trip = new Trips(city, country, tripType);

       

            JSONArray itinerary = jsonObject.getJSONArray("itinerary");

            for (int i = 0; i < itinerary.length(); i++) {
                JSONObject iJsonObject = itinerary.getJSONObject(i);
                DestinationItinerary finalItinerary = parseDestinationItinerary(iJsonObject);
                trip.addDestinationItineraries(finalItinerary);
            

        }
        return trip;

    }


    // EFFECTS: parses a DestinationItinerary from the given JSON object and returns
    // it.
    private DestinationItinerary parseDestinationItinerary(JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        int dayNumber = jsonObject.getInt("dayNumber");

        List<Activity> activityList = new ArrayList<Activity>();
        JSONArray activities = jsonObject.getJSONArray("activities");

        for (int i = 0; i < activities.length(); i++) {
            JSONObject aJson = activities.getJSONObject(i);
            Activity newActivity = parseActivity(aJson);
            activityList.add(newActivity);
        }

        DestinationItinerary finaldestinationitinerary = new DestinationItinerary(date, dayNumber, activityList);
        return finaldestinationitinerary;

    }

    // EFFECTS: parses a Activity object from the provided JSON object and returns
    // it.
    private Activity parseActivity(JSONObject jsonObject) {
    
        String activityName = jsonObject.getString("activityName");
        String location = jsonObject.getString("location");
        String date = jsonObject.getString("date");
        int duration = jsonObject.getInt("duration");
        String time = jsonObject.getString("time");
        String description = jsonObject.getString("description");
        Double cost = jsonObject.getDouble("cost");
        Boolean status = jsonObject.getBoolean("status");

        Budget budget = null;
       
            JSONObject newBudget = jsonObject.getJSONObject("budget");
            budget= parseBudget(newBudget);

        
        Activity newActivity = new Activity(activityName, location, date, duration, time, description, cost, status, budget);
        return newActivity;

    }

    // EFFECTS: parses a Budget object from the provided JSON object and returns
    // it.
    private Budget parseBudget(JSONObject jsonObject) {
       Double budgetLimit = jsonObject.getDouble("budgetLimit");
       Double currentExpenditure = jsonObject.getDouble("currentExpenditure");
        
       Budget newBudget =  new Budget(budgetLimit,currentExpenditure);
       return newBudget;
    }

    // EFFECTS: parses a Checklist object from the provided JSON object and returns
    // it.
    private Checklist parseChecklist(JSONObject jsonObject) {
        Checklist checklist = new Checklist();

        
            JSONArray itemList = jsonObject.getJSONArray("checklist");
    
            // Iterate through each item in the JSON array
            for (int i = 0; i < itemList.length(); i++) {
                JSONObject itemJson = itemList.getJSONObject(i);
                Item newItem = parseItem(itemJson); 
                checklist.addItem(newItem); 
            
        }

        return checklist;
    }

    // EFFECTS: parses the items of the checklist from the provided JSON object and
    // returns it.
    private Item parseItem(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Boolean status = jsonObject.getBoolean("status");

        Item newItemformed= new Item(name, status);
        return newItemformed;
    }

}
