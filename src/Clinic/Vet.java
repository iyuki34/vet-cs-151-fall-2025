package Clinic;

import java.util.ArrayList;
import java.util.Scanner;
public class Vet {
    private String name;
    private String gender;
    private int age;
    private String title;
    private String availableDays;
    private String availableHour;
    
    public Vet(String name, String gender, int age, String title, String availableDays, String availibleHour){
        this.name = name;
        this.gender = gender;
        this.title = title;
        this.age = age;
        this.availableDays = availableDays;
        this.availableHour = availibleHour;
    }

    public String getName(){
        return this.name;
    }
    
    public int getAge() {
        return age;
    }

    public String getAvailableDays() {
        return availableDays;
    }

    
    public String getAvailableHour() {
        return availableHour;
    }

    public String getGender() {
        return gender;
    }
    
    public String getTitle() {
        return title;
    }

     

}
