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
//EFFECTS: prints all the options available to the user.
    public void  displayOptions(){
        System.out.println("Please Choose any options from the provided menu");
        System.out.println("1:Create a Trip");
        System.out.println("2:Add itinerary to the Trip created");
        System.out.println("3:View all the tasks and related information for a particular destination for a specific day");
        System.out.println("4:Make a checklist for the items which is to be carried");
        System.out.println("5:View the Checklist of all the items");
        System.out.println("6:Check if you went over budget and analyze your data");
        System.out.println("7:Exit the application");
    }
//EFFECTS: allows the user to choose between different choices by taking integer input from the user.
    public int choice(){
        System.out.println("Please enter your choice");
        int choice=scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    public void choiceMaker(int choice){
        switch(choice){
            case 1:
            System.out.println("Exiting the Application");
            break;

            default:
            System.out.println("Please choose some valid option available");
            break;

        }
    }



}  