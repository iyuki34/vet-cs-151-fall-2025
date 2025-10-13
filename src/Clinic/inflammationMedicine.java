package Clinic;

public class inflammationMedicine extends Medication implements Service{
    /** There's no reason to re-modify the medication names and price */
    private final double insuranceRate = .75; 
    
    public inflammationMedicine() {
        super("Anti-Inflammatory Cream", 100.00,1); 
    }

    @Override
    public String toHeal() {
       return "Healed inflammation";
    }

    //still need to consider insurance. or maybe do insurance after the final cost
    @Override
    public double getCost(){
        if (hasInsurance() == true){
            return getPrice() * insuranceRate;
        }
        return getPrice();
    }


    @Override
    public String getDescription(){
        return "Inflammation Medicine to help with burning sensations and ripped tissues";
    }

    @Override
    public void refillOrder() {
        if (getStock() == 1){
            System.out.println("Refilling Doses!");
            setStock(30);
        }
    }


}
