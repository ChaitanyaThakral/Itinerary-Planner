package model;

import org.json.JSONObject;

import persistence.Writeable;

/* Represents an item with its name and packing status i.e. whether the item is packed or not.
 * Item has Name of item (has to be String), 
 * status: has to be boolean (representing whether the item is packed or not)*/
public class Item implements Writeable {
    private String name;
    private Boolean status;

    // EFFECTS: Constructs an item with its name and packing status (upon
    // construction status set to false).
    public Item(String name, Boolean status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getStatus() {
        return this.status;
    }

    // MODIFIES:this
    // EFFECTS: sets the name to the new provided name.
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES:this
    // EFFECTS: sets the status to the new provided status.
    public void setStatus(Boolean status) {
        this.status = status;
    }

    // REQUIRES: the Item instance should not be null.
    // EFFECTS: returns a JSONObject representation of this Checklist instance,
    // with its name and packing status.
    @Override
    public JSONObject toJson() {
        return null;
    }
}
