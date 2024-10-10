package model;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item i1;
    
    @BeforeEach
    public void runBefore(){
        i1= new Item("Passport", false);
    }

    @Test
    public void testItem(){
        assertEquals("Passport",i1.getName());
        assertFalse(i1.getStatus());
    }

    @Test
    public void testSetName(){
        i1.setName("Sunglasses");
        assertEquals("Sunglasses",i1.getName());
    }

    @Test
    public void testSetStatus(){
        i1.setStatus(true);
        assertTrue(i1.getStatus());
    }

}
