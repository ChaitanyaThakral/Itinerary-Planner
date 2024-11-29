# Travel Itinerary Planner

## A perfect tool for all your travel needs.

**X and Y of the project**  

**X (DestinationItinerary):** *Represents the full itinerary for a particular destination, comprising activities, hotel details, flight information, and other related information.*

**Y (Trips):** *Represents the trips the user is planning to take, including details of the destination(city, country), trip type (e.g., Business, Solo, Family)*


**What will the application do?**  
*The application is designed to assist travel enthusiasts in organizing and managing their travel plans efficiently.*

Users can categorize their trips into various types, such as Business, Solo, or Family Vacation. They can create new itineraries or manage and edit existing ones, with each itinerary offering customizable options to suit individual needs. Users can input details about their destinations, including the country, city, and optional images. Flight information, such as boarding times, and accommodation details, including check-in and check-out times, can also be added. Additionally, users can jot down notes for specific days to record fascinating encounters or important details.Application also provides a checklist feature for the items that they need to carry before the trip like passport,documents etc

A budgeting feature which will help users manage their travel expenses effectively. Users can track daily spending and set budgets for specific activities, with notifications alerting them when they approach or exceed their budget limits. Upon completion of the itinerary, the application will generate a comprehensive analysis of daily and overall expenses, helping users review their spending patterns and make informed decisions for future travels. 

**Who will use it?**
- Families who are planning vacation.
- Business Professionals
- Frequent Travelers 
- International Students 
- Bloggers

**Why is this project of interest to you?**  
As an international student with a passion for travel, I often find myself needing to return to my home country during the holidays. However, managing my travel plans can be quite challenging. Currently, I either jot down notes in an unorganized manner or store them in various folders on my laptop, which makes it difficult to keep track of everything. Creating a checklist becomes cumbersome, especially when I have to manually delete items once I've packed them. Therefore, a Travel Itinerary Planner will be a great tool to tackle these problems.

## User Strories  
- As a user, I want to be able to add a destination itinerary (X) to my trips (Y), including details such as destination, dates, and activities, so that I can effectively plan and manage the various aspects of my trip.
- As a user, I want to be able to view all the tasks and related information for a particular destination for a speciifc day.
- As a user, I want to create a checklist of all items that I may need for my trip.
- As a user, I want to set a budget for each activity and be able to review and analyze my daily spending to determine if I am staying within my budget or exceeding it.
- As a user, I want to edit or change any existing plans for the day for a particular destination.
- As a user, I want to be able to generate a summary report of my travel itinerary, including total expenses, number of destinations, and activities.
- As a user, I want the option to save the entire state of my itinerary, including trips, activities, budgets, and checklists to a file.
- As a user, I want the option to load the previously saved state of my itinerary from a file, allowing me to resume exactly where I left off.

# Instructions for End User
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the "Create a new Trip",and  entering the input required like city, country, trip type and press ok to create the trip.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking on the "Add Itinerary to the Trip", and entering the required input like date, day Number, and activity details like name of the activity,location of the activity, date,
duration,time taken to complete the activity, description, cost, Status of completion of the activity and budget. And selecting if you want to add more activtiy for that particular activity by selected OK or cancel. if selected OK it adds one more activity, if selected cancel it directs the user to the main menu of GUI.
- You can generate the third required action related to the user story "adding multiple Xs to a Y" by clicking the "View Itinerary".After selecting it displays the itinerary for the trip for all the days.
- You can generate the forth required action related to the user story "adding multiple Xs to a Y" by clicking the button named "Remove itinerary", by inputting the day and name of the activity which is to removed. By pressing OK the activtiy for that day and name is removed.
- You can generate the fifth required action related to the user story "adding multiple Xs to a Y" by clicking on the button named "Get a Budget Analysis" user can input the day for which the Budget analysis is to obtained and the required analysis is displayed to the user.
- You can locate my visual component by going to the data folder, this is the path "data\Background Image.jpeg" for the image.
- You can save the state of my application by clicking on the "Save Trip" button and it will save the state of the Trip.
- You can reload the state of my application by clicking on the "Load Trip" button and it will load the state of the Trip.

# Phase 4: Task 2
Event Log:
Fri Nov 29 11:00:58 PST 2024: The Trip to Vancouver was Loaded
Fri Nov 29 11:00:58 PST 2024: The Trip to Vancouver in Canada of Trip Type Solo was successfully created
Fri Nov 29 11:01:16 PST 2024: Destination Itinerary for Day Number 1 was added Successfully
Fri Nov 29 11:01:37 PST 2024: Destination Itinerary for Day Number 2 was added Successfully
Fri Nov 29 11:01:51 PST 2024: The activity 'Golf' was removed from the Destination Itinerary.
Fri Nov 29 11:02:09 PST 2024: The Budget Analysis for the requested Day with Budget limit and Current Expenditure was displayed
Fri Nov 29 11:02:13 PST 2024: The Trip to Vancouver was Saved
if after saving the application is run again Than
Fri Nov 29 11:03:38 PST 2024: The Trip to Vancouver was Loaded
Fri Nov 29 11:03:38 PST 2024: The Trip to Vancouver in Canada of Trip Type Solo was successfully created
Fri Nov 29 11:03:38 PST 2024: Destination Itinerary for Day Number 1 was added Successfully
Fri Nov 29 11:03:38 PST 2024: Destination Itinerary for Day Number 2 was added Successfully

# Phase 4: Task 3
The TripMaker class currently is performing the dual function of both the console and GUI interface. This makes the trip maker really long and difficult to debug. Refactoring can be done to seperate the functionalities of the trip Maker class into 2 more classes like ConsoleUI class and GUI class. By this refactoring the functionalities of both the GUI and the console will be seperate making the UI class easier to debug. As in this case ConsoleUI will  manage all input/output interactions for the console-based application and GUI class will handle graphical user interface interactions.

Moreover, some of the methods in TripMaker like analyzeBudget(), removeItinerary(), are really big because they are checking if the input entered by the user is valid and than performing their respective task of analyzing the budget or removing the activity from itinerary. By refactoring these methods it will be ensured that they perform their sole task only and are not performing multiple things which makes the code less cohesive and difficult to debug. This will improve the readibility of the code also.
