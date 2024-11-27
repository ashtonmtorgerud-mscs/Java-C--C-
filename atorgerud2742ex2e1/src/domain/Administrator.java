package domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Administrator extends Person {
    private LocalDateTime birthDate;
    private String ssn;
    private String phone;
    private LocalDateTime employmentStartDate;

    public LocalDateTime getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getSsn() {
        return this.ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getEmploymentStartDate() {
        return this.employmentStartDate;
    }

    public void setEmploymentStartDate(LocalDateTime employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }





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
