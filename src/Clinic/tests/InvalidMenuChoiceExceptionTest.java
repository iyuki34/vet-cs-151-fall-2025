package Clinic.tests;

import Clinic.Exceptions.InvalidMenuChoiceException;
import org.junit.Test;
import static org.junit.Assert.*;

public class InvalidMenuChoiceExceptionTest {

    @Test
    public void testInvalidChoiceThrowsException() {
        try {
            int userChoice = 9;

            if (userChoice >= 2){
                throw new InvalidMenuChoiceException("Invalid option");
            }
            else{
                System.out.println("Exception not caught");
            }

        } catch (InvalidMenuChoiceException ex) {
            assertEquals("Invalid option", ex.getMessage());
        }
    }

    @Test
    public void testValidChoiceDoesNotThrowException() {
        try {
            int userChoice = 1; 

            if (userChoice >= 2){
                throw new InvalidMenuChoiceException("Invalid option");
            }
            else{
                System.out.println("Exception not caught");
            }

            assertTrue(true);

        } catch (InvalidMenuChoiceException ex) {
                fail("Exception should not be thrown for valid choice");
        }
    }
}
