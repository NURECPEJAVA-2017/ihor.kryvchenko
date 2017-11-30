package ua.nure.krivchenko;

import junit.framework.TestCase;
import org.junit.Test;


public class UserTest extends TestCase {

    public Integer lessNull(Integer x)
    {
        if(x < 0)
            return 0;
        else
            return 1;
    }

    public Integer checkNull(Integer x)
    {
        if(x == 0)
            return 1;
        else
            return 0;
    }

    public Integer checkExcess(Integer x){
        if(x > 120)
            return 0;
        else
            return 1;
    }

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
        Integer expectedLessNull = 1;
        Integer expectedCheckNull = 0;
        Integer expectedCheckExcess = 1;
        User user = new User();
        user.setAge(100);
        Integer receivedAge = user.getAge();
        assertEquals(expectedLessNull,lessNull(receivedAge)); // will pass the test if age > 0
        assertEquals(expectedCheckNull,checkNull(receivedAge)); // will pass the test if age != 0
        assertEquals(expectedCheckExcess,checkExcess(receivedAge)); //will pas the test if age < 120

    }

}