package model;

import java.time.LocalDate;

public class Activity {
    private String activityName;
    private String location;
    private String date;
    private int duration;// in minutes
    private String time;
    private String description;
    public double cost;
    private boolean status;

    //EFFECTS: creates an activity with its name,location of the activity, date, duratiom,
    // time taken to complete the activity, description, cost, Status of completion (boolean)
    public Activity(String activityName, String location, String date,int duration,String time,
                    String description,double cost,boolean status){
        this.activityName=activityName;
        this.location=location;
        this.date=date;
        this.duration=duration;
        this.time=time;
        this.description=description;
        this.cost=cost;
        this.status=status;
    }

    public String getActivityName(){
        return null;
    }

    public String getLocation(){
        return null;
    }

    public String getDate(){
        return null;
    }

    public int getDuration(){
        return 0;
    }
    
    public String getTime(){
        return null;
    }

    public String getDescription(){
        return null;
    }
    
    public double getCost(){
        return 0.0;
    }

    public Boolean getStatus(){
        return false;
    }

    //MODIFIES:this
    //EFFECTS: Set the activity name to the newly provided Activity name.
    public void setActivityName(){
        //stub
    }

    //MODIFIES:this
    //EFFECTS: Set the Location  to the newly provided Location.
    public void setLocation(){
        //stub
    }

    //MODIFIES:this
    //EFFECTS: Set the Date to the newly provided Date.
    public void setDate(){
       //stub
    }

    //MODIFIES:this
    //EFFECTS: Set the Duration to the newly provided Duration.
    public void setDuration(){
        //stub
    }
    
    //MODIFIES:this
    //EFFECTS: Set the time to the newly provided time.
    public void setTime(){
        //stub
    }

    //MODIFIES:this
    //EFFECTS: Set the Descrition to the newly provided Description
    public void setDescription(){
        //stub
    }
    
    //MODIFIES:this
    //EFFECTS: Set the Cost to the newly provided Cost.
    public void setCost(){
        //stub
    }

    //MODIFIES:this
    //EFFECTS: Set the Status to the newly provided Status.
    public void setStatus(){
       //stub
    }


}
