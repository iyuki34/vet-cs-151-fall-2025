## VET Project CS 151 Fall 2025
Members:
Samuel Salazar (@ssala71)
Shu Saw()
Chan Li

San José State University – CS 151 Fall 2025
Overview

The Veterinary Clinic Management System (VCMS) is a Java-based application that simulates the day-to-day operations of a small veterinary clinic.
The system allows users to:

Register clients (pet owners)
Add and manage pets
Schedule one or more appointments
Record and process payments (cash, card)

This project applies Object-Oriented Programming (OOP) principles — including inheritance, abstraction, interfaces, encapsulation, and polymorphism — to model a real-world clinic system.

Design

Main Classes and Responsibilities

Main
Entry point of the system.
Initializes all core components and launches the menu interface.

Menu
Handles user interface and input through Scanner.
Displays main menu options and routes the user to specific actions.

Owner
Represents a pet owner with name, contact info, and a list of owned pets.

Pet
Represents an individual pet, including name, species, age, and health status.
Connected to a MedicalRecord for treatment history.

Vet
Represents a veterinarian with name, specialization, and assigned appointments.

AppointmentV2
Represents an appointment between a pet, owner, and veterinarian.
Handles scheduling, cancellation, and validation for conflicts.

BookingHelper
Assists with appointment creation and logic
Checks for overlapping appointments and invalid entries.

BookingUtils
Contains utility methods for validating input, checking dates, and printing formatted data.

Medication (abstract class) + coughingMedicine(child) + inflammationMedicine (child)

Base class defining common medication attributes such as name, dosage, and price.
CoughingMedicine
Subclass of Medication designed for treating coughing and respiratory illnesses.
InflammationMedicine
Subclass of Medication designed for treating swelling or inflammation.

Service (interface)
Represents a general veterinary service such as grooming, surgery, or vaccination.

Payment
Manages payment transactions, total cost, and confirmation.

PaymentMethod
Handles the type of payment chosen by the customer (cash, card, or insurance).

Custom Exceptions
BookingConflictException: Thrown when an appointment time overlaps.
CapacityExceededException: Thrown when the system exceeds its data limits.
InvalidMenuChoiceException: Thrown when an invalid menu choice is entered.

OBJECT-ORIENTED PROGRAMMING CONCEPTS
Abstraction
The abstract class Medication defines shared properties and structure for specific medications.
All instance variables are private.
Getters and setters include validation logic for data safety.

Inheritance

CoughingMedicine and InflammationMedicine extend Medication.

AppointmentV2 and Service share similar patterns for reuse.

Polymorphism

Overridden methods in subclasses provide specialized behavior for medication administration and pricing.

Exception Handling

Custom exceptions make the system more stable and user-friendly when errors occur.

Installation Instructions
Git clone: https://github.com/ssala71/vet-cs-151-fall-2025.git

Run the Application: Execute the Main class.
The program will start the main menu and wait for user input.

Usage Example
Primary Workflow:
Initial Registration: Upon startup, the system prompts for user to enter their own personal information such as name, age, contact info and such using the terminal
The system will then list the next action from 7 options:
Option 1: Register Pet:
Option 2: Display Veterinarians
3. Check Pet's Registration Info
4. Select your pet and Book an appointment
5. Go to appointment check up
6. Pay total
7. Exit Vet
The User’s flow should look something similar to this:
Option 1: Register Pet: Users enter pet data. The system checks if the pet already exists to prevent duplicates.
Option 2: Display Veterinarians
To give the user a view of what vets are available before booking
Option 3: Select your pet and Book an appointment
User will need to select one pet from the registered list
User will then need to pick a vet from the list
User will then choose a time slot available for the appointment with the vet
Appointment is made!
Option 4: Go to Appointment Check Up(Simulate Visit):
User selects a pending appointment from the list.
The system simulates a time jump, performs a random diagnosis (Fine, Inflammation, or Coughing).
Prescribed Medication objects are created, added to the bill, and the MedicalRecord is generated and saved to the pet's history.
(The appointment is removed from the queue to prevent re-processing.)
Option 5: Pay Total
User pays the bill and gets a summary of what prescriptions were made
Option 6: Check Pet’s registration info
If the user is curious, they can check what pet they registered with the system.
Option 7: Exit Vet
Choosing option 7 to exit Vet


Contributions:


Sam
Main class
Menu class (Many of the displayMethods, not all Made sure exit was possible whenever)
created the overall UI design and concept
Custom Exceptions(BookingConflictException, CapacityExceededException, InvalidMenuChoiceException) and their implementations
coughingMedicine, inflammationMedicine, their refill order only
All of Payment, PaymentMethod, Vet classes
JUnit Tests(All)
Debugged any issues, not sure on the methods as they were a lot but the commits show


Shu Saw


Medication.java, inflammationMedicine.java, coughingMedicine.java, interface Service. This doesn't include refillOrder() in Medicines classes.
Within Menu.java
fixed scanner errors in original inputs
UI clean up and created a more consistent data inputs from the user
reordered the logic flow for Main Menu
implemented option 1. Register a pet.
implemented option 3. Check Medical records. created a helper method to select pets within registeredPetList = selectionFromList()
helped with logic flow with option 4. Select your pet and Book an appointment
Pet.java
implemented getter methods for Pet
got rid of refillStock() and added toString() to all sub med classes
made toString() and getDescription(). Now all methods within sub med classes are used
fix logic flow for  going to appointment checkup option
old displayAppointment() only randomly prescribes medication and adds to cost. it doesnt remove the existing appointment and the user can spam go to appointment and reach different results each time. Fixed
displayAppointment() was renamed to displayTreatmentAppointment() to make it more clear
within displayTreatmentAppointment(). I made a new helper method which helps list out the appointments that the user has made, user picks one, then it simulates the vet interaction with the pet and which meds are being used (printed out). Had to make the helper method because i needed a way to remove the appointment after the treatment
displayPayment()
changed how it started bc changes in displayTreatmentAppoinment.
UML:
helped Chan determining arrows for the classes relationships
Overall helped with different tiny segments throughout the different classes. 

Chan Li

Completed the Owner class – finalized the structure and logic for managing owner-related data.
Developed the AppointmentV2 class – implemented an updated version of the appointment system with enhanced functionality.
Completed BookingHelper and BookingUtils modules – built and refined helper utilities to support booking operations.
Integrated booking functionality into the main menu – enabled users to access and perform bookings directly from the application interface.
Integrated medical records management – added functionality to create medical records after appointments and display them under the third menu option.
Created the UML diagram – documented the system structure and relationships visually for better understanding and maintenance


Repository Information

Repository Name: VeterinaryClinic-CS151-Fall-2025

Instructor GitHub Username: telvinzhong

