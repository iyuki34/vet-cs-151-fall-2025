package Clinic;


public class coughingMedicine extends Medication implements Service{
/** There's no reason to re-modify the medication names and price */
    private final double insuranceRate = .65;
    public coughingMedicine() {
        super("Coughing Medication", 50.0,30); 
    }

    @Override
    public String toHeal() {
       return "Healed coughing";
    }

    //still need to consider insurance. or maybe do insurance after the final cost
    public double getCost(){
        return getPrice();
    }

    @Override
    public String getDescription() {
        return "Medication for treating coughing symptoms.";
    }

    @Override
    public void refillOrder() {
        if (getStock() == 1){
            System.out.println("Refilling Doses!");
            setStock(30);
        }
    }
    
}
