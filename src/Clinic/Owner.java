package Clinic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
// Pet's owner
public class Owner{
    // Attributes 
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
    private int age;
    private String id;

    protected ArrayList<Pet> pet = new ArrayList<>();

    HashMap<String, ArrayList<String> > list = new HashMap<>();

    // Constructor 
    public Owner(String name, String phoneNumber, String email, String address, int age){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.age = age;
        //this.id = UUID.randomUUID().toString();
    }

    
    // Getter
    public String getName() {
        return this.name;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public String email(){
        return this.email;
    }
    public String address(){
        return this.address;
    }
    public int age(){
        return this.age;
    }
    public String getId(){
        return this.id;
    }

    // Setter
    public void setName(String ownerName){
        this.name = ownerName;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public void setAge(int age){
        this.age = age;
    }
    
    public void addPet(Pet p){
        if (p != null && !pet.contains(p)){
            pet.add(p);
        }
    }
}

