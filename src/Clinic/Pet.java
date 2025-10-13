package Clinic;

import java.util.UUID;

public abstract class Pet{
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

    public String getPetType(){
        return this.petType;
    }

    public String getName(){
        return this.name;
    }

    public String getBloodType(){
        return this.bloodType;
    }

    public int getAge(){
        return this.age;
    }

    public String getSpeciesColor(){
        return this.speciesColor;
    }

    public String getGender(){
        return this.gender;
    }

    public String getId(){
        return this.id;
    }
}
