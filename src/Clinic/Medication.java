package Clinic;

public abstract class Medication implements Service{
    /** There's no reason to re-modify the medication names and price */
    private final String medicationName;
    private final double price;
    private int numDosesInStock;
    //private refilldate;
    public abstract String toHeal();
    public abstract int refillOrder();

    public Medication(String medicationName, double price, int numDosesInStock) {
        this.medicationName = medicationName;
        this.price = price;
        this.numDosesInStock = numDosesInStock;
        //this.expirationDate; // get from appointment
    }
    int x=0;
    
    /**
     * Protected accessor for subclasses to read the medication price.
     */
    protected double getPrice() {
        return this.price;
    }
    
}
