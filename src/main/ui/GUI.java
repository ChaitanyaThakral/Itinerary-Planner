package ui;

import model.Trips;
import persistence.JsonReader;
import persistence.JsonWriter;
import model.Activity;
import model.DestinationItinerary;
import model.EventLog;
import model.Item;
import model.Budget;
import model.Checklist;
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
    private List<Item> checklist;
    private Checklist check;

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
        checklist = new ArrayList();

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
        backgroundPanel.setLocation(100, 100);
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
        finalMessage.setBounds(20, 415, 760, 40);
        finalMessage.setForeground(Color.BLACK);
        finalMessage.setFont(new Font("Arial", Font.BOLD, 20));

        JButton createTripButton = createTripButton(finalMessage);
        JButton addDestinationButton = createAddDestinationItineraryButton(finalMessage);
        JButton viewTasksButton = createViewTasksButton(finalMessage);
        JButton removeActivity = createRemoveActivity(finalMessage);
        JButton budgetAnalysisButton = createBudgetAnalysisButton(finalMessage);
        JButton saveButton = createSaveButton(finalMessage);
        JButton loadButton = createLoadButton(finalMessage);
        JButton createChecklisOperationtButton = createChecklistButton(finalMessage);

        createTripButton.setBounds(20, 25, 200, 40);
        addDestinationButton.setBounds(20, 75, 200, 40);
        viewTasksButton.setBounds(20, 125, 200, 40);
        removeActivity.setBounds(20, 175, 200, 40);
        budgetAnalysisButton.setBounds(20, 225, 200, 40);
        createChecklisOperationtButton.setBounds(20, 275, 200, 40);
        saveButton.setBounds(20, 325, 200, 40);
        loadButton.setBounds(20, 375, 200, 40);

        buttonPanel.add(createTripButton);
        buttonPanel.add(addDestinationButton);
        buttonPanel.add(viewTasksButton);
        buttonPanel.add(removeActivity);
        buttonPanel.add(budgetAnalysisButton);
        buttonPanel.add(createChecklisOperationtButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        backgroundPanel.add(buttonPanel, Integer.valueOf(1));
        backgroundPanel.add(finalMessage, Integer.valueOf(2));

        window.add(backgroundPanel);
        window.revalidate();
        window.repaint();

    }

    public JButton createChecklistButton(JLabel finalMessage) {
        JButton createChecklistButtonLabel = new JButton("Operate on Checklist");

        createChecklistButtonLabel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createChecklistStart(finalMessage);
            }
        });
        return createChecklistButtonLabel;
    }

    public void createChecklistStart(JLabel finalMessage) {

        JFrame checklistFrame = new JFrame("Checklist Operations");
        checklistFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checklistFrame.setSize(800, 500);
        checklistFrame.setLocation(370, 160);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);

        ImageIcon bgIcon = new ImageIcon("data/Background Image.jpeg");
        Image bgImage = bgIcon.getImage().getScaledInstance(800, 500, Image.SCALE_SMOOTH);
        JLabel backgroundLabel = new JLabel(new ImageIcon(bgImage));
        backgroundLabel.setBounds(0, 0, 800, 500);

        JButton checklistButton = createTheChecklistButton(finalMessage);
        JButton checklistViewButton = createChecklistViewButton(finalMessage);
        JButton removeItemButton = createRemoveItemButton(finalMessage);
        JButton addButton = createAddButton(finalMessage);
        JButton remainItemNumberButton = createRemainItemNumberButton(finalMessage);
        JButton changeStatusButton = createChangeStatusButton(finalMessage);
        JButton exitCheckListOperationButton = createExitCheckListButton(finalMessage);

        checklistButton.setBounds(20, 25, 200, 40);
        addButton.setBounds(20, 75, 200, 40);
        checklistViewButton.setBounds(20, 125, 200, 40);
        removeItemButton.setBounds(20, 175, 200, 40);
        remainItemNumberButton.setBounds(20, 225, 200, 40);
        changeStatusButton.setBounds(20, 275, 200, 40);
        exitCheckListOperationButton.setBounds(20, 325, 200, 40);

        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(checklistButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(addButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(checklistViewButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(removeItemButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(remainItemNumberButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(changeStatusButton, JLayeredPane.PALETTE_LAYER);
        layeredPane.add(exitCheckListOperationButton, JLayeredPane.PALETTE_LAYER);

        checklistFrame.setContentPane(layeredPane);

        checklistFrame.revalidate();
        checklistFrame.repaint();

        window.setVisible(false);
        checklistFrame.setVisible(true);
    }

    public JButton createExitCheckListButton(JLabel finalMessage) {
        JButton exitCheckListOperationButton = new JButton("Exit Checklist");
        exitCheckListOperationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitOperation(finalMessage);
            }

        });
        return exitCheckListOperationButton;
    }

    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // EFFECTS: creates the button to change the status of item in the checklist.

    public JButton createChangeStatusButton(JLabel finalMessage) {
        JButton changeStatusButton = new JButton("Change status of items");
        changeStatusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeStatusOperation(finalMessage);
            }

        });
        return changeStatusButton;
    }

    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // EFFECTS: creates the button to count the number of items which are not packed
    // in the checklist.

    public JButton createRemainItemNumberButton(JLabel finalMessage) {
        JButton remainItemNumberButton = new JButton("Find No. of items in Checklist");
        remainItemNumberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remainItemNumberOperation(finalMessage);
            }
        });

        return remainItemNumberButton;
    }
    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // EFFECTS:create the button to create a checklist.

    public JButton createTheChecklistButton(JLabel finalMessage) {
        JButton checklistButton = new JButton("Create a Checklist");
        checklistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChecklistOperation(finalMessage);
            }
        });

        return checklistButton;
    }
    // REQUIRES:finalMessage is a non-null JLabel to display messages.

    // EFFECTS: creates a button to add item in the checklist.

    public JButton createAddButton(JLabel finalMessage) {
        JButton addButton = new JButton("Add item to Checklist");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItemOperation(finalMessage);
            }
        });

        return addButton;
    }
    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // EFFECTS:creates a button to remove item in the checklist.

    public JButton createRemoveItemButton(JLabel finalMessage) {
        JButton removeItemButton = new JButton("Remove item from Checklist");
        removeItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItem(finalMessage);
            }
        });

        return removeItemButton;
    }
    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // EFFECTS: creates a button to display items of the checklist.

    public JButton createChecklistViewButton(JLabel finalMessage) {
        JButton checklistViewButton = new JButton("View the Checklist");
        checklistViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checklistView(finalMessage);
            }
        });

        return checklistViewButton;
    }

    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // MODIFIES:finalMessage,checklist
    // EFFECTS:change the status of the given item name.

    public void changeStatusOperation(JLabel finalMessage) {
        String checkName = JOptionPane
                .showInputDialog("Enter the name of item for which the status has to be changed ?");
        String status = JOptionPane.showInputDialog("Enter the new status of item (yes/no)");
        for (Item i : checklist) {
            if (i.getName().equals(checkName)) {
                checklist.remove(i);
                Boolean choice;
                if (status.equals("yes") || status.equals("Yes")) {
                    choice = true;
                } else {
                    choice = false;
                }
                Item item = new Item(checkName, choice);
                checklist.add(item);
            }
        }
        finalMessage.setText("Item remove Successfully");
    }
    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // MODIFIES:finalMessage,checklist
    // EFFECTS:add item of the given name and status to the checklist.

    public void addItemOperation(JLabel finalMessage) {
        String checkName = JOptionPane.showInputDialog("Enter the name of item to pack:");
        String choicemid = JOptionPane
                .showInputDialog("Enter the status of the item whether packed or not (yes/no) ?");
        Boolean checkStatus;
        if (choicemid.equals("yes")) {
            checkStatus = true;
        } else {
            checkStatus = false;
        }

        Item item = new Item(checkName, checkStatus);
        checklist.add(item);
        finalMessage.setText("Item added successfully!");
    }

    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // MODIFIES:finalMessage,checklist
    // EFFECTS: Gives the count of remaining item in the checklist.

    public void remainItemNumberOperation(JLabel finalMessage) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.append("There are " + checklist.size() + " items in the list");
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JOptionPane.showMessageDialog(window, scrollPane, "Display Checklist Details", JOptionPane.INFORMATION_MESSAGE);
        finalMessage.setText("Checklist operation of count done successfully!");
    }

    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // MODIFIES:finalMessage,checklist
    // EFFECTS: creates a checklist and add items into it.

    public void createChecklistOperation(JLabel finalMessage) {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        Boolean choice = true;
        while (choice) {
            String checkName = JOptionPane.showInputDialog("Enter the name of item to pack:");
            String choicemid = JOptionPane
                    .showInputDialog("Enter the status of the item whether packed or not (yes/no) ?");
            Boolean checkStatus;
            if (choicemid.equals("yes")) {
                checkStatus = true;
            } else {
                checkStatus = false;
            }

            Item item = new Item(checkName, checkStatus);
            checklist.add(item);

            int addMore = JOptionPane.showConfirmDialog(window, "Do you want to add another item to the Checklist?",
                    "Add more items?", JOptionPane.YES_NO_OPTION);
            choice = (addMore == JOptionPane.YES_OPTION);

        }
        finalMessage.setText("Checklist created successfully!");

    }

    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // MODIFIES:finalMessage,checklist
    // EFFECTS: display the checklist.

    public void checklistView(JLabel finalMessage) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);

        for (Item i : checklist)
            textArea.append(
                    "Item Name: " + i.getName() +
                            "\nPacking Status: " + i.getStatus() +
                            "\n------------------------------------\n");

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JOptionPane.showMessageDialog(window, scrollPane, "View Checklist Details", JOptionPane.INFORMATION_MESSAGE);

        finalMessage.setText("Displaying details of the Checklist");

    }

    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // MODIFIES:finalMessage,checklist
    // EFFECTS:remove the item of given name from the checklist.

    public void removeItem(JLabel finalMessage) {
        Boolean choice = true;
        while (choice) {
            String checkName = JOptionPane.showInputDialog("Enter the name of item to remove from the Checklist:");
            for (Item i : checklist) {
                if (i.getName().equals(checkName)) {
                    checklist.remove(i);
                }
            }

            int addMore = JOptionPane.showConfirmDialog(window,
                    "Do you want to remove another item from the Checklist?",
                    "remove more items?", JOptionPane.YES_NO_OPTION);
            choice = (addMore == JOptionPane.YES_OPTION);
        }

        finalMessage.setText("Items removed successfully!");

    }

    // REQUIRES:finalMessage is a non-null JLabel to display messages.
    // MODIFIES:finalMessage,checklist
    // EFFECTS: exit the checklist operation and head to the main menu.

    public void exitOperation(JLabel finalMessage) {

        JFrame checklistFrame = (JFrame) SwingUtilities.getWindowAncestor(finalMessage);
        checklistFrame.setVisible(false);

        window.setVisible(true);

        finalMessage.setText("Returned to main menu.");
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

                itinerary.loggingFunctionForRemoveActivity(activityToRemove);

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
    // EFFECTS: Save the current state of the trip and the checklist from the given
    // path and displays
    // a message conveying Throws an IOException if file not found.
    public void saveTripGUI(JLabel finalMessage) {
        String path = "data\\myTrip.json";
        String checklistPath = "data\\myChecklist.json";
        JsonWriter writer = new JsonWriter(path);
        JsonWriter writerChecklist = new JsonWriter(checklistPath);

        try {
            writer.open();
            if (trip != null) {
                writer.writeTrips(trip);
                writer.close();
                finalMessage.setText("Trip saved to " + path);
            } else {
                finalMessage.setText("Trip not found.");
            }
        } catch (FileNotFoundException e) {
            finalMessage.setText("File not found exception occurred while saving trip.");
        }

        try {
            writerChecklist.open();
            if (checklist != null && !checklist.isEmpty()) {
                Checklist check = new Checklist();
                for (Item i : checklist) {
                    check.addItem(i);
                }
                writerChecklist.writeChecklist(check);
                writerChecklist.close();
                finalMessage.setText(finalMessage.getText() + " Checklist saved to " + checklistPath);
            } else {
                finalMessage.setText("No checklist found to save.");
            }
        } catch (FileNotFoundException e) {
            finalMessage.setText("File not found exception occurred while saving checklist.");
        }
    }

    // REQUIRES: finalMessage is a non-null JLabel to display messages.
    // MODIFIES: finalMessage
    // EFFECTS: Load the current state of the trip and the checklist from the given
    // path and displays
    // a message conveying
    // whether the trip has been loaded or not. Throws an IOException if file not
    // found.

    public void loadTripGUI(JLabel finalMessage) {
        String path = "data\\myTrip.json";
        String checklistPath = "data\\myChecklist.json";
        JsonReader reader = new JsonReader(path);
        JsonReader readerChecklist = new JsonReader(checklistPath);

        try {
            trip = reader.readTrips();
            if (trip != null) {
                finalMessage.setText("Trip loaded from " + path);
            } else {
                finalMessage.setText("No trip found.");
            }
        } catch (IOException e) {
            finalMessage.setText("File not found exception occurred while loading trip.");
        }

        try {
            Checklist check = readerChecklist.readChecklist();
            if (check != null) {
                checklist = check.getChecklist();
                finalMessage.setText(finalMessage.getText() + " Checklist loaded from " + checklistPath);
            } else {
                finalMessage.setText(finalMessage.getText() + " No checklist found.");
            }
        } catch (IOException e) {
            finalMessage
                    .setText(finalMessage.getText() + " File not found exception occurred while loading checklist.");
        }
    }

}
