package ua.nure.krivchenko;

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

}
