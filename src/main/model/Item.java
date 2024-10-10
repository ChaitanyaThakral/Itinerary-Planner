package model;

public class Item {
    private String name;
    private Boolean status;


    //EFFECTS: Constructs an item with its name and packing status (upon construction status set to false).
    public Item(String name,Boolean Status){
       this.name=name;
       this.status=false;
    }

    public String getName(){
        return this.name;
    }

    public Boolean getStatus(){
        return this.status;
    }

    //MODIFIES:this
    //EFFECTS: sets the name to the new provided name.
    public void setName(String name){
        this.name=name;
    }

    //MODIFIES:this
    //EFFECTS: sets the status to the new provided status.
    public void setStatus(Boolean status){
        this.status=status;
    }
}
