package Clinic;

public class Payment {
    private double totalAmount;
    private final PaymentMethod method;
    
    public Payment(double totalAmount, PaymentMethod method){
        this.totalAmount = totalAmount;
        this.method = method;
    }
    
    public PaymentMethod getMethod() {
        return method;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}