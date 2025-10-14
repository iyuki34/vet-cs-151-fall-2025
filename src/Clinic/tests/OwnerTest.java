import org.junit.Test;
import static org.junit.Assert.*;



public class OwnerTest {
    @Test
    public void testConstructor() {
        Owner owner  = new Owner("Jeremy", "5006768991", "hdjfhjkh@gmail.com", "San Jose", 30);
        assertEquals("name should be Jeremy", "Jeremy", owner.getName());
        assertEquals("phone number should be 5006768991", "5006768991", owner.getPhoneNumber());
    }
}
