package Clinic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
   Scanner scnr = new Scanner(System.in);
   private ArrayList<Vet> vetList = new ArrayList<>();
   private List<Pet> registeredPets = new ArrayList<>();
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
   
   //Register appointment -> register a pet -> display all veternarians -> book a time
   //Check Medical records
   //Check Pet Status
   //Payment plan
   public void displayMenu(){
      System.out.println("What would you like to do today?");
        System.out.println("1. Register a pet");
        System.out.println("2. Select Pet and Book an Appointment");
        System.out.println("3. Check Medical Records");
        System.out.println("4. Check Pet Status");
        System.out.println("5. Payment Checkout\n ");

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
            displayVetenarians();
        }
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
        List<Pet> petsToBook = new ArrayList<Pet>();
        String choice;
        System.out.println("Select which pet(s) you would like receive care? \n");
        //figure out if pet belongs to the right owner id? do we need to do this
        int count=1;
        for(Pet pet : registeredPets)
        {
            System.out.println(count + ". " + pet.getName() +", " + pet.getPetType() + ", " + pet.getAge() + pet.getGender());
            count++;
        }
        
        choice = scnr.next();
        count = 1;
        while(!choice.equals("Done")){
            System.out.println("Enter a number to select another pet. Don't re-select");
            System.out.println("Please type "+ " 'Done' " + "when you're done with selecting");
            petsToBook.add(registeredPets.get(Integer.parseInt(choice) - 1));
            System.out.println("Here are the selected pet(s) so far: \n");
            for(Pet pet: petsToBook)
            {
                System.out.println(count + ". " + pet.getName() +", " + pet.getPetType() + ", " + pet.getAge() + pet.getGender());
            }
            choice = scnr.next();
            //have selected pets enter petsToBook
        }
        count = 1;
        System.out.println("\nHere are the pets selected for the apppointment!");
        for(Pet pet : petsToBook)
        {
            System.out.println(count + ". " + pet.getName() +", " + pet.getPetType() + ", " + pet.getAge() + pet.getGender());
            count++;
        }

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
        String petType = null;
        System.out.println("Now we would like to ask, register your pet.");
        System.out.println("Now tell us what type of pet are you choosing");
        System.out.println("1. Dog \n2. Cat\n3. Reptile\n4. Fish\n5. Birds\n6. Other");
        int answer = scnr.nextInt();
        //throw an exception if not int
        scnr.nextLine(); 
        // this allows petName to be read because for some reason a nextLine() right after
        //a nextInt() takes in the "Enter" and our petName can't be read;
        if(answer == 1)
            petType = "Dog";
        else if  (answer == 2)
            petType = "Cat";
        else if  (answer == 3)
            petType = "Reptile";
        else if  (answer == 4)
            petType = "Fish";
        else if  (answer == 5)
            petType = "Birds";
        else petType = "Other";
        

        System.out.println("What's your pet's name?");
        String petName = scnr.nextLine();

        System.out.println("What's " + petName + "'s bloodType?");
        String bloodType = scnr.nextLine();

        System.out.println("What's " + petName +"'s age?");
        int petAge = scnr.nextInt();
        scnr.nextLine();

        System.out.println("What color is "+ petName+ "?");
        String speciesColor = scnr.nextLine();
        

        System.out.println("What's "+petName+"'s gender?");
        String petGender = scnr.nextLine();

        Pet pet = new Pet(petType, petName, bloodType, petAge, speciesColor, petGender);
        registeredPets.add(pet);

        displayMenu();
    }
}
