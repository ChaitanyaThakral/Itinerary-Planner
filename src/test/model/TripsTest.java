package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TripsTest {
    private Trips trip;
    private List<DestinationItinerary> itinerary; 
    private Activity a1;
    private Activity a2;
    
    @BeforeEach
    void runBefore() {
        trip=new Trips("Vancouver","Canada","Solo");
        a1= new Activity("Surfing", "Vancouver", "2024-09-10",
         60,"10:00 AM", "Did surfing with family", 100.0, true);
        a2= new Activity("Eating", "Vancouver", "2024-09-10",
        20,"11:00 AM", "Ate Noodles with family", 30.0, true);
    }

    @Test
    void testTrips(){
        assertEquals("Vancouver",trip.getCity());
        assertEquals("Canada",trip.getCountry());
        assertEquals("Solo",trip.getTripType());
    }

    @Test
    void testsetCity(){
        assertEquals("Vancouver",trip.getCity()); //checking if the city is already at the base value.
        trip.setCity("Delhi"); // Setting the city to a new city
        assertEquals("Delhi",trip.getCity());
    }

    @Test
    void testsetCountry(){
        assertEquals("Canada",trip.getCountry());//checking if the country is already at the base value.
        trip.setCountry("India");// Setting the country to a new country
        assertEquals("India",trip.getCountry());
    }
    @Test
    void testsetTripType(){
        assertEquals("Solo",trip.getTripType());//checking if the trip type is already at the base value.
        trip.setTripType("Business");
        assertEquals("Business",trip.getTripType());
    }

    @Test
    void testaddDestinationItineraries(){
        DestinationItinerary Entry = new DestinationItinerary("2024-09-10", 1);
        trip.addDestinationItineraries(Entry); //added our entry to the trip

        itinerary=trip.getDestinationItinerary();
        assertEquals(Entry,itinerary.get(0));
        // Checking if our added entry is equal to the first entry (both should be same)
        assertEquals(1, itinerary.size());
       // checking the size of list(should be 1 only one element added)
    }
 
}
