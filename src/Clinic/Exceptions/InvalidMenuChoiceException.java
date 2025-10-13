package Clinic.Exceptions;
public class InvalidMenuChoiceException extends RuntimeException {
    public InvalidMenuChoiceException(String msg){
        super(msg);
    }
}
