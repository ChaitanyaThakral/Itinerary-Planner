package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import model.Activity;
import model.Budget;
import model.Checklist;
import model.DestinationItinerary;
import model.Item;
import model.Trips;

public class JsonWriterTest {

    private JsonWriter writer;
    private String destination;
    private Trips trip;
    private JsonReader reader;
    private Activity a1;
    private Budget b1;
    private DestinationItinerary i1;
    private List<Activity> activity;
    private Checklist checklist;
    private Item item1;
    private Item i2;
    private Item i4;
    private JsonWriter writerChecklist;
    private JsonReader readerChecklist;
    private Checklist emptyChecklist;

    @BeforeEach
    void runBefore() {
        writer = new JsonWriter("./data/myFile.json");
        trip = new Trips("Vancouver", "Canada", "Solo");
        reader = new JsonReader("./data/myFile.json");
        b1 = new Budget(1000, 0);
        a1 = new Activity("Surfing", "Vancouver", "2024-09-10",
                60, "10:00 AM", "Did surfing with family", 100.0, true, b1);
        activity = new ArrayList<Activity>();
        activity.add(a1);
        i1 = new DestinationItinerary("2024-09-10", 1, activity);
        trip.addDestinationItineraries(i1);
        checklist = new Checklist();
        emptyChecklist = new Checklist();
        item1 = new Item("Passport", false);
        i2 = new Item("Sunglasses", false);
        i4 = new Item("Book", true);
        checklist.addItem(item1);
        checklist.addItem(i2);
        checklist.addItem(i4);
        writerChecklist=new JsonWriter("./data/myChecklist.json");
        readerChecklist=new JsonReader("./data/myChecklist.json");
    }

    @Test
    void testInvalidFile() {
        try {
            JsonWriter writer1 = new JsonWriter("my\\0illegalFile.json");
            writer1.open();
            fail("IOException is expected here");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testEmptyTrip() {
        try {
            writer.open();
            writer.writeTrips(trip);
            writer.close();

            Trips tripRead = reader.readTrips();
            assertEquals("Canada", tripRead.getCountry());
            assertEquals("Vancouver", tripRead.getCity());
            assertEquals("Solo", tripRead.getTripType());
            assertEquals(0, tripRead.getDestinationItinerary().size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void testTripWithItinerary() {
        try {
            writer.open();
            writer.writeTrips(trip);
            writer.close();

            Trips tripRead = reader.readTrips();
            assertEquals("Canada", tripRead.getCountry());
            assertEquals("Vancouver", tripRead.getCity());
            assertEquals("Solo", tripRead.getTripType());
            assertEquals(1, tripRead.getDestinationItinerary().size());

            DestinationItinerary itineraryRead = tripRead.getDestinationItinerary().get(0);
            assertEquals("2024-09-10", itineraryRead.getDate());
            assertEquals(1, itineraryRead.getActivity().size());

            Activity act = itineraryRead.getActivity().get(0);
            assertEquals("Surfing", act.getActivityName());
            assertEquals("Vancouver", act.getLocation());
            assertEquals("2024-09-10", act.getDate());
            assertEquals(60, act.getDuration());
            assertEquals("10:00 AM", act.getTime());
            assertEquals("Did surfing with family", act.getDescription());
            assertEquals(100.0, act.getCost());
            assertTrue(act.getStatus());

            assertEquals(1000, act.getBudget().getBudgetLimit());
            assertEquals(0, act.getBudget().getCurrentExpenditure());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

    }

    @Test
    void testEmptyChecklist(){
        try {
            writerChecklist.open();
            writerChecklist.writeChecklist(emptyChecklist);

            writerChecklist.close();

            Checklist checklistRead = readerChecklist.readChecklist();
            assertEquals(0, checklistRead.getChecklist().size());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

    }

    @Test
    void testChecklist() {
        try {
            writerChecklist.open();
            writerChecklist.writeChecklist(checklist);
            writerChecklist.close();

            Checklist checklistRead = readerChecklist.readChecklist();
            
            
            assertEquals(3, checklistRead.getChecklist().size());

            
            Item readItem1 = checklistRead.getChecklist().get(0);
            assertEquals("Passport", readItem1.getName());
            assertFalse(readItem1.getStatus());

            Item readItem2 = checklistRead.getChecklist().get(1);
            assertEquals("Sunglasses", readItem2.getName());
            assertFalse(readItem2.getStatus());

            Item readItem3 = checklistRead.getChecklist().get(2);
            assertEquals("Book", readItem3.getName());
            assertFalse(readItem3.getStatus());

        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

}
