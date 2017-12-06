package ua.nure.krivchenko;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;


public class UserTest extends TestCase {

    private User user;
    private Date dateOfBirthd;

    protected void setUp() throws Exception{
        super.setUp();

        user = new User();

        Calendar calendar = Calendar.getInstance();
        calendar.set(1997,calendar.DECEMBER,6);
        dateOfBirthd = calendar.getTime();
    }

    @Test
    public void testFullName() {
        user.setFirstName("Igor");
        user.setLastName("Krivchenko");
        String fullName = user.getFullName();
        assertEquals("Igor Krivchenko", fullName);
    }
    @Test
    public void testAge(){
        user.setDateOfBirth(dateOfBirthd);
        int Age = user.getAge();
        assertEquals(2017-1997,Age);
    }

}
