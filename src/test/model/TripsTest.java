package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TripsTest {
    private Trips trip;
    private List<DestinationItinerary> itinerary;
    private List<Activity> activity;
    private Activity a1;

    @BeforeEach
    void runBefore() {
        itinerary = new ArrayList<DestinationItinerary>();

        trip = new Trips("Vancouver", "Canada", "Solo");

        a1 = new Activity("Surfing", "Vancouver", "2024-09-10",
                60, "10:00 AM", "Did surfing with family", 100.0, true, new Budget(1000, 0));

        activity = new ArrayList<Activity>();

        activity.add(a1);
    }

    @Test
    void testTrips() {
        assertEquals("Vancouver", trip.getCity());
        assertEquals("Canada", trip.getCountry());
        assertEquals("Solo", trip.getTripType());
        assertEquals(0, itinerary.size());
    }

    @Test
    void testsetCity() {
        assertEquals("Vancouver", trip.getCity()); // checking if the city is already at the base value.
        trip.setCity("Delhi"); // Setting the city to a new city
        assertEquals("Delhi", trip.getCity());
    }

    @Test
    void testsetCountry() {
        assertEquals("Canada", trip.getCountry());// checking if the country is already at the base value.
        trip.setCountry("India");// Setting the country to a new country
        assertEquals("India", trip.getCountry());
    }

    @Test
    void testsetTripType() {
        assertEquals("Solo", trip.getTripType());// checking if the trip type is already at the base value.
        trip.setTripType("Business");
        assertEquals("Business", trip.getTripType());
    }

    @Test
    void testaddDestinationItineraries() {
        DestinationItinerary entry = new DestinationItinerary("2024-09-10", 1, activity);
        trip.addDestinationItineraries(entry); // added our entry to the trip

        itinerary = trip.getDestinationItinerary();
        assertEquals(entry, itinerary.get(0));
        // Checking if our added entry is equal to the first entry (both should be same)
        assertEquals(1, itinerary.size());
        // checking the size of list(should be 1 only one element added)
    }

    @Test
    void testToJson(){
        DestinationItinerary entry = new DestinationItinerary("2024-09-10", 1, activity);
        trip.addDestinationItineraries(entry); 

        JSONObject tr = trip.toJson();
        JSONArray trItinerary = tr.getJSONArray("itinerary");

        assertEquals("Vancouver", tr.getString("City"));
        assertEquals("Canada", tr.getString("Country"));
        assertEquals("Solo",tr.getString("Trip Type"));
        assertTrue(tr.has("itinerary"));
        assertEquals(1, trItinerary.length()); 

    }
}
