package model;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class DestinationItinerary {
    private String date;
    private int dayNumber;
    private List<Activity> activity;

    // EFFECTS: constructs a Destination Itinerary with date, dayNumber 
    //and List of all the activities user may be doing.
    public DestinationItinerary(String date,int dayNumber, List<Activity> activity){
     //stub
    }

    public String getDate(){
        return null;
    }

    public int getDayNumber(){
        return 0;
    }

    public List<Activity> getActivity(){
        return null;
    }

    //MODIFIES:this
    //EFFECTS: Set the date to the provided new date.
    public void setDate(String date){
        //stub
    }
    
    //MODIFIES:this
    //EFFECTS: Set the day number to the provided new day number.
    public void setDayNumber(int dayNumber){
        //stub
    }
    
    //MODIFIES:this
    //EFFECTS: add a new activity to the list of activities.
    public void addActivity(Activity activity){
        //stub
    }

    //EFFECTS: returns the total cost of the activity.
    public double getTotalCost(){
        return 0;
    }
}