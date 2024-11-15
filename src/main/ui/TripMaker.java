package ui;

import java.util.Scanner;
import model.Trips;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Activity;
import model.DestinationItinerary;
import model.Budget;
import model.Checklist;
import model.Item;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * The trip maker allows a user to make a console based interface 
 * by allowing the user to: Create a Trip, Add itinerary to the Trip created,
 * View all the tasks and related information for a particular destination for a specific day,
 * Make a checklist for the all items which is to be carried, View the Checklist of all the items,
 * Check if you went over budget and analyze your data of the budget and Exit the application.
 * It also allows the user to save the trip and checklist which was made, later on if saved the data
 * can be loaded and printed. It allows the user to manage the trip easily.
 */

public class TripMaker {
    private Scanner scanner;
    private Trips trip;
    private List<DestinationItinerary> destinationItinerary;
    private Checklist checklist;
    private JsonWriter jsonWriterTrip;
    private JsonReader jsonReaderTrip;
    private JsonWriter jsonWriterChecklist;
    private JsonReader jsonReaderChecklist;
    private Checklist checkistData;
    private Trips tripData;
    private JFrame window;

    // EFFECTS: constructs a TripMaker object with Scanner to take input from the
    // user, destinationItinerary list to manage the itinerary of the trip.
    // checklist to manage the checklist, Initializes JSON readers and writers for
    // trip and checklist data
    // and calls the run() method to start the application.
    public TripMaker() {
        scanner = new Scanner(System.in);
        destinationItinerary = new ArrayList<>();
        checklist = new Checklist();
        jsonWriterTrip = new JsonWriter("./data/myTrip.json");
        jsonReaderTrip = new JsonReader("./data/myTrip.json");
        jsonReaderChecklist = new JsonReader("./data/myChecklist.json");
        jsonWriterChecklist = new JsonWriter("./data/myChecklist.json");
        mainWindow();
        createPanel();
        run();
    }

    // EFFETCS: keep running the console interface until user has inputted all his
    // data and made the travel itenerary.
    /*
     * EFFECTS: Continuously displays menu options and processes user choices until
     * the user decides to exit. Calls displayOptions() to show the available
     * actions,
     * it also calls choice() to get user input, and choiceMaker(choice) to handle
     * the selected option. Ask user after each action to decide whether to
     * return to the menu or quit the travel iternerary application.
     * If the user returns to main menu, it redisplays the menu options.
     */

    public void run() {
        boolean keepRunning = true;
        displayOptions();

        while (keepRunning) {
            int choice = choice();
            choiceMaker(choice);
            System.out.println("Do you want to go to menu or quit (type true to go to menu /type false for exit)");
            keepRunning = scanner.nextBoolean();
            if (keepRunning) {
                displayOptions();
            }
        }

    }

    // EFFECTS: prints all the options available to the user to create an itenerary.

    public void displayOptions() {
        System.out.println("Please Choose any options from the provided menu");
        System.out.println("1:Create a Trip");
        System.out.println("2:Add itinerary to the Trip created");
        System.out.println("3:View all the tasks for a specific day");
        System.out.println("4:Make a checklist for the items which is to be carried");
        System.out.println("5:View the Checklist of all the items");
        System.out.println("6:Edit the checklist");
        System.out.println("7:Check if you went over budget and analyze your data");
        System.out.println("8:Save the Trip and Checklist in a file");
        System.out.println("9:Load and print Trip and Checklist data from the file");
        System.out.println("10:Exit the application");
    }
    // EFFECTS: allows the user to choose between different choices by taking
    // integer input from the user.

    public int choice() {
        System.out.println("Please enter your choice");
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    // MODIFIES: this
    // EFFECTS: inputs the user's choice and calls the corresponding method
    // based on the choice made by the user.

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
                editChecklist();
                break;

            case 7:
                overBudget();
                break;

            case 8:
                saveTrip();
                break;

            case 9:
                loadTrip();
                break;

            case 10:
                System.out.println("Exiting the Application");
                System.exit(0);

                break;
            default:
                System.out.println("Please choose some valid option available");
                break;

        }
    }

    // REQUIRES:The `trip` and checklist object must be initialized and not null.
    // EFFECTS: saves the Trip and Checklist to a json file and print a statement
    // stating the trip and the checlist
    // is saved. Gives an error if file is not found.
    public void saveTrip() {
        try {
            jsonWriterTrip.open();
            jsonWriterTrip.writeTrips(trip);
            jsonWriterTrip.close();
            System.out.println("Saved trip data to ./data/myTrip.json");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save trip data");
        }

        if (checklist == null) {
            System.out.println("Cannot save: Checklist is not initialized.");
            return;
        } else {
            try {
                jsonWriterChecklist.open();
                jsonWriterChecklist.writeChecklist(checklist);
                jsonWriterChecklist.close();
                System.out.println("Saved checklist data to ./data/myChecklist.json");
            } catch (FileNotFoundException e) {
                System.out.println("Unable to save checklist data");
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: If the trip is successfully loaded, the current trip is updated with
    // the loaded data,
    // and details (city, country, trip type, itinerary, and activities) are
    // printed.
    //
    // For checklist If the checklist is successfully loaded, the current checklist
    // will
    // be updated with the loaded data,
    // and the details of each item , the name and packing status will be printed.
    // If no checklist found , a message
    // conveying that is printed.
    //
    //
    // If no trip is found in the file, a message conveying the same is printed.
    // Or if no checlist is found in the file, a message conveying the checklist not
    // found is printed.
    // If an IOException occurs while reading the file, an appropriate printed for
    // that as well .*/
    @SuppressWarnings("methodlength")
    public void loadTrip() {
        try {
            tripData = jsonReaderTrip.readTrips();
            if (tripData != null) {
                trip = tripData;

                System.out.println("City: " + trip.getCity());
                System.out.println("Country name: " + trip.getCountry());
                System.out.println("Trip Type: " + trip.getTripType());
                if (trip.getDestinationItinerary() == null) {
                    System.out.println("No itinerary found please add the itinerary");
                } else {
                    for (DestinationItinerary itinerary : trip.getDestinationItinerary()) {
                        for (Activity activity : itinerary.getActivity()) {
                            System.out.println("The name of the activity: " + activity.getActivityName());
                            System.out.println("The location of the activity: " + activity.getLocation());
                            System.out.println("The date of the activity: " + activity.getDate());
                            System.out.println("The duration of the activity: " + activity.getDuration());
                            System.out.println("The time of the activity: " + activity.getTime());
                            System.out.println("The description of the activity: " + activity.getDescription());
                            System.out.println("The cost of the activity: " + activity.getCost());
                            System.out
                                    .println("The status (completed or not) of the activity: " + activity.getStatus());
                            System.out.println(
                                    "The budget (Limit) of the activity: " + activity.getBudget().getBudgetLimit());
                            System.out
                                    .println("The amount of money spent: "
                                            + activity.getBudget().getCurrentExpenditure());
                        }
                    }

                }

            } else {
                System.out.println("No trip found in the file.");
            }
        } catch (IOException e) {
            System.out.println("Unable to load Trip data");
        }

        try {
            checkistData = jsonReaderChecklist.readChecklist();
            if (checklist != null) {
                checklist = checkistData;

                for (Item item : checkistData.getChecklist()) {
                    System.out.println("Item name " + item.getName() + " , Item is packed? " + item.getStatus());
                }
            } else {
                System.out.println("Please create a checklist first");
            }

        } catch (IOException e) {
            System.out.println("Unable to load checklist data");
        }

    }

    // REQUIRES: String cityName ,String countryName and String tripType are all not
    // null
    // MODIFIES: The trip variable is updated with a new Trips object.
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
        System.out.println(" travel to - " + cityName + " in - " + countryName + " Trip type - " + tripType);
    }
    // REQUIRES: String date,int dayNumber and trip is not null.
    // MODIFIES: destinationItinerary
    // EFFETCS: add itinerary to the trip created, and continue running if the user
    // want to add more activites.

    public void itineraryCreation() {
        if (trip == null) {
            System.out.println("Please create a trip first before adding an itinerary");
        } else {
            System.out.println("Please enter the date ");
            String date = scanner.nextLine();
            System.out.println("Please enter the day Number (like Day 1 or Day 2 , an integer)");
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

    }
    /*
     * REQUIRES: String nameActivity,String locationActivity,String dateActivity,
     * int durationActivity is a positive integer,String timeActivity,String
     * descriptionActivity,double
     * costActivity is not negative,
     * Boolean statusActivity,double budgetLimit is not negative, double
     * currentExpenditure is not negative
     * 
     * EFFECTS: allows the user to create an activity with the inputted
     * name,location, date,duratiom,time,description,cost,status(activity completed
     * or not),
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

        System.out.println("Please enter the Duration (in minutes (integer)) of the Activity");
        int durationActivity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Please enter the Time of the Activity (like 10:00AM)");
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

    // REQUIRES: destinationItinerary is not empty.
    // EFFECTS: take day number as user input and provide the user with the activity
    // for that particular day.
    // if no activity for that day it prints no activity

    public void viewActivies() {
        System.out.println("Please enter the day number for which you want to view your activity");
        int dn = scanner.nextInt();
        for (DestinationItinerary i : trip.getDestinationItinerary()) {
            if (i.getDayNumber() == dn) {
                System.out.println("Your activities for Day Number : " + dn + " is as follows:");
                for (Activity a : i.getActivity()) {
                    System.out.println("the name of the activity was :" + a.getActivityName());
                    System.out.println("the location of the activity was : " + a.getLocation());
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
        if (trip.getDestinationItinerary().isEmpty()) {
            System.out.println("No activities found for that day");
        }
    }

    // REQUIRES: name of the item to be string and status to be Boolean.
    // MODIFIES: checklist (this)
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
            System.out.println("Do you want to add more items in the checklist (true/false)");
            moreRun = scanner.nextBoolean();
            scanner.nextLine();
        }
    }

    // EFFECTS: print all the avaiable choices that the user has in editing the
    // checklist.
    public void printCheck() {
        System.out.println("What do you want to do in the checklist");
        System.out.println("1: Get total items in the checklist");
        System.out.println("2:Get number of remaining items in the checklist (which are not packed)");
        System.out.println("3:Get the name of the remaining items which are not packed");
        System.out.println("4:Add an item to the checklist");
        System.out.println("5: Remove an item from the checklist");

    }

    // REQUIRES: checklist is not null.
    // EFFECTS: allow the user for input based on available choices calls the
    // corresponding method
    // based on the choice made by the user.

    public void takeInputCheck() {
        System.out.println("Please enter what you want to do from the options above");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                totalItem();
                break;

            case 2:
                remainItem();
                break;

            case 3:
                remainName();
                break;

            case 4:
                addItem();
                break;

            case 5:
                removeItem();
                break;

            default:
                System.out.println("Please enter a valid option");

        }
    }

    // MODIFIES: checklist(this)
    // EFFECTS: Allows the user to edit the checklist repeatedly until they decide
    // to stop.
    // Prints the current checklist and prompts the user for input each iteration.
    // Updates the checklist based on user input through the takeInputCheck()
    // method.
    public void editChecklist() {
        boolean editCheck = true;
        while (editCheck) {
            printCheck();
            takeInputCheck();

            System.out.println("Do you want to keep editing checklist (true/false)");
            editCheck = scanner.nextBoolean();

        }
    }
    // REQUIRES: checklist is not null.
    // EFFCTS: prints the name of all the items which are not packet yet.

    public void remainName() {
        if (checklist.remainItemName().isEmpty()) {
            System.out.println("the checklist has no item which is unpacked");
        } else {
            for (int i = 0; i < checklist.remainItemName().size(); i++) {
                if (checklist.remainItemName().get(i).getStatus() == false) {
                    System.out.println("Name of the item : " + checklist.remainItemName().get(i).getName());
                }
            }

        }
    }

    // REQUIRES: checklist is not null.
    // EFFECTS: prints the total number of items in the checklist.
    public void totalItem() {
        System.out.println("The total number of items in the checklist: " + checklist.totalItems());
    }

    // REQUIRES: checklist is not null.
    // EFFECTS: prints the total number of items which are not packet yet.
    public void remainItem() {
        System.out.println("number of items which are not packed: " + checklist.remainingItems());
    }

    // MODIFIES: checklist (this)
    // EFFECTS: Adds a new item with the given name and status to the checklist.
    public void addItem() {
        System.out.println("Please enter the name of the item");
        String itemName = scanner.nextLine();
        System.out.println("please enter the Status of the item");
        boolean itemStatus = scanner.nextBoolean();
        Item newItem = new Item(itemName, itemStatus);
        checklist.addItem(newItem);

    }

    // REQUIRES: The checklist should not be null.
    // MODIFIES: checklist (this)
    // EFFECTS: remove the item of the specified index from the checklist and prints
    // the name of the item which was removed
    // and if all the items are packed it displays a message that no more items can
    // be removed.
    public void removeItem() {
        if (checklist == null) {
            System.out.println("there are no item to remove in checklist , please add some items first");
        } else {
            List<Item> items = checklist.getChecklist();
            System.out.println("This is the list of all the items in your checklist");
            for (int i = 0; i < items.size(); i++) {
                System.out.println("index: " + i + " Name: " + items.get(i).getName()
                        + " is it Packed? " + items.get(i).getStatus());
            }
            System.out.println("Please enter the index of the item you want to remove");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index >= 0 && index < items.size()) {
                Item toRemove = items.get(index);
                checklist.removeItem(toRemove);
                System.out.println("The item named " + toRemove.getName() + " was removed");
            } else {
                System.out.println("No item at that index found");
            }

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
    // REQUIRES: destinationItinerary is not null.
    // EFFECTS: calculates the toal spending done on activities on that particular
    // day and give warning to user if
    // spending>limit and tell if spending<limit, if no activity prints no activity
    // found cannot make budget.

    public void overBudget() {
        System.out.println("Please enter the specific day number for which you want to review you budget");
        int day = scanner.nextInt();
        double spending = 0.0;
        double limit = 0.0;
        for (DestinationItinerary i : trip.getDestinationItinerary()) {
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

    // MODIFIES The state of the main window.
    // EFFECTS: makes the main window, with title, given size and default close
    // operation
    // and makes it visible to the user.
    public void mainWindow() {
        window = new JFrame();
        window.setTitle("Travel Itinerary Planner");
        window.setSize(800, 500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    // REQUIRES: The parent window is initialized and able to display new panels.
    // MODIFIES: The state of the panel.
    // EFFECTS: create a panel, add buttons in the panel for the specific actions
    // which needs to be performed like creating trip, add ititnerary, view
    // itinerary, save trip and load trip.
    public void createPanel() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        JLabel finalMessage = new JLabel("");
        finalMessage.setForeground(Color.WHITE);

        JButton createTripButton = createTripButton(finalMessage);
        JButton addDestinationButton = new JButton("Add Destination to the Trip");
        JButton viewTasksButton = new JButton("View Tasks");
        JButton saveButton = new JButton("Save the Trip");
        JButton loadButton = new JButton("Load Trip");

        Dimension buttonSize = new Dimension(200, 40);
        createTripButton.setMaximumSize(buttonSize);
        addDestinationButton.setMaximumSize(buttonSize);
        viewTasksButton.setMaximumSize(buttonSize);
        saveButton.setMaximumSize(buttonSize);
        loadButton.setMaximumSize(buttonSize);

        panel.add(Box.createVerticalStrut(30));
        panel.add(createTripButton);
        panel.add(Box.createVerticalStrut(30));
        panel.add(addDestinationButton);
        panel.add(Box.createVerticalStrut(30));
        panel.add(viewTasksButton);
        panel.add(Box.createVerticalStrut(30));
        panel.add(saveButton);
        panel.add(Box.createVerticalStrut(30));
        panel.add(loadButton);
        panel.add(Box.createVerticalStrut(40));
        panel.add(finalMessage);

        window.add(panel, BorderLayout.CENTER);
    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage
    // EFFECTS: Creates the "Create a new Trip" button and add fucntionality to it
    // using actionlistener and perform the action of making a new trip by calling a
    // new function that will input the data for the GUI.
    public JButton createTripButton(JLabel finalMessage) {
        return null;
    }

}
