package model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class DestinationItineraryTest {
    private DestinationItinerary i1;
    private List<Activity> activity;
    private Activity a1;
    private Activity a2;

    @BeforeEach
    void runBefore(){
        a1= new Activity("Surfing", "Vancouver", "2024-09-10",
        60,"10:00 AM", "Did surfing with family", 100.0, true);

        a2= new Activity("Eating", "Vancouver", "2024-09-10",
        20,"11:00 AM", "Ate Noodles with family", 30.0, true);

        activity=new ArrayList<Activity>();

        activity.add(a1);

        i1=new DestinationItinerary("2024-09-10",1,activity);
    }

    @Test
    public void testDestinationItinerary(){
        assertEquals("2024-09-10",i1.getDate());
        assertEquals(1,i1.getDayNumber());
        assertEquals(activity, i1.getActivity());
    }

    @Test
    public void testSetDate(){
        i1.setDate("2024-09-11");
        assertEquals("2024-09-11", i1.getDate());   
    }

    @Test
    public void testSetDayNumber(){
        i1.setDayNumber(1);
        assertEquals(1, i1.getDayNumber());   
    }
    @Test
    public void testAddActivity(){
        i1.addActivity(a2);
        List<Activity> test= new ArrayList<Activity>();
        test.add(a1);
        test.add(a2);
        assertEquals(test,i1.getActivity());
        assertEquals(2,i1.getActivity().size());
    }

    @Test
    public void testGetTotalCost(){
       assertEquals(100,i1.getTotalCost());
       i1.addActivity(a2);
       assertEquals(130,i1.getTotalCost());

    }
}
