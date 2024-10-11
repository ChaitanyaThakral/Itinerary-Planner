package model;

/* Creates an item with its name and packing status i.e. whether the item is packed or not.  */
public class Item {
    private String name;
    private Boolean status;


    //EFFECTS: Constructs an item with its name and packing status (upon construction status set to false).
    public Item(String name,Boolean status){
       this.name=name;
       this.status=status;
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
