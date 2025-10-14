package Clinic;

public class inflammationMedicine extends Medication implements Service{
    /** There's no reason to re-modify the medication names and price */
    
    public inflammationMedicine() {
        super("Anti-Inflammatory Cream", 100.00,1); 
    }

    @Override
    public String toHeal() {
       return "Your pet has inflammation. Prescribing inflammation medicine";
    }

    //still need to consider insurance. or maybe do insurance after the final cost
    @Override
    public double getCost(){
        return getPrice();
    }



    //should be used final payment method in menu
    @Override
    public String getDescription(){
        return "Medication for treating redness, swelling, pain, etc... ";
    }

    @Override
    public String toString() {
        return "Anti-Inflammatory Cream, Price: " + getCost() + " , Quantity: " + getStock();
    }


}
