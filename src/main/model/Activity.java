package model;

import org.json.JSONObject;

import persistence.Writeable;

/* 
 * Represents an activity within a travel itinerary.That the user will 
 * go throughout a particular day with all the important details like:
 *  activityName, location, date, duration in minutes, time, description, cost, status, budget for an activity.
 * 
 * Activity has activityName: the name of the activity user will perform (has to be String)
 *  location: the location where the activity is done. (has to be String)
 * date: the date when the activity is scheduled. (has to be String)
 *  duration: the duration of the activity in minutes. (has to be int)
 * time: time the activity took place at (has to be String)(like "10:00 AM")
 * description: description of the activity.(has to be String)
 * cost: the total cost for the activity.(has to be double)
 *  status: indicates whether the activity was completed or not.(has to be boolean)
 * budget: an instance of the Budget class representing the budget for this activity.
*/

public class Activity implements Writeable  {
    private String activityName;
    private String location;
    private String date;
    private int duration;// in minutes
    private String time;
    private String description;
    private double cost;
    private boolean status;
    private Budget budget;

    // EFFECTS: creates an activity with its name,location of the activity, date,
    // duratiom,
    // time taken to complete the activity, description, cost, Status of completion
    // (boolean) and budget.
    public Activity  (String activityName, String location, String date, int duration, String time,
            String description, double cost, boolean status, Budget budget) {
        this.activityName = activityName;
        this.location = location;
        this.date = date;
        this.duration = duration;
        this.time = time;
        this.description = description;
        this.cost = cost;
        this.status = status;
        this.budget = budget;
    }

    public String getActivityName() {
        return this.activityName;
    }

    public String getLocation() {
        return this.location;
    }

    public String getDate() {
        return this.date;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getTime() {
        return this.time;
    }

    public String getDescription() {
        return this.description;
    }

    public double getCost() {
        return this.cost;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public Budget getBudget() {
        return this.budget;
    }

    // EFFECTS: Checks if the cost of the activity is within the budget limit.
    // return true if the cost is less than or equal to the budget limit and false
    // if not.

    public Boolean budgetCheck() {
        return this.cost <= budget.getBudgetLimit();
    }

    // MODIFIES:this
    // EFFECTS: Set the activity name to the newly provided Activity name.
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    // MODIFIES:this
    // EFFECTS: Set the Location to the newly provided Location.
    public void setLocation(String location) {
        this.location = location;
    }

    // MODIFIES:this
    // EFFECTS: Set the Date to the newly provided Date.
    public void setDate(String date) {
        this.date = date;
    }

    // MODIFIES:this
    // EFFECTS: Set the Duration to the newly provided Duration.
    public void setDuration(int duration) {
        this.duration = duration;
    }

    // MODIFIES:this
    // EFFECTS: Set the time to the newly provided time.
    public void setTime(String time) {
        this.time = time;
    }

    // MODIFIES:this
    // EFFECTS: Set the Descrition to the newly provided Description
    public void setDescription(String description) {
        this.description = description;
    }

    // MODIFIES:this
    // EFFECTS: Set the Cost to the newly provided Cost.
    public void setCost(double cost) {
        this.cost = cost;
    }

    // MODIFIES:this
    // EFFECTS: Set the Status to the newly provided Status.
    public void setStatus(Boolean status) {
        this.status = status;
    }

    // REQUIRES: the Acitivity instance should not be null.
    // EFFECTS: returns a JSONObject representation of this Acitivity instance with
    // its name, location of the activity, date, duratiom, time taken to complete the
    // activity, description,cost, Status of completion (boolean) and budget.

    @Override
    public JSONObject toJson() {
        return null;
    }

}
