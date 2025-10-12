package Clinic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
   Scanner scnr = new Scanner(System.in);
   private ArrayList<Vet> vetList = new ArrayList<>();
   private List<Pet> registeredPets = new ArrayList<>();
   private List<Appointment> appointments = new ArrayList<>();
   private Owner user = null; //tracks the current user using scanner
   
   public Menu(){
      Vet generalVet = new Vet("Dr.", "M", 65, "General Expert", "Monday, Tuesday, Wednesday, Thursday", "8AM-3PM");
      Vet catVet = new Vet("Dr.", "F", 32, "Cat Expert", "Monday, Thursday, Saturday", "12PM-5PM");
      Vet dogVet = new Vet("Dr.", "M", 35, "Dog Expert", "Sunday, Tuesday, Thuesday, Saturday", "12PM-5PM");
      Vet birdVet = new Vet("Dr.", "F", 28, "Bird Expert","Monday, Tuesday, Friday", "1PM-6PM");
      Vet reptileVet = new Vet("Dr.", "F", 25,"Reptile Expert","Friday, Tuesday", "1PM-8PM");

      vetList.add(generalVet);
      vetList.add(dogVet);
      vetList.add(catVet);
      vetList.add(birdVet);
      vetList.add(reptileVet);

      introMenu();
   }

   public void introMenu(){
      System.out.println("Welcome to ____ Vetenarian. We welcome you with open arms!");
        System.out.println("Now we would like to ask for your name.");
        String name = scnr.nextLine();

        System.out.println("Great, lovely to meet you " + name + "! We would like a little bit more info before we proceed.");

        System.out.println("What number would we be able to contact you?");
        String number = scnr.nextLine();

        System.out.println("Perfect. Whats your email address to send you updates via email?");
        String email = scnr.nextLine();

        System.out.println("What address could we send mail to?");
        String address = scnr.nextLine();

        System.out.println("Final question. How old are you?");
        int age = scnr.nextInt();


        user = new Owner(name,number,address,email,age);

        System.out.println("Thank you! " + name + " for registering with us!");

        displayMenu();
   }
   
   //Select Pet and book an appointmet -> Select registered pets to book -> display vets and choose and time 
   //Check Medical records
   //Check Pet Status
   //Payment plan
   public void displayMenu(){
      System.out.println("What would you like to do today?");
        System.out.println("1. Register a pet");
        System.out.println("2. Select Pet and Book an Appointment");
        System.out.println("3. Check Medical Records");
        System.out.println("4. Payment Checkout");
        System.out.println("5. Exit Vet\n ");

        Scanner scnr = new Scanner(System.in);
        int option = scnr.nextInt();

        if (option == 1){
            //Register a pet 
            //need to check if they already have some pet registered
            if (registeredPets.size() > 0) {
                System.out.println("1. You currently have " + registeredPets.size() + " pet(s) registered under your name.");
                System.out.println("Would you like to register another pet?\n");
                System.out.println("1. Register a pet");
                System.out.println("2. Back to main menu");
                int ans = scnr.nextInt();
                if (ans == 1)
                    displayPetMenu();
                else if(ans == 2)
                    displayMenu();
                //else throw an exception error;

       } else {
            System.out.println("1. You currently have no pets registered.");
            System.out.println("Would you like to register a pet?\n");
            System.out.println("1. Register a pet");
            System.out.println("2. Back to main menu");
            int ans = scnr.nextInt();
            if (ans == 1)
                    displayPetMenu();
            else if(ans == 2)
                    displayMenu();
            //else throw an exception error;
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
        }
        if (option == 5){
            System.out.println("Thank you for visiting our Hospital and we hope you enjoyed your visit");
            scnr.close();
        }
   }

   //helper method, might use instead of coding it twice
   public ArrayList<Pet> selectionFromList(){
    
    return null;
    
   }
   public void displayMedicalRecords(){
    //need to check if pet's record has been recorded before
    //if first time then no, if after check up then yes

    if(registeredPets.size() == 0){
        System.out.println("You have no registered pet(s), please go back to the main menu");
        System.out.println("1. Back to main menu");
        int ans = scnr.nextInt();
        if (ans == 1)
            displayMenu();
        // else throw an exception and return back to this message and try re-entering #1 to go back to main menu
    }
    else{
        //based on medication + appointment classes data
        //Select Pet(s) to book an appointment with
        List<Pet> petsToCheckRecords= new ArrayList<Pet>();
        String choice;
        System.out.println("Select which pet(s) you would like to see medical records\n");
        //figure out if pet belongs to the right owner id? do we need to do this
        int count=1;
        for(Pet pet : registeredPets)
        {
            System.out.println(count + ". " + pet.getName() +", " + pet.getPetType() + ", " + pet.getAge() + pet.getGender());
            count++;
        }
        
        choice = scnr.next();
        count = 1;
        //can throw an EXCEPTION here if the user doesn't pick a number within the range of registeredPets.size()
        while(!choice.equals("Done")){
            System.out.println("Enter a number to select another pet. Don't re-select");
            System.out.println("Please type "+ " 'Done' " + "when you're done with selecting");
            petsToCheckRecords.add(registeredPets.get(Integer.parseInt(choice) - 1));
            System.out.println("Here are the selected pet(s) so far: \n");
            for(Pet pet: petsToCheckRecords)
            {
                System.out.println(count + ". " + pet.getName() +", " + pet.getPetType() + ", " + pet.getAge() + ", " + pet.getGender());
            }
            choice = scnr.next();
            //have selected pets enter petsToCheckRecords
        }
        System.out.println("\nDisplaying records for your selected pets!");
        for(Pet pet : petsToCheckRecords)
        {
            System.out.println("Name: " + pet.getName() +"\nPet Type: " + pet.getPetType() + "\nAge: " + pet.getAge() + "\nGender : " +pet.getGender()
            + "\nBlood Type: " + pet.getBloodType() + "\nColor: " + pet.getSpeciesColor() +"\n");
            /**
             * need appointment class to be done and the option == 2 in main menu logic flow to be done
             * 
             * something like this? 
             * Purpose: print out medical records and past cost if there's any
             * for (Appointment apt : appointments) {
            //     if (apt.getPet().equals(p)) {
            //         // display diagnosis, services, and costs
            //     }
             */
        }

        //forced to go back to main menu bc this isn't the last stop
        System.out.println("1. Back to main menu");
        int ans = scnr.nextInt();
        if (ans == 1)
            displayMenu();
        else displayMenu();
        
    }

   }

   public void displayVetenarians(){
        Scanner scnr = new Scanner(System.in);
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

            int result = scnr.nextInt();

            if (result == 1){
                //Go back to the main menu
            }
            if (result == 2){
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
        //PET TYPE!!!
        String petType = null;
        System.out.println("\nNow we would like to ask, register your pet.");
        System.out.println("Now tell us what type of pet are you choosing (1-6)");
        String[] typeList = {"Dog", "Cat", "Reptile", "Fish", "Birds", "Others"};
        for (int i = 0; i < typeList.length; i++) {
            System.out.println((i + 1) + ". " + typeList[i]);
        } 
        int choice = scnr.nextInt(); 
        //throw an exception if not int
        scnr.nextLine(); 
        // scnr.nextLine() allows petName to be read because for some reason a nextLine() right after
        //a nextInt() takes in the "Enter" and our petName can't be read;
        if(choice >= 1 && choice <= typeList.length){
            petType = typeList[choice -1];
        }
        else {
            //throw a invalid inputer exception
            System.out.println("Invalid input");
            return;
        }

        //PET NAMEEE!!!
        System.out.println("\nWhat's your pet's name?");
        String petName = scnr.nextLine();

        System.out.println("What's " + petName + "'s bloodType?");
        String[] bloodTypes = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};
        for (int i = 0; i < bloodTypes.length; i++) {
            System.out.println((i + 1) + ". " + bloodTypes[i]);
        }   
        choice = scnr.nextInt();
        String bloodType;
        if(choice >= 1 && choice <= bloodTypes.length){
            bloodType = bloodTypes[choice -1];
        }
        else {
            //throw a invalid inputer exception
            System.out.println("Invalid input");
            return;
        }
        
        //PET AGEEE
        System.out.println("\nWhat's " + petName +"'s age? (years)");
        int petAge = scnr.nextInt();
        scnr.nextLine();

        //PET COLORR
        System.out.println("\nWhat color is "+ petName+ "?");
        String speciesColor = scnr.nextLine();
        
        //PET GENDERRR
        System.out.println("What's "+petName+"'s gender? (1-2)");
        String[] genderList = {"Female","Male"};
        for (int i = 0; i < genderList.length; i++) {
            System.out.println((i + 1) + ". " + genderList[i]);
        }   
        choice = scnr.nextInt();
        String petGender;
        if(choice >= 1 && choice <= genderList.length){
            petGender = genderList[choice -1];
        }
        else {
            //throw a invalid inputer exception
            System.out.println("Invalid input");
            return;
        }

        Pet pet = new Pet(petType, petName, bloodType, petAge, speciesColor, petGender);
        registeredPets.add(pet);

        displayMenu();
    }
}
