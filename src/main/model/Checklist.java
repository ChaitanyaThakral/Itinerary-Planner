package model;
import java.util.List;

public class Checklist {
    private List<Item> checklist;

    //EFFECTS: creates a checklist with the list of items present.
    public Checklist(){
        //stub
    }

    //EFFECTS:return the total number of items in the checklist
    public int totalItems(){
        return 0;
    }

    //EFFECTS:return the total number of items in the checklist yet to pack.
    public int remainingItems(){
        return 0;
    }

    //MODIFIES:this
    //EFFECTS: add a new item into the checklist.
    public void addItem(){
        //stub
    }

    //EFFECTS: return the list of all the items in the checklist.
    public List<Item> getChecklist(){
        return null;
    }

    //MODIFIES:this
    //EFFECTS: removes an items from the list.
    public void removeItem(){
        //stub
    }
}
