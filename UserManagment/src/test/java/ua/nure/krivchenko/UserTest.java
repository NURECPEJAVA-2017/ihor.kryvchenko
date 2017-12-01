package ua.nure.krivchenko;

import junit.framework.TestCase;
import org.junit.Test;


public class UserTest extends TestCase {

    @Test
    public void testFullName() {
        User user = new User();
        user.setFirstName("Igor");
        user.setLastName("Krivchenko");
        String fullName = user.getFullName();
        assertEquals("Igor Krivchenko", fullName);
    }
    @Test
    public void testAge(){

    }

}
