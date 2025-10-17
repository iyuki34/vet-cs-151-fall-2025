package Clinic;

public class Surgery implements Service {
    private String name;
    private int duration;
    private double cost;
    private Vet vet;
    
    public Surgery(String name, int duration, double cost, Vet vet) {
        this.name = name;
        this.duration = duration;
        this.cost = cost;
        this.vet = vet;
    }
    public String getName() {
        return name;
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
