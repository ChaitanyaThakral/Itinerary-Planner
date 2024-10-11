package ui;
import java.util.Scanner;
import model.Trips;
import model.Activity;
import model.DestinationItinerary;
import model.Budget;
import model.Checklist;
import model.Item;
import java.util.ArrayList;
import java.util.List;
/*
 * The trip maker allows a user to make a console based interface 
 * by allowing the user to: Create a Trip, Add itinerary to the Trip created,
 * View all the tasks and related information for a particular destination for a specific day,
 * Make a checklist for the all items which is to be carried, View the Checklist of all the items,
 * Check if you went over budget and analyze your data of the budget and Exit the application.
 * It allows the user to manage the trip easily.
 */
public class TripMaker {
    private Scanner scanner;
    private Trips trip;
    private List<DestinationItinerary> destinationItinerary;
    private Checklist checklist;

//EFFECTS: constructs a TripMaker object with Scanner to take input from the user, 
//destinationItinerary list to manage the itinerary of the trip.
    public TripMaker(){
    scanner= new Scanner(System.in);
    destinationItinerary = new ArrayList<>();
    }
}  