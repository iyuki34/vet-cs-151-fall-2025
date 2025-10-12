package Clinic;
import java.util.UUID;


public class Pet{
    // Attributes
    private String petType;
    private String name;
    private String bloodType;
    private int age;
    private String speciesColor;
    private String gender;
    private String id;

    // Constructor
    public Pet(String petType, String name, String bloodType, int age, String speciesColor, String gender){
        this.petType = petType;
        this.name = name;
        this.bloodType = bloodType;
        this.age = age;
        this.speciesColor = speciesColor;
        this.gender = gender;
        this.id = UUID.randomUUID().toString();
    }

    public String getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public String getPetType(){
        return this.petType;
    }

    public int getAge(){
        return this.age;
    }

    public String getGender(){
        return this.gender;
    }
    
}
