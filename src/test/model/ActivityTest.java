package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ActivityTest {
    private Activity a1;

    @BeforeEach
    void runBefore(){
        a1= new Activity("Surfing", "Vancouver", "2024-09-10",
        60,"10:00 AM", "Did surfing with family", 100.0, true);
    }

    @Test
    public void testSetActivityName(){
       a1.setActivityName("Swimming");
       assertEquals("Swimming",a1.getActivityName());
       a1.setActivityName("Boating");
       assertEquals("Boating",a1.getActivityName());
    }

    @Test
    public void testSetLocation(){
        a1.setLocation("Delhi");
        assertEquals("Delhi",a1.getLocation());
    }

    @Test
    public void setDate(){
       a1.setDate("2024-09-11");
       assertEquals("2024-09-11", a1.getDate());
    }

    @Test
    public void setDuration(){
        a1.setDuration(100);
        assertEquals(100,a1.getDuration());
    }
    
    @Test
    public void setTime(){
        a1.setTime("11:00 AM");
        assertEquals("11:00 AM",a1.getTime());
    }

    @Test
    public void setDescription(){
        a1.setDescription("Went to Surf");
        assertEquals("Went to Surf",a1.getDescription());
    }
    
    @Test
    public void setCost(){
        a1.setCost(90.00);
        assertEquals(90.00,a1.getCost());
    }

    @Test
    public void setStatus(){
        a1.setStatus(false);
        assertFalse(a1.getStatus());

        a1.setStatus(true);
        assertTrue(a1.getStatus());
    }

    
}
