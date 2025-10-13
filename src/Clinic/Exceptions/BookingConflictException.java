package Clinic.Exceptions;
public class BookingConflictException extends RuntimeException{
    public BookingConflictException(String msg){
        super(msg);
    }
}
