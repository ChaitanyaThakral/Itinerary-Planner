package model;

import java.util.ArrayList;
import java.util.List;

/*Represents a checklist with all the list of items which one may require before the trip, 
user can add,remove,find,remaining items in the checklist 
checklist is initialized as list of Item.*/

public class Checklist {
    private List<Item> checklist;

    //EFFECTS: creates a checklist with the list of items present.
    public Checklist() {
        checklist = new ArrayList<Item>();
    }

    //EFFECTS:return the total number of items in the checklist
    public int totalItems() {
        return checklist.size();
    }

    //EFFECTS:return the total number of items in the checklist yet to pack.
    public int remainingItems() {
        int count = 0;
        for (int i = 0;i < checklist.size();i++) {
            if (checklist.get(i).getStatus() == false) {
                count++;
            }
        }
        return count;
    }

    //MODIFIES:this
    //EFFECTS: add a new item into the checklist.
    public void addItem(Item item) {
        checklist.add(item);
    }

    //EFFECTS: return the list of all the items in the checklist.
    public List<Item> getChecklist() {
        return checklist;
    }

    //MODIFIES:this
    //EFFECTS: removes an items from the list.
    public void removeItem(Item item) {
        checklist.remove(item);
    }

    // EFFECTS: returns a list of all the items which are yet to be packed.
    public List<Item> remainItemName() {
        return null;

    }
}
