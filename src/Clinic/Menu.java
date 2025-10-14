package Clinic;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

import Clinic.Exceptions.BookingConflictException;
import Clinic.Exceptions.InvalidMenuChoiceException;

public class Menu {
   Scanner scnr = new Scanner(System.in);
   private ArrayList<Vet> vetList = new ArrayList<>();
   private List<Pet> registeredPets = new ArrayList<>();
   private List<AppointmentV2> appointments = new ArrayList<>();
   private BookingHelper bookingHelper = new BookingHelper();
   private List<Service> services = new ArrayList<>();
   private Owner user = null; //tracks the current user using scanner
   private PaymentMethod method;

   private int count = 1;
   private double totalCost;
   
   public Menu(){
      Vet generalVet = new Vet("Dr. Ethan Hayes", "M", 65, "General Expert", "Monday, Tuesday, Wednesday, Thursday", "8AM-3PM");
      Vet catVet = new Vet("Dr. Abigal Lee", "F", 32, "Cat Expert", "Monday, Thursday, Saturday", "12PM-5PM");
      Vet dogVet = new Vet("Dr. Marcus Liu", "M", 35, "Dog Expert", "Sunday, Tuesday, Thuesday, Saturday", "12PM-5PM");
      Vet birdVet = new Vet("Dr. Emily Carter", "F", 28, "Bird Expert","Monday, Tuesday, Friday", "1PM-6PM");
      Vet reptileVet = new Vet("Dr. Natalie Nguyen Sophia Patel", "F", 25,"Reptile Expert","Friday, Tuesday", "1PM-8PM");

      vetList.add(generalVet);
      vetList.add(dogVet);
      vetList.add(catVet);
      vetList.add(birdVet);
      vetList.add(reptileVet);

      introMenu();
   }

   public void introMenu(){
      System.out.println("Welcome to Pet Wellness Clinic. We welcome you with open arms!");
        System.out.println("Now we would like to ask for your name.");
        String name = readStringExit();

        System.out.println("Great, lovely to meet you " + name + "! We would like a little bit more info before we proceed.");

        System.out.println("What number would we be able to contact you?");
        String number = readStringExit();

        System.out.println("Perfect. Whats your email address to send you updates via email?");
        String email = readStringExit();

        System.out.println("What address could we send mail to?");
        String address = readStringExit();

        System.out.println("Final question. How old are you?");
        
        int age = readIntExit();
        if (age < 18){
            throw new IllegalArgumentException ("You are not an adult, you can't register on the vet");
        }
        //throw exception if not int

        user = new Owner(name,number,address,email,age);

        System.out.println("Thank you! " + name + " for registering with us!");

        displayMenu();
   }
   
   //Select Pet and book an appointmet -> Select registered pets to book -> display vets and choose and time 
   //Check Medical records
   //Check Pet Status
   //Payment plan
   public void displayMenu(){

    boolean running = true;
    while (running) {
        System.out.println("===Main Menu===");
        System.out.println("What would you like to do today?");
        System.out.println("1. Register a pet");
        System.out.println("2. Display Veterinarians");
        System.out.println("3. Check Pet's Registration Info");
        System.out.println("4. Select your pet and Book an appointment");
        System.out.println("5. Go to appointment check up");
        System.out.println("6. Pay total");
        System.out.println("7. Exit Vet\n ");

        int option = readIntExit();;

        if (option == 1){
            //Register a pet 
            //need to check if they already have some pet registered
            if (registeredPets.size() > 0) {
                System.out.println("\nYou currently have " + registeredPets.size() + " pet(s) registered under your name.");
                System.out.println("Would you like to register another pet?");
                System.out.println("1. Register a pet");
                System.out.println("2. Back to main menu\n");
                int ans = readIntExit();;
                if (ans == 1)
                    displayPetMenu();
                else if(ans == 2)
                    displayMenu();
                //else throw an exception error;
                else{
                  throw new InvalidMenuChoiceException("Invalid option");
                }

       } else {
            System.out.println("\nYou currently have no pets registered.");
            System.out.println("Would you like to register a pet?");
            System.out.println("1. Register a pet");
            System.out.println("2. Back to main menu\n");
            int ans = readIntExit();;
            if (ans == 1)
                    displayPetMenu();
            else if(ans == 2)
                    displayMenu();
            //else throw an exception error;
            else{
                  throw new InvalidMenuChoiceException("Invalid option");
                }
       }
        }
        if (option == 2){
            //display vet options and appointment avalibility
            displayVetenarians();
        }
        if (option == 3){
            //checks if pet's medicalRecord
            displayMedicalRecords();
        }
        if (option == 4){
            //calls appointment class
            bookAppointment();
        }
        if (option == 5){
           displayTreatmentAppointment();
        }
        if (option == 6){
            displayPaymentMethod();
        }
        if (option == 7){
          System.out.println("Thank you for visiting our Hospital and we hope you enjoyed your visit");
            quit();
        }
      }
   }

   //helper method for 2. Select Pet(s) and Book an Appointment") 3. Check Medical Records"
   //Select which pets to see from the registeredPet list
   public List<Pet> selectionFromList(){
    List<Pet> selectionFromList = new ArrayList<Pet>();
    count=1;
    String choice;
        for(Pet pet : registeredPets)
        {
            System.out.println(count + ". " + pet.getName() +", " + pet.getPetType() + ", " + pet.getAge() + ", "+ pet.getGender());
            count++;
        }
        
        choice = readStringExit();
        count = 1;
        //can throw an EXCEPTION here if the user doesn't pick a number within the range of registeredPets.size()
        while(!choice.equals("Done")){
            System.out.println("Enter a number to select another pet. Don't re-select");
            System.out.println("Please type "+ " 'Done' " + "when you're done with selecting");
            selectionFromList.add(registeredPets.get(Integer.parseInt(choice) - 1));
            System.out.println("Here are the selected pet(s) so far: \n");
            for(Pet pet: selectionFromList)
            {
                System.out.println(count + ". " + pet.getName() +", " + pet.getPetType() + ", " + pet.getAge() + ", " + pet.getGender());
            }
            choice = readStringExit();
            //have selected pets enter petsToCheckRecords
        }
    return selectionFromList;
   }
   public void displayMedicalRecords(){
    //need to check if pet's record has been recorded before
    //if first time then no, if after check up then yes

    if(registeredPets.size() == 0){
        System.out.println("You have no registered pet(s), please go back to the main menu");
        System.out.println("1. Back to main menu");
        int ans = readIntExit();
        if (ans == 1)
            return;
        // else throw an exception and return back to this message and try re-entering #1 to go back to main menu
        else{
                  throw new InvalidMenuChoiceException("Invalid option");
                }
    }
    else{
        //based on medication + appointment classes data
        //Select Pet(s) to book an appointment with
        List<Pet> petsToCheckRecords= new ArrayList<Pet>();
        //String choice;
        System.out.println("\nSelect which pet(s) you would like to see medical records\n");
        petsToCheckRecords = selectionFromList();
        //figure out if pet belongs to the right owner id? do we need to do this
        
        System.out.println("\nDisplaying records for your selected pets!");
        for(Pet pet : petsToCheckRecords)
        {
            System.out.println("Name: " + pet.getName() +"\nPet Type: " + pet.getPetType() + "\nAge: " + pet.getAge() + "\nGender : " +pet.getGender()
            + "\nBlood Type: " + pet.getBloodType() + "\nColor: " + pet.getSpeciesColor() +"\n");

        }
        }
    }


   public void displayVetenarians(){
        System.out.println("===All Vetenarians===");
        int page = 1;
        int index = 0;
        while (true){
            
            Vet v = vetList.get(index);

            System.out.println("Vetenarian " + page);
            System.out.println("Name: " + v.getName());
            System.out.println("Age: " + v.getAge());
            System.out.println("Gender: "+ v.getGender());
            System.out.println("Availability: " + v.getAvailableDays());
            System.out.println("1. Exit out");
            System.out.println("2. Next Page (" + page + "/5)");
            //need the ability to choose the Vet
            //After choosing, methods within Appointment class are called? to determine and register the booking time

            if (page >= 2){
                System.out.println("3. Previous page (" + page + "/5)");
            }

            int result = readIntExit();

            if (result == 1){
                //return to main menu
                displayMenu();
            }
            if (result == 2){
               if (page == 5){
                  System.out.println("We are at the last page.");
                  continue;
               }
                index++;
                page++;
            }
            if (result == 3){
                index--;
                page--;
                
            }
        }
        
    } 

    public void displayPetMenu(){
        //PET TYPE
        String petType = null;
        System.out.println("===Pet Resgistration===");
        System.out.println("\nNow we would like to ask, register your pet.");
        System.out.println("Now tell us what type of pet are you choosing (1-6)");
        String[] typeList = {"Dog", "Cat", "Reptile", "Fish", "Birds", "Others"};
        for (int i = 0; i < typeList.length; i++) {
            System.out.println((i + 1) + ". " + typeList[i]);
        } 
        int choice = readIntExit(); 
        //throw an exception if not int
        // scnr.nextLine() allows petName to be read because for some reason a nextLine() right after
        //a nextInt() takes in the "Enter" and our petName can't be read;
        if(choice >= 1 && choice <= typeList.length){
            petType = typeList[choice -1];
        }
        else{
                  throw new InvalidMenuChoiceException("Invalid option");
         }

        //PET NAME
        System.out.println("\nWhat's your pet's name?");
        String petName = readStringExit();

        System.out.println("\nWhat's " + petName + "'s bloodType?");
        String[] bloodTypes = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
        for (int i = 0; i < bloodTypes.length; i++) {
            System.out.println((i + 1) + ". " + bloodTypes[i]);
        }   
        choice = readIntExit();
        String bloodType;
        if(choice >= 1 && choice <= bloodTypes.length){
            bloodType = bloodTypes[choice -1];
        }
        else{
                  throw new InvalidMenuChoiceException("Invalid option");
                }
        
        //PET AGE
        System.out.println("\nWhat's " + petName +"'s age? (years)");
        int petAge = readIntExit();;

        //PET COLOR
        System.out.println("\nWhat color is "+ petName+ "?");
        String speciesColor = readStringExit();
        
        //PET GENDER
        System.out.println("\nWhat's "+petName+"'s gender? (1-2)");
        String[] genderList = {"Female","Male"};
        for (int i = 0; i < genderList.length; i++) {
            System.out.println((i + 1) + ". " + genderList[i]);
        }   
        choice = readIntExit();
        String petGender;
        if(choice >= 1 && choice <= genderList.length){
            petGender = genderList[choice -1];
        }
        else{
                  throw new InvalidMenuChoiceException("Invalid option");
                }

        Pet pet = new Pet(petType, petName, bloodType, petAge, speciesColor, petGender);
        registeredPets.add(pet);

        return;
    }
    private void bookAppointment(){
        if (registeredPets.isEmpty()) {
            System.out.println("No registered pets. Please register a pet first.");
            return;
        }

        // 1. Select one pet
        System.out.println("Select a pet to book for:");
        for (int i = 0; i < registeredPets.size(); i++) {
            Pet p = registeredPets.get(i);
            System.out.printf("%d) %s (%s, %d years)\n", i + 1, p.getName(), p.getPetType(), p.getAge());
        }
        int petChoice = readIntExit();
        Pet chosenPet = registeredPets.get(petChoice - 1);

        // 2. Select a vet
        System.out.println("Select a vet:");
        for (int i = 0; i < vetList.size(); i++) {
            Vet v = vetList.get(i);
            System.out.printf("%d) %s - %s - %s\n", i + 1, v.getName(), v.getTitle(), v.getAvailableDays());
        }
        int vetChoice = readIntExit();
        Vet chosenVet = vetList.get(vetChoice - 1);

        // 3. Choose a date from vet's available weekdays
        Set<DayOfWeek> availableWeekdays = BookingUtils.parseAvailableDays(chosenVet.getAvailableDays());
        if (availableWeekdays.isEmpty()) {
            System.out.println("This vet has no available weekdays listed. Returning to menu.");
            return;
        }

        // get next 1 week of available dates
        List<LocalDate> candidateDates = BookingUtils.getNextMatchingDates(availableWeekdays, 7); 
        System.out.println("\nSelect a date from the vet's available days (0 to cancel):");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("EEE yyyy-MM-dd", Locale.ENGLISH);
        for (int i = 0; i < candidateDates.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, candidateDates.get(i).format(df));
        }

        int dateChoice = readIntExit();
        if (dateChoice == 0 || dateChoice > candidateDates.size()) return;
        LocalDate chosenDate = candidateDates.get(dateChoice - 1);

        // 4. Generate time slots based on vet's working hours
        LocalTime[] workHours = BookingUtils.parseWorkingHours(chosenVet.getAvailableHour());
        List<LocalTime> slots = BookingUtils.generateSlots(workHours[0], workHours[1], 60); // 1-hour slots

        System.out.println("\nAvailable time slots for " + BookingUtils.fmtDate(chosenDate) + " (0 to cancel):");
        for (int i = 0; i < slots.size(); i++) {
            LocalDateTime dt = LocalDateTime.of(chosenDate, slots.get(i));
            String status = bookingHelper.isSlotFree(chosenVet, dt) ? "free" : "booked";
            System.out.printf("%d) %s [%s]%n", i + 1, slots.get(i), status);
        }

        int slotChoice = readIntExit();
        if (slotChoice == 0 || slotChoice > slots.size()) return;
        LocalTime chosenTime = slots.get(slotChoice - 1);
        LocalDateTime when = LocalDateTime.of(chosenDate, chosenTime);

        // 5. Book
        AppointmentV2 appt = new AppointmentV2(chosenVet, user, chosenPet, when);
        if (bookingHelper.book(appt)) {
            System.out.println("Appointment booked successfully!");
        } else {
            throw new BookingConflictException("Sorry, this slot is already taken.");

        }
        // 6. Show details of the booked appointment
        System.out.println("Appointment details:\n" + appt.toString());
        return;
    }

    private void quit(){
      System.out.println("Goobye!");
         scnr.close();
         System.exit(0);
    }

    private String readStringExit(){
      String s = scnr.nextLine().trim();
      if ("EXIT".equalsIgnoreCase(s)){
         quit();
         return "EXIT";
      }
      return s;
    }

    private int readIntExit() {
    while (true) {
        if (!scnr.hasNext()) {
            quit(); 
        }

        if (scnr.hasNextInt()) {
            int v = scnr.nextInt();
            if (scnr.hasNextLine()) scnr.nextLine(); 
            return v;
        }

        String tok = scnr.next().trim();
        if ("EXIT".equalsIgnoreCase(tok)) {
            quit();
        }
        if (scnr.hasNextLine()) scnr.nextLine();

        System.out.print("Please enter a number (or type EXIT): ");
    }
}

    public void displayAppointmentTime(){
        //Appointment Scheduling Section
        System.out.println("Here are the availible appointment times for today:");
        LocalTime time1 = LocalTime.of(12, 0);
        LocalTime time2 = LocalTime.of(13, 0); //1pm
        LocalTime time3 = LocalTime.of(14, 0); //2pm
        LocalTime time4 = LocalTime.of(15, 0); //3pm
        LocalTime time5 = LocalTime.of(16, 0); //4pm

        System.out.println("1. " + time1);
        System.out.println("2. " + time2);
        System.out.println("3. " + time3);
        System.out.println("4. " + time4);
        System.out.println("5. " + time5);
        System.out.println("Please enter your choice (1-5)");
        int appointmentTime = readIntExit();

        //converting (int) appointmentTime into LocalTime reference 
        LocalTime timeChoice;
        if (appointmentTime == 1) {
            timeChoice = time1;
        } else if (appointmentTime == 2) {
            timeChoice = time2;
        }
        else if (appointmentTime == 3) {
            timeChoice = time2;
        } 
        else if (appointmentTime == 4) {
            timeChoice = time2;
        }
        else {
            timeChoice = time5;
        }

        LocalDateTime appointmentDateTime = LocalDateTime.of(LocalDate.now(), timeChoice);
        System.out.println(appointmentDateTime);
    }

    public void displayPaymentMethod(){
      if (totalCost == 0){
            System.out.println("You have no outstanding bill. Go back to menu.");
            return;
        }
      System.out.println("Great, you have decided to pay!");
      System.out.println("What type of format would you like to pay?");
      System.out.println("1. Cash");
      System.out.println("2. Card");

      int input = readIntExit();

      if (input == 1){
         method = new PaymentMethod("CASH");
      }
      else if (input == 2){
         method = new PaymentMethod("CARD");
      }
      else{
         System.out.println("Invalid choice. Defaulting cash.");
         method = new PaymentMethod("CASH");

      }

      System.out.println("Here are the medications prescribed and bill:");
      System.out.println();
      for(Service service : services)
      {
        System.out.println(service.toString());
        System.out.println("Purpose of medication: "+ service.getDescription() + "\n");
        
      }
      System.out.println("Your total cost will be " + totalCost + ". Is that okay?");
      System.out.println("1. Yes");
      System.out.println("2. No");

      int confirm = readIntExit();
        if (confirm == 1) {
            System.out.println("Payment confirmed! Thank you for your business.");
            totalCost = 0;
        } else {
            System.out.println("Payment canceled.");
        }

    }

    //Select appointment
    //Mark as completed
    //Remove from queue to avoid spamming going to appointment which rises the prices
    void displayTreatmentAppointment(){
      if (bookingHelper.allBookings().isEmpty()){
         System.out.println("You have no appointments. Go back to menu.");

         return;
      }
      System.out.println("\nShowing list of pending appointments");
      //selectedApt = which appointment to simulate the vet's healing and bills
      List<AppointmentV2> selectedApts = selectAppointmentToProcess(); 

      AppointmentV2 appt = selectedApts.get(0); 
    

      System.out.println("It's time for the appointment with ." + appt.getVet().getName() + " for " + appt.getPet().getName() + ".");
      System.out.println(appt);

      int result = (int)(Math.random() * 3);

      if (result == 0) {
        System.out.println("Your pet is perfectly fine! No medicine needed.");
        bookingHelper.cancel(appt);
        return;
    } else if (result == 1) {
         inflammationMedicine inflameMed = new inflammationMedicine();
         services.add(inflameMed);
         System.out.println(inflameMed.toHeal());

         double cost = inflameMed.getCost();
         totalCost += cost;
         inflameMed.useOneDose();
         System.out.println("The inflammation medicine is around $" + cost + "\nAdded to total amount: " + totalCost + "\n");
    } else {
        coughingMedicine coughMedicine = new coughingMedicine();
        services.add(coughMedicine);
        System.out.println(coughMedicine.toHeal());

        double cost = coughMedicine.getCost();
        totalCost += cost;
        coughMedicine.useOneDose();
        System.out.println("The coughing medicine is around $" + cost + "\nAdded to total amount: " + totalCost + "\n");
    }
    bookingHelper.cancel(appt);
    System.out.println("Total Cost: " + totalCost);
    
    
   }
   private List<AppointmentV2> selectAppointmentToProcess() {
    while(true){
        List<AppointmentV2> currentBookings = bookingHelper.allBookings();
        if(currentBookings.size() == 0)
            System.out.println("\nYou have no appointments");
        for (int i = 0; i < currentBookings.size(); i++) {
                AppointmentV2 app = currentBookings.get(i);
                System.out.printf("%d) %s with %s at %s\n", i + 1, app.getPet().getName(), app.getVet().getName(), app.getWhen());
            }
        System.out.println("\nChoose one pending appointment to process");
        try {
                int choice = scnr.nextInt();
                scnr.nextLine(); 
                
                if (choice > 0 && choice <= currentBookings.size()) {
                    // Returns a list containing the single selected appointment
                    List<AppointmentV2> selected = new ArrayList<>();
                    selected.add(currentBookings.get(choice - 1));
                    return selected; 
                } else {
                    System.err.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.err.println("\nInvalid input. Please enter a number.");
                scnr.nextLine();
            }

    }

    
   }
}
