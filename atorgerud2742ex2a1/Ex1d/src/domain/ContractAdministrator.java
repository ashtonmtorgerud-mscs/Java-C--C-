package domain;

import java.time.LocalDateTime;

public class ContractAdministrator extends Administrator {

    double monthlyRate;

    public ContractAdministrator(int personId, String firstName, String lastName, String username, LocalDateTime birthDate, String ssn, String phone, LocalDateTime employmentStartDate, double monthlyRate) {
        super(personId, firstName, lastName, username, birthDate, ssn, phone, employmentStartDate);
        this.monthlyRate = monthlyRate;
    }


    @Override
    public String toString() {
        return super.toString() + " ContractAdministrator{ monthlyRate= " + monthlyRate;
    }

    private Double getMonthlyRate(){ return this.monthlyRate; }
    private void setMonthlyRate(double monthlyRate) { this.monthlyRate = monthlyRate; }


    public double calcGrossPay(){
        return this.getMonthlyRate();
    }

}
