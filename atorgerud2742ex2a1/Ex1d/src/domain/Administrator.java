package domain;

import java.time.LocalDateTime;

public abstract class Administrator extends Person {
    private LocalDateTime birthDate;
    private String ssn;
    private String phone;
    private LocalDateTime employmentStartDate;


    Administrator (int personId, String firstName, String lastName, String username, LocalDateTime birthDate, String ssn, String phone, LocalDateTime employmentStartDate) {
        super(personId, firstName, lastName, username);
    }

    @Override
    public String toString() {
        return super.toString() +
                " Tenant{" +
                "birthdate= " + birthDate +
                ", ssn= " + ssn + '\'' +
                ", phone= " + phone + '\'' +
                ", emplomentStartDate= " + employmentStartDate +
                "}";

    }


    public abstract double calcGrossPay();

}
