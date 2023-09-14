# Room Scheduler Application

## Overview
This project involves the development of a Room Scheduling application for Tiny College. The application allows faculty members to reserve rooms for specific dates based on their seating requirements. It features a graphical user interface (GUI) and utilizes a Derby database for data storage. The application is designed with good Object-Oriented principles in mind, with separate classes for key entities such as Faculty, Room, Date, and Reservations.

## Program Functionality
The Room Scheduler application offers a range of functionalities designed to streamline room reservation processes at Tiny College:

1. **Add Faculty**: Add a faculty member to the database, identified by a single name.

2. **Reserve Faculty Date Seats**: Assign a room to a faculty member for a requested date, provided there are available seats. The assignment follows a best-fit approach, allocating the smallest available room that meets the seating requirements. If no suitable rooms are available, the faculty member is placed on the waiting list.

3. **Status Reservations by Date**: Display faculty members with room reservations on a specific date.

4. **Status Waiting List**: Display faculty members on the waiting list, ordered by the date of reservation request.

5. **Add Room Seats**: Add a new room to the system, specifying the room name and the number of seats in the room. When a room is added, it checks the waiting list for faculty members who were waiting for a room on all reserved dates and assigns them rooms accordingly.

6. **Add Date**: Add a new date to the system for which rooms can be reserved.

7. **Cancel Reservation Faculty Date**: Remove a reservation for a faculty member on a specific date. If the reservation is removed, the application checks the waiting list to see if another faculty member can be booked for that room and date, considering seat requirements.

8. **Status Faculty member**: Display room and date information for each room reserved by or waitlisted for a faculty member.

9. **Drop Room**: Remove a room from the application. Any faculty members who had reservations for that room on any date are assigned alternative rooms if possible, following priority sequence. If a faculty member cannot get a new room, they are placed on the waitlist.

## Software Setup
To set up the Room Scheduler application, follow these steps:

1. **Java Development Environment**: Ensure you have Java Development Kit (JDK) installed on your system. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html).

2. **NetBeans IDE**: It is recommended to use the NetBeans IDE for this project, particularly for the GUI design. You can download NetBeans from [here](https://netbeans.apache.org/download/index.html).

3. **Derby Database**: The application uses the Derby database. You can find more information and download Derby from [here](https://db.apache.org/derby/).

4. **Database Configuration**: Configure your Derby database and set up the necessary tables (Rooms, Dates, Faculty, Reservations) as per project requirements. Refer to the provided testing scenario for guidance on preloading data.

## Running the Program
Follow these steps to run the Room Scheduler application:

1. **Compile and Build**: Build the project in NetBeans to compile the code and create the executable JAR file.

2. **Run the JAR file**: Execute the JAR file generated by NetBeans to start the Room Scheduler application.

3. **Use the GUI**: Interact with the graphical user interface to perform various actions, depending on the phase of the project you are in:

   - **Adding Faculty**: Add faculty members.
   - **Reserving Rooms**: Reserve rooms for faculty on specific dates.
   - **Checking Reservations**: Check the status of reservations by date.
   - **Viewing Waiting List**: View the waiting list of faculty members.
   - **Adding Rooms with Seats**: Add rooms with seating capacities.
   - **Adding New Dates**: Add new dates for room reservations.
   - **Canceling Reservations**: Cancel reservations for faculty members on specific dates.
   - **Checking Faculty Status**: Check the status of faculty members' reservations and waitlists.
   - **Dropping Rooms**: Remove rooms from the application.

## Notes
- The GUI has been designed to make data entry and information retrieval user-friendly, with drop-down lists and combo boxes for known data.
- When executing commands, the results are displayed within the same interface for convenience.
- For database considerations, ensure that relevant data is stored without redundancy in the database tables.

For both phases of the project, it is recommended to utilize the GUI designer in NetBeans for efficient GUI development.


# Academic Integrity Statement

Please note that all work included in this project is the original work of the author, and any external sources or references have been properly cited and credited. It is strictly prohibited to copy, reproduce, or use any part of this work without permission from the author.

If you choose to use any part of this work as a reference or resource, you are responsible for ensuring that you do not plagiarize or violate any academic integrity policies or guidelines. The author of this work cannot be held liable for any legal or academic consequences resulting from the misuse or misappropriation of this work.

Any unauthorized copying or use of this work may result in serious consequences, including but not limited to academic penalties, legal action, and damage to personal and professional reputation. Therefore, please use this work only as a reference and always ensure that you properly cite and attribute any sources or references used.