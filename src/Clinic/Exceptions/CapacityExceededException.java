package Clinic.Exceptions;
public class CapacityExceededException extends RuntimeException {
    public CapacityExceededException(String msg){
        super(msg);
    }
}
