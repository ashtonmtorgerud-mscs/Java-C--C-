package domain;

import exceptions.PersonIllegalArgumentException;

import java.time.LocalDateTime;
import java.util.Objects;

public class Person {
    private int personId;
    protected String firstName;
    private String lastName;
    private String userName;
    private LocalDateTime updated;

    public Person() {
        this.personId = 0;
        this.firstName = "";
        this.lastName = "";
        this.userName = "";
        this.updated = LocalDateTime.now();
    }
    public Person(int personId, String firstName, String lastName, String userName) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.updated = LocalDateTime.now();
    }

    public int getPersonId()                    { return this.personId; }
    public String getFirstName()                { return this.firstName; }
    public String getLastName()                 { return lastName; }
    public String getUserName()                 { return userName; }
    public void setUpdated()                    { this.updated = LocalDateTime.now(); }
    public LocalDateTime getUpdated()           { return updated; }
    public String getFullName()                 { return this.firstName + " " + this.lastName; }
    public String getFullNameID()                 { return "[" + this.personId + "] " + this.firstName + " " + this.lastName; }




//    public String setPersonId(int personId) {
//        String errMsg = "";
//        if (personId >= 101 && personId <= 999)
//            this.personId = personId;
//        else
//            errMsg = Integer.toString(personId) + " is invalid. PersonId must be >= 101 and <= 999";
//
//        return errMsg;
//    }

    public void setPersonId(int personId) {
        if (personId >= 101 && personId <= 999)
            this.personId = personId;
        else
//            throw new IllegalArgumentException(Integer.toString(personId) + " is invalid. PersonID must be more than 101 and less than 999");
        throw new PersonIllegalArgumentException(Integer.toString(personId) + " is invalid. PersonID must be more than 101 and less than 999");
    }


    public void setFirstName(String firstName) {
        if (firstName != null && firstName.length() > 2 && firstName.length() <= 15)
            this.firstName = firstName;
        else
//            throw new IllegalArgumentException(firstName + " is invalid. First name must be less than or equal to 15");
        throw new PersonIllegalArgumentException(firstName + " is invalid. First name must be less than or equal to 15");
    }

    public void setLastName(String lastName) {
        if (lastName != null && lastName.length() >= 2 && lastName.length() <= 30) {
            this.lastName = lastName;
        }
        else
            throw new IllegalArgumentException(lastName + " is invalid. Last name must be less than or equal to 30");

    }

    public void setUserName(String userName){
        if (userName!= null && userName.length() >= 5 && userName.length() <= 30) {
            switch (userName.toLowerCase()) {
                case "admin":
                case "administrator":
                case "supervisor":
                    throw new IllegalArgumentException(userName + " is invalid. Admin user names not allowed");
                default:
                    this.userName = userName;
                    break;
            }
        }
        else
            throw new IllegalArgumentException(userName + " is invalid. Username must be less than or equal to 5");

    }




    @Override
    public String toString(){
        String returnString = Integer.toString(personId) + " " + lastName + ", " + firstName;
        return returnString;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
//        return getPersonId() == person.getPersonId() && Objects.equals(getFirstName(), person.getFirstName()) && Objects.equals(getLastName(), person.getLastName()) && Objects.equals(getUserName(), person.getUserName()) && Objects.equals(getUpdated(), person.getUpdated());
        return getPersonId() == person.getPersonId();
    }


    @Override
    public int hashCode() {
//        return Objects.hash(getPersonId(), getFirstName(), getLastName(), getUserName(), getUpdated());
        return Objects.hash(getPersonId());
    }
}