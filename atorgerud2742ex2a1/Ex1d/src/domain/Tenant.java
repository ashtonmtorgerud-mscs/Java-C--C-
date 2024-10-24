package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class Tenant extends Person {

    private LocalDateTime birthDate;
    private String ssn;
    private String phone;
    private String employer;
    private String occupation;
    private double grossPay;
    private LocalDateTime employmentStartDate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Tenant(int personId, String firstName, String lastName, String username,
                  LocalDateTime birthDate, String ssn, String phone,
                  String employer, String occupation, double grossPay, LocalDateTime employmentStartDate){
        super(personId, firstName, lastName, username);
        this.birthDate = birthDate;
        this.ssn = ssn;
        this.occupation = occupation;
        this.employer = employer;
        this.employmentStartDate = employmentStartDate;
        this.phone = phone;
        this.grossPay = grossPay;

    }


    @Override
    public String toString() {
        return super.toString() +
                " Tenant{" +
                "birthdate= " + birthDate.format(formatter) +
                ", ssn= " + ssn + '\'' +
                ", phone= " + phone + '\'' +
                ", employer= " + employer + '\'' +
                ", occupation= " + occupation + '\'' +
                ", grossPay= " + grossPay +
                ", emplomentStartDate= " + employmentStartDate.format(formatter) +
                "}";

    }

    public String samp(){
        return this.firstName + " works for " + this.employer;
    }

    public double calcGrossPay(){
        return this.grossPay;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
    }

    public LocalDateTime getEmploymentStartDate() {
        return employmentStartDate;
    }

    public void setEmploymentStartDate(LocalDateTime employmentStartDate) {
        this.employmentStartDate = employmentStartDate;
    }



}
