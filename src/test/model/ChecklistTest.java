package model;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;


public class ChecklistTest {
    private Checklist checklist;
    private Item i1;
    private Item i2;
    private Item i3;
    
    @BeforeEach
    void runBefore(){
        checklist=new Checklist();
        i1= new Item("Passport", false);
        i2= new Item("Sunglasses", false);
        i3= new Item("Photo",false);
        checklist.addItem(i1);
        checklist.addItem(i2);
    }

    @Test
    void testChecklist(){
        assertEquals(2,checklist.totalItems());
        assertEquals(i1,checklist.getChecklist().get(0));
        assertEquals(i2,checklist.getChecklist().get(1));
    }

    @Test 
    void testAddItem(){
        assertEquals(2,checklist.totalItems());
        checklist.addItem(i3);
        assertEquals(3,checklist.totalItems());
    }

    @Test
    void testRemoveItem(){
        assertEquals(2,checklist.totalItems());
        checklist.removeItem(i2);
        assertEquals(1,checklist.totalItems());
    }

    @Test
    void testRmainingItems(){
        i1.setStatus(true);
        checklist.addItem(i3);
        assertEquals(2,checklist.remainingItems());
    }

}
