package test.java.ua.nure.krivchenko;

import com.sun.org.apache.xalan.internal.xsltc.dom.CachedNodeListIterator;
import junit.framework.TestCase;
import org.junit.Test;
import ua.nure.krivchenko.User;

import java.util.Calendar;
import java.util.Date;

public class UserTest extends TestCase {

    private static final int DAY_OF_BIRTH_FUTURE = 8;
    private static final int DAY_OF_BIRTH_TODAY = Calendar.DAY_OF_MONTH;
    private static final int MONTH_OF_BIRTH_FUTURE = Calendar.JANUARY;
    private static final int MONTH_OF_BIRTH_NOT_BEGAN = Calendar.NOVEMBER;



    private static final int DAY_OF_BIRTH = 7;
    private static final int MOTH_OF_BIRTH = Calendar.DECEMBER;
    private static final int YEAR_OF_BIRTH = 1997;
    private static final int CURRENT_YEAR = 2017;
    private static final int ETALONE_AGE = CURRENT_YEAR - YEAR_OF_BIRTH;

    private User user;
    private Date dateOfBirthd;

    protected void setUp() throws Exception{
        super.setUp();

        user = new User();

    }

    @Test
    public void testFullName() {
        user.setFirstName("Igor");
        user.setLastName("Krivchenko");
        String fullName = user.getFullName();
        assertEquals("Igor Krivchenko", fullName);
    }
    @Test
    public void testAgeDayFutureButMonthGoes1(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MOTH_OF_BIRTH,DAY_OF_BIRTH_FUTURE);
        dateOfBirthd = calendar.getTime();

        user.setDateOfBirth(dateOfBirthd);
        int Age = user.getAge();
        assertEquals(ETALONE_AGE,Age++);
    }

    @Test
    public void testAgeMonthPassedInThisYear2(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_FUTURE,DAY_OF_BIRTH);
        dateOfBirthd = calendar.getTime();

        user.setDateOfBirth(dateOfBirthd);
        int Age = user.getAge();
        assertEquals(ETALONE_AGE ,Age);
    }

    @Test
    public void testAgeToday3(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MOTH_OF_BIRTH,DAY_OF_BIRTH_TODAY);
        dateOfBirthd = calendar.getTime();

        user.setDateOfBirth(dateOfBirthd);
        int Age = user.getAge();
        assertEquals(ETALONE_AGE,Age);
    }
    @Test
    public void testAgeMonthGoesButDayFuture4(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MOTH_OF_BIRTH,DAY_OF_BIRTH);
        dateOfBirthd = calendar.getTime();

        user.setDateOfBirth(dateOfBirthd);
        int Age = user.getAge();
        assertEquals(ETALONE_AGE,Age);
    }
    @Test
    public void testAgeMonthNotBegan5(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(YEAR_OF_BIRTH,MONTH_OF_BIRTH_NOT_BEGAN,DAY_OF_BIRTH);
        dateOfBirthd = calendar.getTime();

        user.setDateOfBirth(dateOfBirthd);
        int Age = user.getAge();
        assertEquals(ETALONE_AGE,Age);
    }



}
