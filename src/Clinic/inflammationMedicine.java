package Clinic;

public class inflammationMedicine extends Medication implements Service{
    /** There's no reason to re-modify the medication names and price */

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
        return getPrice();
    }

    @Override
    public String getDescription(){
        return "Inflammation Medicine to help with burning sensations and ripped tissues";
    }

    @Override
    public int refillOrder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refillOrder'");
    }


}
