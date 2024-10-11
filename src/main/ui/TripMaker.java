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

    // EFFECTS: constructs a TripMaker object with Scanner to take input from the
    // user,
    // destinationItinerary list to manage the itinerary of the trip.
    public TripMaker() {
        scanner = new Scanner(System.in);
        destinationItinerary = new ArrayList<>();
        run();
    }
    // EFFETCS: keep running the console interface until user has inputted all his
    // data.

    public void run() {
        boolean keepRunning = true;
        displayOptions();

        while (keepRunning) {
            int choice = choice();
            choiceMaker(choice);
            System.out.println("Do you want to go to menu or quit (press true to go to menu /press false for exit)");
            keepRunning = scanner.nextBoolean();
            if (keepRunning) {
                displayOptions();
            }
        }

    }

    // EFFECTS: prints all the options available to the user.

    public void displayOptions() {
        System.out.println("Please Choose any options from the provided menu");
        System.out.println("1:Create a Trip");
        System.out.println("2:Add itinerary to the Trip created");
        System.out.println("3:View all the tasks for a specific day");
        System.out.println("4:Make a checklist for the items which is to be carried");
        System.out.println("5:View the Checklist of all the items");
        System.out.println("6:Check if you went over budget and analyze your data");
        System.out.println("7:Exit the application");
    }
    // EFFECTS: allows the user to choose between different choices by taking
    // integer input from the user.

    public int choice() {
        System.out.println("Please enter your choice");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    @SuppressWarnings("methodlength")
    public void choiceMaker(int choice) {

        switch (choice) {
            case 1:
                tripCreation();
                break;

            case 2:
                itineraryCreation();
                break;

            case 3:
                viewActivies();
                break;

            case 4:
                makeChecklist();
                break;

            case 5:
                viewChecklist();
                break;

            case 6:
                overBudget();
                break;

            case 7:
                System.out.println("Exiting the Application");
                System.exit(0);

                break;
            default:
                System.out.println("Please choose some valid option available");
                break;

        }
    }
    // REQUIRES: String cityName ,String countryName and String tripType.
    // EFFECTS: allows the user to create a trip by inputting city name, country
    // name, and trip type.

    public void tripCreation() {
        System.out.println("Please enter you Destination (city) Name:");
        String cityName = scanner.nextLine();

        System.out.println("Please enter you Destination (country) Name:");
        String countryName = scanner.nextLine();

        System.out.println("Please enter your Trip type (solo,family,buisness)");
        String tripType = scanner.nextLine();

        trip = new Trips(cityName, countryName, tripType);
        System.out.println(" travel to " + cityName + " in " + countryName + "  Trip type " + tripType);
    }
    // REQUIRES: String date,int dayNumber.
    // EFFETCS: add itinerary to the trip created, and run if the user want to add
    // more activites.

    public void itineraryCreation() {
        System.out.println("Please enter the date ");
        String date = scanner.nextLine();

        System.out.println("Please enter the day Number (like Day 1 or Day2 , just a number)");
        int dayNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Now lets add your activites for the day " + dayNumber);

        Boolean wantMoreActivity = true;
        List<Activity> activityList = new ArrayList<>();

        while (wantMoreActivity) {
            Activity activity = activityCreation();
            activityList.add(activity);
            System.out.println("Do you want to add more activities for the same day? (true/false)");
            wantMoreActivity = scanner.nextBoolean();
            scanner.nextLine();
        }
        DestinationItinerary listOfitinerary = new DestinationItinerary(date, dayNumber, activityList);
        this.destinationItinerary.add(listOfitinerary);
        trip.addDestinationItineraries(listOfitinerary);
    }
    /*
     * REQUIRES: String nameActivity,String locationActivity,String dateActivity,
     * int durationActivity,String timeActivity,String descriptionActivity,double
     * costActivity,
     * Boolean statusActivity,double budgetLimit,double currentExpenditure
     * 
     * EFFECTS: allows the user to create an activity with the inputted
     * name,location,
     * date,duratiom,time,description,cost,status(activity completed or not),
     * and budget
     */
    
    @SuppressWarnings("methodlength")
    public Activity activityCreation() {

        System.out.println("Please enter the name of the Activity");
        String nameActivity = scanner.nextLine();

        System.out.println("Please enter the Location of the Activity");
        String locationActivity = scanner.nextLine();

        System.out.println("Please enter the Date of the Activity");
        String dateActivity = scanner.nextLine();

        System.out.println("Please enter the Duration of the Activity");
        int durationActivity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please enter the Time of the Activity");
        String timeActivity = scanner.nextLine();

        System.out.println("Please enter the Descrition of the Activity");
        String descriptionActivity = scanner.nextLine();

        System.out.println("Please enter the Cost of the Activity");
        double costActivity = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Please enter the Status  (if completed type true/ if incompleted type false)");
        Boolean statusActivity = scanner.nextBoolean();
        scanner.nextLine();

        System.out.println("We will now set Budget of the Activity");
        System.out.println("Please enter the budget limit for this activity");
        double bl = scanner.nextDouble();
        System.out.println("Please enter the Current spending you have done for this activity");
        double ce = scanner.nextDouble();

        Budget b = new Budget(bl, ce);

        return new Activity(nameActivity, locationActivity, dateActivity, durationActivity,
                timeActivity, descriptionActivity, costActivity, statusActivity, b);

    }
    // EFFECTS: take day number as user input and provide the user with the activity
    // for that particular day.
    // if no activity for that day it prints no activity

    public void viewActivies() {
        System.out.println("Please enter the day number for which you want to view your activity");
        int dn = scanner.nextInt();
        for (DestinationItinerary i : destinationItinerary) {
            if (i.getDayNumber() == dn) {
                System.out.println("Your activities for Day Number : " + dn + " is as follows:");
                for (Activity a : i.getActivity()) {
                    System.out.println("the name of the activity was :" + a.getActivityName());
                    System.out.println("the lcoation of the activity was : " + a.getLocation());
                    System.out.println("the date of the activity was : " + a.getDate());
                    System.out.println("the duration of the activity was : " + a.getDuration());
                    System.out.println("the time of the activity was : " + a.getTime());
                    System.out.println("the description of the activity was : " + a.getDescription());
                    System.out.println("the cost of the activity was : " + a.getCost());
                    System.out.println("the status(completed or not) of the activity was : " + a.getStatus());
                    System.out.println("the budget(Limit) of the activity was : " + a.getBudget().getBudgetLimit());
                    System.out.println("the amount of money spent was : " + a.getBudget().getCurrentExpenditure());
                }
            }
        }
        if (destinationItinerary.isEmpty()) {
            System.out.println("No activities found for that day");
        }
    }
    // REQUIRES: name of the item to be string and stutus to be Boolean.
    // EFFETCS: Creates a checklist containing the items which has to be packed.
    // item has name and pack status.

    public void makeChecklist() {
        System.out.println("Lets create a checklist");
        boolean moreRun = true;
        checklist = new Checklist();
        while (moreRun) {
            System.out.println("Please enter the name of the item");
            String nameItem = scanner.nextLine();
            System.out.println("Please enter the status of the item (if Packed type -true/ not packed type -false)");
            boolean statusItem = scanner.nextBoolean();
            scanner.nextLine();
            checklist.addItem(new Item(nameItem, statusItem));
            System.out.println("Do you want to add more items (true/false)");
            moreRun = scanner.nextBoolean();
            scanner.nextLine();
        }
    }
    // EFFETCS: print the checklist and if no checklist present presents prints no
    // checklist print

    public void viewChecklist() {
        System.out.println("Here is your checklist");
        if (checklist == null) {
            System.out.println("checklist not found, please be sure to make the checklist first");
        } else {
            for (Item item : checklist.getChecklist()) {
                System.out.println("Item name " + item.getName() + " , Item is packed? " + item.getStatus());
            }
        }
    }
    // EFFECTS: calculates the toal spending done on activities on that particular
    // day and give warning to user if
    // spending>limit and tell if spending<limit, if no activity prints no activity
    // found cannot make budget.

    public void overBudget() {
        System.out.println("Please enter the specific day number for which you want to review you budget");
        int day = scanner.nextInt();
        double spending = 0.0;
        double limit = 0.0;
        for (DestinationItinerary i : destinationItinerary) {
            if (i.getDayNumber() == day) {

                for (Activity activity : i.getActivity()) {
                    spending = spending + activity.getBudget().getCurrentExpenditure();
                    limit = limit + activity.getBudget().getBudgetLimit();

                }
                break;
            }
        }

        System.out.println("total amount spent : " + spending + " total limit : " + limit);

        if (destinationItinerary.isEmpty()) {
            System.out.println("No activities found for that day cannot make budget analysis");
        }

        if (spending > limit) {
            System.out.println("you went over the buget limit, need to reduce the spending");
        }
        if (spending < limit) {
            System.out.println("you were not over buget limit");
        }
    }

}
