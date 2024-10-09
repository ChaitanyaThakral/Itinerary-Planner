package model;

import java.util.ArrayList;
import java.util.List;

public class Trips {
    private String city;
    private String country;
    private String tripType;
    private List<DestinationItinerary> itinerary; 

// EFFECTS: contructs a trip with city, country, type of the trip and list of itinerary.
    public Trips(String city, String country, String tripType){
        
    }
    //MODIFIES: this
    //EFFECTS:sets the City to the new provided city.
    public void setCity(String city) {
    
    }
    //MODIFIES: this
    //EFFECTS:sets the country to the new provided country.
    public void setCountry(String country) {
        
    }

    //MODIFIES: this
    //EFFECTS: sets the trip type to the new provided trip type.
    public void setTripType(String tripType){
    
    }

    //MODIFIES: this
    //EFFECTS: add the given activity to our list of destination itinerary.
    public List<DestinationItinerary> addDestinationItineraries(DestinationItinerary activity){
    return null;
    }
    
    public String getCity() {
    return null;

    }

    public String getCountry() {
    return null;

    }

    public List<DestinationItinerary> getDestinationItinerary() {
    return null;
    }

    public String getTripType() {
    return null;
    }

}
