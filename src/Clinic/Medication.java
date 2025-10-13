package Clinic;

import Clinic.Exceptions.CapacityExceededException;

public abstract class Medication implements Service{
    /** There's no reason to re-modify the medication names and price */
    private final String medicationName;
    private final double price;
    private int numDosesInStock;
    //private refilldate;
    public abstract String toHeal();
    public abstract void refillOrder();

    public Medication(String medicationName, double price, int numDosesInStock) {
        this.medicationName = medicationName;
        this.price = price;
        this.numDosesInStock = numDosesInStock;
        //this.expirationDate; // get from appointment
    }
    
    protected void setStock(int numDosesInStock){
        this.numDosesInStock = numDosesInStock;
    }
    protected int getStock(){
        return numDosesInStock;
    }
    /**
     * Protected accessor for subclasses to read the medication price.
     */
    protected double getPrice() {
        return this.price;
    }

    protected void useOneDose(){
        if (numDosesInStock <= 0){
            throw new CapacityExceededException("Out of " + medicationName);
        }
        numDosesInStock--;
    }
    
}
