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

    public Activity(String activityName, String location, String string,int duration,String time,
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

    
    
}
