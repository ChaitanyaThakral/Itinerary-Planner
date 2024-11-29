package ui;

import model.Trips;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Activity;
import model.DestinationItinerary;
import model.EventLog;
import model.Budget;
import model.Event;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * The GUI class Creates a GUI interface to create trip, add itinerary to the trip, 
 * display the activties, remove itinerary,Budget analyis , loading and saving the state of the trip. 
 * Once the user exits the application it displays the log events onto the console.
 * It allows the user to manage the trip easily.
 */

public class GUI {

    private Trips trip;
    private JFrame window;

    // EFFECTS: constructs a TripMaker object with Scanner to take input from the
    // user, destinationItinerary list to manage the itinerary of the trip.
    // checklist to manage the checklist, Initializes JSON readers and writers for
    // trip and checklist data
    // and calls the run() method to start the application.
    // calls the mainWindow() method to create the main Window for the GUI, and
    // CreatePanel to
    // create all the required background image and the buttons for the GUI.
    public GUI() {
        mainWindow();
        createPanel();
    }

    // MODIFIES main window.
    // EFFECTS: makes the main window, with title, given size and default close
    // operation
    // and makes it visible to the user.
    public void mainWindow() {
        window = new JFrame();
        window.setTitle("Travel Itinerary Planner");
        window.setSize(800, 500);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                logDisplay();
            }
        });

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    // REQUIRES: EventLog is properly initialized
    // EFFECTS: Displays all events in the EventLog to the console
    // and then terminates the program.

    public void logDisplay() {
        System.out.println("Event Log:");
        for (Event event : EventLog.getInstance()) {

            System.out.println(event.getDate() + ": " + event.getDescription());
        }
        System.exit(0);
    }

    @SuppressWarnings("methodlength")
    // REQUIRES: The parent window is initialized and able to display all the new
    // panels.

    // MODIFIES: The state of the panel and components such as buttons and labels.

    // EFFECTS: create a panel , set up a background panel with a background
    // image,which fit the window size of (800x500).
    // add buttons in the panel for the specific actions
    // which needs to be performed like creating trip, add ititnerary, view
    // itinerary, removing an itinerary, getting a budget analysis, save trip and
    // load trip.
    // it Sets positions and sizes for each button. Adds the buttons and labels to
    // the panel.
    // Displays the final message at the bottom of the window for convenience of the
    // user.
    public void createPanel() {
        JLayeredPane backgroundPanel = new JLayeredPane();
        backgroundPanel.setPreferredSize(new Dimension(800, 500));

        ImageIcon bg = new ImageIcon("data/Background Image.jpeg");
        JLabel background = new JLabel(new ImageIcon(bg.getImage()
                .getScaledInstance(800, 500, Image.SCALE_SMOOTH)));
        background.setBounds(0, 0, 800, 500);
        backgroundPanel.add(background, Integer.valueOf(0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(0, 0, 800, 500);

        JLabel finalMessage = new JLabel("");
        finalMessage.setBounds(20, 400, 760, 40);
        finalMessage.setForeground(Color.BLACK);
        finalMessage.setFont(new Font("Arial", Font.BOLD, 20));

        JButton createTripButton = createTripButton(finalMessage);
        JButton addDestinationButton = createAddDestinationItineraryButton(finalMessage);
        JButton viewTasksButton = createViewTasksButton(finalMessage);
        JButton removeActivity = createRemoveActivity(finalMessage);
        JButton budgetAnalysisButton = createBudgetAnalysisButton(finalMessage);
        JButton saveButton = createSaveButton(finalMessage);
        JButton loadButton = createLoadButton(finalMessage);

        createTripButton.setBounds(20, 50, 200, 40);
        addDestinationButton.setBounds(20, 100, 200, 40);
        viewTasksButton.setBounds(20, 150, 200, 40);
        removeActivity.setBounds(20, 200, 200, 40);
        budgetAnalysisButton.setBounds(20, 250, 200, 40);
        saveButton.setBounds(20, 300, 200, 40);
        loadButton.setBounds(20, 350, 200, 40);

        buttonPanel.add(createTripButton);
        buttonPanel.add(addDestinationButton);
        buttonPanel.add(viewTasksButton);
        buttonPanel.add(removeActivity);
        buttonPanel.add(budgetAnalysisButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        backgroundPanel.add(buttonPanel, Integer.valueOf(1));
        backgroundPanel.add(finalMessage, Integer.valueOf(2));

        window.add(backgroundPanel);
        window.revalidate();
        window.repaint();

    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage,trip
    // EFFECTS: Creates the "Create a new Trip" button and add fucntionality to it
    // using actionlistener and perform the action of making a new trip by calling a
    // new function that will input the data for the GUI.
    public JButton createTripButton(JLabel finalMessage) {
        JButton createTripButton = new JButton("Create a new Trip");

        createTripButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tripCreationGUI(finalMessage);
            }
        });

        return createTripButton;
    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage
    // EFFECTS: Creates the itinerary for the trip GUI and add fucntionality to it
    // using actionlistener and perform the action of calling a
    // new function that will input the data for the GUI.
    public JButton createAddDestinationItineraryButton(JLabel finalMessage) {
        JButton addDestinationButton = new JButton("Add Itinerary to the Trip");
        addDestinationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDestinationToTripGUI(finalMessage);
            }
        });
        return addDestinationButton;

    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage
    // EFFECTS: Creates the button which will help the user to view itinerary, and
    // add fucntionality to it
    // using actionlistener and perform the action of function used for displaying.
    public JButton createViewTasksButton(JLabel finalMessage) {
        JButton viewTasksButton = new JButton("View Itinerary");

        viewTasksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                viewTasksForDay(finalMessage);
            }
        });

        return viewTasksButton;
    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage
    // EFFECTS: Creates the button which will help the user to remove a particular
    // itinerary, and add functionality to it using actionlistener and perform the
    // action of calling the function that
    // perform the task of removing the itinerary.
    public JButton createRemoveActivity(JLabel finalMessage) {
        JButton removeActivity = new JButton("Remove itinerary");
        removeActivity.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                removeItinerary(finalMessage);
            }
        });

        return removeActivity;
    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage
    // EFFECTS: Creates the button which will help the user to a get an analysis of
    // the budget.and add functionality to it using actionlistener and perform the
    // action of calling the function that
    // perform the task of budget analysis of the trip.
    public JButton createBudgetAnalysisButton(JLabel finalMessage) {
        JButton budgetAnalysisButton = new JButton("Get a Budget Analysis");
        budgetAnalysisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int dayNumber = getDayNumberBudget(finalMessage);
                if (dayNumber >= 0) {

                    analyzeBudget(dayNumber, finalMessage);
                }
            }
        });

        return budgetAnalysisButton;
    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage
    // EFFECTS: Creates the button which will help the user to save the state of the
    // trip and add functionality to it using actionlistener and perform the
    // action of calling the function that will save the state of the trip.
    public JButton createSaveButton(JLabel finalMessage) {
        JButton saveButton = new JButton("Save Trip");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                saveTripGUI(finalMessage);
            }
        });

        return saveButton;

    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage
    // EFFECTS: Creates the button which will help the user to load the state of the
    // trip.and add functionality to it using actionlistener and perform the
    // action of calling the function that will load the state of the trip which it
    // had after saving and before closing.
    public JButton createLoadButton(JLabel finalMessage) {
        JButton loadButton = new JButton("Load Trip");
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                loadTripGUI(finalMessage);
            }
        });

        return loadButton;
    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage, trip
    // EFFECTS: Opens a dialog box allowing the user to enter the trip's city,
    // country, and trip type. After the user has entered all the details it prints
    // the final message, that the trip has been created onto the GUI.
    public void tripCreationGUI(JLabel finalMessage) {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JTextField city = new JTextField();
        JTextField country = new JTextField();
        JTextField tripType = new JTextField();

        inputPanel.add(new JLabel("Enter City:"));
        inputPanel.add(city);
        inputPanel.add(new JLabel("Enter Country:"));
        inputPanel.add(country);
        inputPanel.add(new JLabel("Trip Type (Solo, Family, Business):"));
        inputPanel.add(tripType);

        int choice = JOptionPane.showConfirmDialog(window, inputPanel, "Create your Trip",
                JOptionPane.OK_CANCEL_OPTION);

        if (choice == JOptionPane.OK_OPTION) {

            String cityString = city.getText();
            String countryString = country.getText();
            String tripTypeString = tripType.getText();

            trip = new Trips(cityString, countryString, tripTypeString);

            finalMessage.setText(" Your New Trip to " + cityString + " in " + countryString + " ( " + tripTypeString
                    + ") is created");

        }
    }

    @SuppressWarnings("methodlength")
    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage, Destination Itinerary.
    // EFFECTS:If no trip is found, an error message "No Trip found" is displayed.
    // Opens a dialog box allowing the user to enter the trip's destination
    // itinerary with date, day and activity with the inputted
    // name,location, date,duratiom,time,description,cost,status(activity completed
    // or not),
    // and budget.After the user has entered all the details it prints
    // the final message, that the destination Itinerary has been added.
    public void addDestinationToTripGUI(JLabel finalMessage) {
        if (trip == null) {
            finalMessage.setText("Please create a trip first before adding an itinerary");
            return;
        }

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JTextField date = new JTextField();
        JTextField day = new JTextField();

        inputPanel.add(new JLabel("Please enter the date "));
        inputPanel.add(date);
        inputPanel.add(new JLabel("Please enter the day Number (like Day 1 or Day 2 , an integer)"));
        inputPanel.add(day);

        int option = JOptionPane.showConfirmDialog(window, inputPanel, "Add Destination to Trip",
                JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String dateString = date.getText();
            int dayNumber = Integer.parseInt(day.getText());
            List<Activity> activitiyList = new ArrayList<>();

            boolean choice = true;

            while (choice) {
                String activityName = JOptionPane.showInputDialog("Please enter the name of the Activity");
                String location = JOptionPane.showInputDialog("Please enter the Location of the Activity");
                String activityDate = JOptionPane.showInputDialog("Please enter the Date of the Activity");
                int duration = Integer.parseInt(JOptionPane
                        .showInputDialog("Please enter the Duration (in minutes (integer)) of the Activity"));
                String time = JOptionPane.showInputDialog("Please enter the Time of the Activity (like 10:00AM)");
                String description = JOptionPane.showInputDialog("Please enter the Descrition of the Activity");
                double cost = Double.parseDouble(JOptionPane.showInputDialog("Please enter the Cost of the Activity"));
                int result = JOptionPane.showConfirmDialog(window,
                        "Is this activity completed?", "Activity Status",
                        JOptionPane.YES_NO_OPTION);
                boolean status = (result == JOptionPane.YES_OPTION);

                double budgetLimit = Double
                        .parseDouble(JOptionPane.showInputDialog("Please enter the budget limit for this activity"));
                double currentExpenditure = Double.parseDouble(JOptionPane
                        .showInputDialog("Please enter the Current spending you have done for this activity"));
                Budget budget = new Budget(budgetLimit, currentExpenditure);

                Activity activity = new Activity(activityName, location, activityDate, duration, time, description,
                        cost, status, budget);
                activitiyList.add(activity);

                int addMore = JOptionPane.showConfirmDialog(window, "Do you want to add another activity?");
                choice = (addMore == JOptionPane.YES_OPTION);
            }

            DestinationItinerary destinationItinerary = new DestinationItinerary(dateString, dayNumber, activitiyList);

            trip.addDestinationItineraries(destinationItinerary);

        }
        finalMessage.setText("Destination and Activities added successfully!");

    }

    @SuppressWarnings("methodlength")
    // REQUIRES: finalMessage is a non-null JLabel to display messages, trip not
    // null,DestinationItinerary not null.
    // MODIFIES: finalMessage
    // EFFECTS:
    // If no trip is found, an error message "No Trip found" is displayed.
    // If no itinerary exists, an error message "No Itinerary found for the trip" is
    // displayed. After that it displays the details of all activities in the trip's
    // destination
    // itineraries using a scrollable dialog box. If the trip is not found , or the
    // itineraries
    // not found it displays an error message for the same
    // gives a final message conveying that the Itineraries have been displayed.
    public void viewTasksForDay(JLabel finalMessage) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        for (DestinationItinerary itinerary : trip.getDestinationItinerary()) {
            textArea.append(
                    " Activities for Day Number: " + itinerary.getDayNumber() + " (" + itinerary.getDate() + "):\n");

            for (Activity activity : itinerary.getActivity()) {
                textArea.append("\nActivity Name: " + activity.getActivityName()
                        + "\nLocation: " + activity.getLocation()
                        + "\nDuration: " + activity.getDuration()
                        + "\nTime: " + activity.getTime()
                        + "\nDescription: " + activity.getDescription()
                        + "\nCost: $" + activity.getCost()
                        + "\nStatus: " + (activity.getStatus() ? "Completed" : "Pending")
                        + "\nBudget Limit: $" + activity.getBudget().getBudgetLimit()
                        + "\nCurrent Expenditure: $" + activity.getBudget().getCurrentExpenditure()
                        + "\n");
            }
            textArea.append("\n---------------------------------------------\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 400));

        JOptionPane.showMessageDialog(window, scrollPane, "View Itinerary Details", JOptionPane.INFORMATION_MESSAGE);

        finalMessage.setText("Displaying details of the trip");
    }

    // REQUIRES:dayNumber, activityName, finalMessage are instantiated properly and
    // not null.
    // MODIFIES: trip (this),finalMessage
    // EFFECTS: if the dayNumber is not valid or activity name is not valid it
    // returns. Otherwise remove the activity with the given name , day Number and
    // appropriate final message.
    public void removeItinerary(JLabel finalMessage) {

        int dayNumber = getDayNumber(finalMessage);
        if (dayNumber < -1) {
            return;
        }

        String activityName = getActivityName(finalMessage);
        if (activityName == null || activityName.trim().isEmpty()) {
            finalMessage.setText("Please enter a valid Activity name.");
            return;
        }

        removeActivity(dayNumber, activityName, finalMessage);
    }

    // MODIFIES: finalMessage
    // EFFECTS: inputs valid dayInput from the user. Provide an appropriate error
    // message if input entered is not valid.
    private int getDayNumber(JLabel finalMessage) {
        String dayInput = JOptionPane.showInputDialog(window,
                "Enter the day number for which you want to remove the Activity");

        if (dayInput == null || dayInput.trim().isEmpty()) {
            finalMessage.setText("Day number cannot be empty.");
            return -1;
        }

        return Integer.parseInt(dayInput.trim());
    }

    // EFFECTS: inputs activity name from the user
    private String getActivityName(JLabel finalMessage) {
        return JOptionPane.showInputDialog(window, "Enter the name of the Activity to remove");
    }

    @SuppressWarnings("methodlength")

    // REQUIRES:dayNumber, activityName, finalMessage are instantiated properly and
    // not null.
    // MODIFIES: final message
    // EFFECTS:Find the itineraries for the given day, and activtiy for that day and
    // provide with appropriate final message about the operation.
    private void removeActivity(int dayNumber, String activityName, JLabel finalMessage) {
        List<DestinationItinerary> itinerariesForDay = new ArrayList<>();
        for (DestinationItinerary itinerary : trip.getDestinationItinerary()) {
            if (itinerary.getDayNumber() == dayNumber) {
                itinerariesForDay.add(itinerary);
            }
        }

        int removedCount = 0;
        for (DestinationItinerary itinerary : itinerariesForDay) {
            Activity activityToRemove = null;
            for (Activity activity : itinerary.getActivity()) {
                if (activity.getActivityName().equalsIgnoreCase(activityName)) {
                    activityToRemove = activity;
                    break;
                }
            }

            if (activityToRemove != null) {
                itinerary.getActivity().remove(activityToRemove);
                removedCount++;
            }
        }

        if (removedCount > 0) {
            JOptionPane.showMessageDialog(window,
                    "Activity '" + activityName + "' removed successfully from Day " + dayNumber,
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            finalMessage.setText("Activity '" + activityName + "' removed for Day Number " + dayNumber);
        } else {
            JOptionPane.showMessageDialog(window, "Activity '" + activityName + "' not found in Day " + dayNumber,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // REQUIRES: final message is not null, day number is valid and not null.
    // EFFECTS:EFFECTS: If the user provides a valid day number input, the method
    // returns that value as an integer.
    // If the input is invalid, the method displays an error message
    private int getDayNumberBudget(JLabel finalMessage) {
        String dayInput = JOptionPane.showInputDialog(window,
                "Enter the day number for which you want to get the Budget Analysis for?");
        if (dayInput == null || dayInput.trim().isEmpty()) {
            finalMessage.setText("Please Enter a valid Day Number");
            return -1;
        }

        try {
            return Integer.parseInt(dayInput.trim());
        } catch (NumberFormatException e) {
            finalMessage.setText("Invalid Day Number format. Please enter a numeric value.");
            return -1;
        }
    }

    @SuppressWarnings("methodlength")
    // REQUIRES: dayNumber, activity,trip,final message to be not null and valid.
    // EFFECTS:
    // If there are activities for the given day, the method displays a detailed
    // budget analysis, showing: Activity name, budget limit, current expenditure.
    // Whether the budget limit is exceeded or if the activity is under budget.
    // The amount exceeded or the remaining budget.
    // If no activities are found for the specified day, an error message is
    // displayed.

    public void analyzeBudget(int dayNumber, JLabel finalMessage) {
        List<Activity> activitiesForDay = new ArrayList<>();
        for (DestinationItinerary itinerary : trip.getDestinationItinerary()) {
            if (itinerary.getDayNumber() == dayNumber) {
                activitiesForDay.addAll(itinerary.getActivity());
            }
        }

        if (activitiesForDay.isEmpty()) {
            JOptionPane.showMessageDialog(window, "No itinerary found for Day " + dayNumber,
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        textArea.append("Budget Analysis for the Day Number: " + dayNumber + ":\n\n");

        for (Activity activity : activitiesForDay) {
            Budget budget = activity.getBudget();
            double budgetLimit = budget.getBudgetLimit();
            double currentExpenditure = budget.getCurrentExpenditure();

            textArea.append("Activity Name: " + activity.getActivityName() + "\n");
            textArea.append("Budget Limit: " + budgetLimit + "\n");
            textArea.append("Current Expenditure: " + currentExpenditure + "\n");

            if (budget.budgetExceed()) {
                double exceeded = currentExpenditure - budgetLimit;
                textArea.append("You have exceeded your budget limit for " + activity.getActivityName() + "\n"
                        + "You exceeded the budget limit by $" + exceeded);
            } else {
                double remaining = budgetLimit - currentExpenditure;
                textArea.append("You were under the budget limit\n" + "Remaining Budget: $" + remaining + "\n");
            }

            textArea.append("\n");
        }

        JScrollPane scrollPane = new JScrollPane(textArea);

        JOptionPane.showMessageDialog(window, scrollPane, "Budget Analysis", JOptionPane.INFORMATION_MESSAGE);
    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage
    // EFFECTS: Save the current state of the trip from the given path and displays
    // a message conveying Throws an IOException if file not found.

    public void saveTripGUI(JLabel finalMessage) {
        String path = "data\\myTrip.json";
        JsonWriter writer = new JsonWriter(path);

        try {
            writer.open();
            if (trip != null) {
                writer.writeTrips(trip);
                writer.close();
                finalMessage.setText("Trip saved to " + path);
            } else {
                finalMessage.setText("Trip not found ");
            }
        } catch (FileNotFoundException e) {
            finalMessage.setText("File not Found Exception occured.");
        }

    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage
    // EFFECTS: Load the current state of the trip from the given path and displays
    // a message conveying
    // whether the trip has been loaded or not. Throws an IOException if file not
    // found.
    public void loadTripGUI(JLabel finalMessage) {
        String path = "data\\myTrip.json";
        JsonReader reader = new JsonReader(path);
        try {
            trip = reader.readTrips();
            if (trip != null) {
                finalMessage.setText("Trip loaded  from " + path);
            } else {
                finalMessage.setText("No trip found");
            }
        } catch (IOException e) {
            finalMessage.setText("File not Found Exception occured.");
        }
    }

}
