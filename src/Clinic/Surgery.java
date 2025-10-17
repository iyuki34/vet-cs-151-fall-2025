package Clinic;

public class Surgery implements Service {
    private String name;
    private int duration;
    private double cost;
    private String vet;
    
    public Surgery(String name, int duration, double cost, String vet) {
        this.name = name;
        this.duration = duration;
        this.cost = cost;
        this.vet = vet;
    }

    @Override
    public double getCost() {
        return cost;    
    }

    @Override
    public String getDescription() {
        return "Surgery: " + name + " by Dr. " + vet + ". Assumed Duration is " + duration + " mins and the it costs $" + cost;
    }  
}
