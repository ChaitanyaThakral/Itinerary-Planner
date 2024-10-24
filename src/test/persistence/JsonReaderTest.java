package persistence;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;



import model.Activity;
import model.Checklist;
import model.DestinationItinerary;
import model.Item;
import model.Trips;

public class JsonReaderTest {

    @Test
    public void testInvalidFile() {
        JsonReader reader = new JsonReader("my\\0illegalFile.json");
        try {
            Trips trip = reader.readTrips();
            fail("IOException expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    public void testEmptyTrip() {
        JsonReader reader = new JsonReader("./data/myTripEmpty.json");
        try {
            Trips trip = reader.readTrips();
            assertTrue(trip != null);
            assertEquals(0, trip.getDestinationItinerary().size());

        } catch (IOException e) {
            fail("Unable to read file");
        }
    }

    @Test
    public void testReadTrip() {
        JsonReader reader = new JsonReader("./data/myTrip.json");
        try {
            Trips trip = reader.readTrips();
            assertTrue(trip != null);
            assertEquals(1, trip.getDestinationItinerary().size());

            DestinationItinerary itinerary = trip.getDestinationItinerary().get(0);
            assertEquals("2024-09-10", itinerary.getDate());
            assertEquals(1, itinerary.getDayNumber());

            Activity act = itinerary.getActivity().get(0);
            assertEquals("Surfing", act.getActivityName());
            assertEquals("Vancouver", act.getLocation());
            assertEquals("2024-09-10", act.getDate());
            assertEquals(60, act.getDuration());
            assertEquals("10:00 AM", act.getTime());
            assertEquals("Did surfing with family", act.getDescription());
            assertEquals(100.0, act.getCost());
            assertTrue(act.getStatus());

        } catch (IOException e) {
            fail("Unable to read file");
        }
    }

    @Test
    public void testReadEmptyChecklist() {
        JsonReader reader = new JsonReader("./data/myChecklistEmpty.json");
        try {
            Checklist checklist = reader.readChecklist();
            assertTrue(checklist != null);
            assertEquals(0, checklist.getChecklist().size());
        } catch (IOException e) {
            fail("Unable to read file");
        }
    }

    @Test
    public void testChecklist() {
        JsonReader reader = new JsonReader("./data/myChecklist.json");
        try {
            Checklist checklist = reader.readChecklist();
            assertTrue(checklist != null);
            assertEquals(3, checklist.getChecklist().size());

            Item readItem1 = checklist.getChecklist().get(0);
            assertEquals("Passport", readItem1.getName());
            assertFalse(readItem1.getStatus());

            Item readItem2 = checklist.getChecklist().get(1);
            assertEquals("Sunglasses", readItem2.getName());
            assertFalse(readItem2.getStatus());

            Item readItem3 = checklist.getChecklist().get(2);
            assertEquals("Book", readItem3.getName());
            assertTrue(readItem3.getStatus());

        } catch (IOException e) {
            fail("Unable to read file");
        }
    }

}
