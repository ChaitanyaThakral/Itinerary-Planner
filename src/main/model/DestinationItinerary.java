package model;

import java.util.List;

import org.json.JSONObject;

import persistence.Writeable;

/* Represents a Destination Itinerary with date, dayNumber 
and List of all the activities user may be doing  throughout the trip
model has date: date of itinerary.(has to be String), dayNumber: day number of trip (like Day 1)(has to int)
activity: Comprises of the list of activity.
*/

public class DestinationItinerary implements Writeable {
    private String date;
    private int dayNumber;
    private List<Activity> activity;

    // EFFECTS: constructs a Destination Itinerary with date, dayNumber
    // and List of all the activities user may be doing.
    public DestinationItinerary(String date, int dayNumber, List<Activity> activity) {
        this.date = date;
        this.dayNumber = dayNumber;
        this.activity = activity;
    }

    public String getDate() {
        return this.date;
    }

    public int getDayNumber() {
        return this.dayNumber;
    }

    public List<Activity> getActivity() {
        return this.activity;
    }
    // MODIFIES:this
    // EFFECTS: Set the date to the provided new date.

    public void setDate(String date) {
        this.date = date;
    }

    // MODIFIES:this
    // EFFECTS: Set the day number to the provided new day number.
    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    // MODIFIES:this
    // EFFECTS: add a new activity to the list of activities.
    public void addActivity(Activity newActivity) {
        activity.add(newActivity);
    }

    // EFFECTS: returns the total cost of the activity.
    public double getTotalCost() {
        double sum = 0;
        for (int i = 0; i < this.activity.size(); i++) {
            Activity act = activity.get(i);
            sum = sum + act.getCost();
        }
        return sum;
    }

    // REQUIRES: the DestinationItinerary instance should not be null.
    // EFFECTS: returns a JSONObject representation of this Destination Itinerary
    // with date, dayNumber and List of all the activities.
    @Override
    public JSONObject toJson() {
        return null;
    }

}
