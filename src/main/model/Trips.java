package model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writeable;

/*Represents a trip with city, country, type of the trip and list of itinerary associated with that trip 
 * Trip specifies a city where the user is going to , country where the user is going to, type of the trip 
 * solo, buisness or family trip, itinerary of the trip of what all the activities user may have for his trip
*/
public class Trips implements Writeable{
    private String city;
    private String country;
    private String tripType;
    private List<DestinationItinerary> itinerary; 

// EFFECTS: contructs a trip with city, country, type of the trip and list of itinerary
//.On creation the itinerary list is empty.
    public Trips(String city, String country, String tripType) {
        this.city = city;
        this.country = country;
        this.tripType = tripType;
        itinerary = new ArrayList<DestinationItinerary>();
    }

    //MODIFIES: this
    //EFFECTS:sets the City to the new provided city.
    public void setCity(String city) {
        this.city = city;
    }
    //MODIFIES: this
    //EFFECTS:sets the country to the new provided country.

    public void setCountry(String country) {
        this.country = country;
    }

    //MODIFIES: this
    //EFFECTS: sets the trip type to the new provided trip type.
    public void setTripType(String tripType) {
        this.tripType = tripType;
    }

    //MODIFIES: this
    //EFFECTS: add the given activity to our list of destination itinerary.
    public void addDestinationItineraries(DestinationItinerary activity) {
        itinerary.add(activity);
    }
    
    public String getCity() {
        return this.city;

    }

    public String getCountry() {
        return this.country;

    }

    public List<DestinationItinerary> getDestinationItinerary() {
        return this.itinerary;
    }

    public String getTripType() {
        return this.tripType;
    }

    //REQUIRES: the trip instance should not be null.
    //EFFECTS: returns a JSONObject representation of this Trips instance, including all the details and fields like 
    //city, country, type of the trip and list of itinerary.
     @Override
    public JSONObject toJson() {
        JSONObject tr = new JSONObject();
        tr.put("City", this.city);
        tr.put("Country", this.country);
        tr.put("Trip Type", this.tripType);

        JSONArray trItinerary = new JSONArray();
        for (DestinationItinerary object : this.itinerary) {
            trItinerary.put(object.toJson());
        }

        tr.put("Itinerary",trItinerary);

        return tr;

    }


}


