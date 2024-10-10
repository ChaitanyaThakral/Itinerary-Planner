package model;

public class Activity {
    private String activityName;
    private String location;
    private String date;
    private int duration;// in minutes
    private String time;
    private String description;
    public double cost;
    private boolean status;
    private Budget budget;

    //EFFECTS: creates an activity with its name,location of the activity, date, duratiom,
    // time taken to complete the activity, description, cost, Status of completion (boolean)
    public Activity(String activityName, String location, String date,int duration,String time,
                    String description,double cost,boolean status,Budget budget){
        this.activityName=activityName;
        this.location=location;
        this.date=date;
        this.duration=duration;
        this.time=time;
        this.description=description;
        this.cost=cost;
        this.status=status;
        this.budget=budget;
    }

    public String getActivityName(){
        return this.activityName;
    }

    public String getLocation(){
        return this.location;
    }

    public String getDate(){
        return this.date;
    }

    public int getDuration(){
        return this.duration;
    }
    
    public String getTime(){
        return this.time;
    }

    public String getDescription(){
        return this.description;
    }
    
    public double getCost(){
        return this.cost;
    }

    public Boolean getStatus(){
        return this.status;
    }

    public Budget getBudget(){
        return this.budget;
    }

    public Boolean budgetCheck(){
        return this.cost<=budget.getBudgetLimit();
    }
    
    //MODIFIES:this
    //EFFECTS: Set the activity name to the newly provided Activity name.
    public void setActivityName(String activityName){
        this.activityName=activityName;
    }

    //MODIFIES:this
    //EFFECTS: Set the Location  to the newly provided Location.
    public void setLocation(String location){
        this.location=location;
    }

    //MODIFIES:this
    //EFFECTS: Set the Date to the newly provided Date.
    public void setDate(String date){
       this.date=date;
    }

    //MODIFIES:this
    //EFFECTS: Set the Duration to the newly provided Duration.
    public void setDuration(int duration){
        this.duration=duration;
    }
    
    //MODIFIES:this
    //EFFECTS: Set the time to the newly provided time.
    public void setTime(String time){
        this.time=time;
    }

    //MODIFIES:this
    //EFFECTS: Set the Descrition to the newly provided Description
    public void setDescription(String description){
        this.description=description;
    }
    
    //MODIFIES:this
    //EFFECTS: Set the Cost to the newly provided Cost.
    public void setCost(double cost){
        this.cost=cost;
    }

    //MODIFIES:this
    //EFFECTS: Set the Status to the newly provided Status.
    public void setStatus(Boolean status){
       this.status=status;
    }


}
