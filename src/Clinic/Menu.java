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
      Vet generalVet = new Vet("Dr.", "male", 65, "General Expert", "Monday, Tuesday, Wednesday, Thursday");
      Vet catVet = new Vet("Dr.", "female", 32, "Cat Expert", "Monday, Thursday, Saturday");
      Vet dogVet = new Vet("Dr.", "male", 35, "Dog Expert", "Sunday, Tuesday, Thuesday, Saturday");
      Vet birdVet = new Vet("Dr.", "female", 28, "Bird Expert","Monday, Tuesday, Friday");
      Vet reptileVet = new Vet("Dr.", "female", 25,"Reptile Expert","Friday, Tuesday");

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
        System.out.println("2. Check Medical Records");
        System.out.println("3. Check Pet Status");
        System.out.println("4. Checkout");

        Scanner scnr = new Scanner(System.in);
        int option = scnr.nextInt();

        if (option == 1){
            //Register a pet 
            //need to check if they already have some pet registered
            if (registeredPets.size() > 0) {
                System.out.println("1. You currently have " + registeredPets.size() + " pet(s) registered under your name.");
                System.out.println("Would you like to register another pet?");
                System.out.println("1. Register a pet");
                System.out.println("2. Back to main menu");
                int ans = scnr.nextInt();
                if (ans == 1)
                    displayPetMenu();
                else displayMenu();

       } else {
            System.out.println("1. You currently have no pets registered.");
            System.out.println("Would you like to register a pet?");
            System.out.println("1. Register a pet");
            System.out.println("2. Back to main menu");
            int ans = scnr.nextInt();
            if (ans == 1)
                    displayPetMenu();
                else displayMenu();
       }
        }
        if (option == 2){
            //medical records display
        }
        if (option == 3){
            //checks if pet is healthy
        }
        if (option == 4){
            //calls appointment class
        }
        if (option == 5){
            displayVetenarians();
        }
   }

   private String moreThanOnePetMenu() {
       System.out.println("\n--- PET REGISTRATION ---");
       // assume the owner already has at least one pet so they can register another
       if (user.pet.size() > 0) {
           System.out.print("You have a pet registered. Do you want to register a new one? (Y/N): ");
           String ans = scnr.nextLine().trim().toUpperCase();

           if (ans.equals("Y")) {
               return  "Y";// Assuming first pet for simplicity
           }
       }
       return "N";
   }
   public void displayMedicalRecords(){
    //need to check if pet's record has been recorded before
    //if first time then no, if after check up then yes
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
        

        System.out.println("What's your pet's name?\n");
        String petName = scnr.nextLine();

        System.out.println("What's " + petName + "'s bloodType?\n");
        String bloodType = scnr.nextLine();

        System.out.println("What's " + petName +"'s age?\n");
        int petAge = scnr.nextInt();

        System.out.println("What color is "+ petName+ "?\n");
        String speciesColor = scnr.nextLine();

        System.out.println("What's "+petName+"'s gender?\n");
        String petGender = scnr.nextLine();

        Pet pet = new Pet(petType, petName, bloodType, petAge, speciesColor, petGender);
        registeredPets.add(pet);
    }
}
