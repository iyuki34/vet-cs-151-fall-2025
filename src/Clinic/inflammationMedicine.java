package Clinic;

public class inflammationMedicine extends Medication implements Service{
    /** There's no reason to re-modify the medication names and price */
    
    public inflammationMedicine() {
        super("Anti-Inflammatory Cream", 100.00,10); 
    }

    @Override
    public String toHeal() {
       return "Healed inflammation";
    }

    //still need to consider insurance. or maybe do insurance after the final cost
    @Override
    public double getCost(){
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
