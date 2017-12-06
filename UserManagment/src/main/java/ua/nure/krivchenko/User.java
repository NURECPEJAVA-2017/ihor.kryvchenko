package ua.nure.krivchenko;

import java.util.Calendar;
import java.util.Date;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    public void setId(Long id){
        this.id = id;
    }
    public Long getId(){
        return id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public String getFirstName(){
        return firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth = dateOfBirth;
    }
    public Date getDateOfBirth(){
        return dateOfBirth;
    }

    public String getFullName(){
        return new StringBuilder().append(firstName).append(" ").append(lastName).toString();
    }

    public int getAge(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);

        calendar.setTime(getDateOfBirth());

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        if(currentMonth < month) {
            int recivedYear = currentYear - year + 1 ;
            return recivedYear;
        }

        else if(currentMonth == month && currentDay < day) {
            int recivedYear = currentYear - year + 1;
            return recivedYear;
        }

        int recivedYear = currentYear - year ;
        return recivedYear;
    }

}
