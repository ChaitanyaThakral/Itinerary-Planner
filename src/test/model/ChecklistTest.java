package model;

import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class ChecklistTest {
    private Checklist checklist;
    private Item i1;
    private Item i2;
    private Item i3;
    private Item i4;

    @BeforeEach
    void runBefore() {
        checklist = new Checklist();
        i1 = new Item("Passport", false);
        i2 = new Item("Sunglasses", false);
        i3 = new Item("Photo", false);
        i4 = new Item("Book", true);
        checklist.addItem(i1);
        checklist.addItem(i2);
        checklist.addItem(i4);
    }

    @Test
    void testChecklist() {
        assertEquals(3, checklist.totalItems());
        assertEquals(i1, checklist.getChecklist().get(0));
        assertEquals(i2, checklist.getChecklist().get(1));
    }

    @Test
    void testAddItem() {
        assertEquals(3, checklist.totalItems());
        checklist.addItem(i3);
        assertEquals(4, checklist.totalItems());
    }

    @Test
    void testRemoveItem() {
        assertEquals(3, checklist.totalItems());
        checklist.removeItem(i2);
        assertEquals(2, checklist.totalItems());
    }

    @Test
    void testRmainingItems() {
        i1.setStatus(true);
        checklist.addItem(i3);
        assertEquals(2, checklist.remainingItems());
    }

    @Test
    public void testRemainItemName() {
        List<Item> item = new ArrayList<>();
        item.add(i1);
        item.add(i2);
        assertEquals(item, checklist.remainItemName());

    }

    @Test
    public void testToJason() {
        JSONObject checklistJsonObject = checklist.toJson();

        JSONArray checkArray = checklistJsonObject.getJSONArray("checklist");
        assertTrue(checklistJsonObject.has("checklist"));
        assertEquals(3, checkArray.length());

    }

}
