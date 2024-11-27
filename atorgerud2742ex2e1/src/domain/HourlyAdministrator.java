package domain;

import exceptions.TimeCardIllegalArgumentException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class HourlyAdministrator extends Administrator implements JSONStringifiable {

    double hourlyRate;
    ArrayList<TimeCard> timeCards = new ArrayList<TimeCard>();

    public HourlyAdministrator(int personId, String firstName, String lastName, String username, LocalDateTime birthDate, String ssn, String phone, LocalDateTime employmentStartDate, double hourlyRate) {
        super(personId, firstName, lastName, username, birthDate, ssn, phone, employmentStartDate);
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(LocalDateTime startDate, LocalDateTime endDate) {
        try {
            this.timeCards.add(new TimeCard(startDate, endDate));
        } catch (TimeCardIllegalArgumentException e) {
            throw new TimeCardIllegalArgumentException("Invalid start/end in TimeCard for personId=" + getPersonId() + ", " + e.getMessage());
        }

    }

    public void addTimeCard(TimeCard timeCard) {
        try {
            this.timeCards.add(timeCard);
        } catch (TimeCardIllegalArgumentException e) {
            throw new TimeCardIllegalArgumentException("Invalid start/end in TimeCard for personId=" + getPersonId() + ", " + e.getMessage());
        }

    }

    public void removeTimeCard(int index) {
        try {
            this.timeCards.remove(index);
        } catch (IndexOutOfBoundsException e) {

        }
    }

    public TimeCard removeAndGetTimeCard(int index) {
        try {
            TimeCard timeCard = this.timeCards.remove(index);
            return this.timeCards.get(index);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }

    }

    public ArrayList<TimeCard> getTimeCards() {
        return this.timeCards;
    }

    public TimeCard getTimeCard(int index){
        return this.timeCards.get(index);
    }

    public double getTotalHours(){
        int totalHours = 0;
        for (TimeCard timeCard : this.timeCards) {
            totalHours += timeCard.calcHours();
        }
        return (double)totalHours;
    }

    public double calcGrossPay(){
        return this.hourlyRate * getTotalHours();
    }

    public double getHourlyRate(){
        return this.hourlyRate;
    }

    public String JSONStringify(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        StringBuilder builder = new StringBuilder();
        builder.append("  {\n" +
                "    \"subclass\": \"" + getClass() +  "\",\n" +
                "    \"personId\":" + getPersonId() + ",\n" +
                "    \"lastName\":\"" + getLastName() + "\",\n" +
                "    \"firstName\":\"" + getFirstName() + "\",\n" +
                "    \"userName\":\"" + getUserName() + "\",\n" +
                "    \"birthDate\":\"" + formatter.format(getBirthDate()) + "\",\n" +
                "    \"ssn\":\"" + getSsn() + "\",\n" +
                "    \"phone\":\" " + getPhone() +" \",\n" +
                "    \"employmentStartDate\":\" " + formatter.format(getEmploymentStartDate()) + " \",\n" +
                "    \"hourlyRate\": " + getHourlyRate() + ",\n" +
                "    \"timeCards\":[\n");

                for (TimeCard timeCard : this.timeCards) {
                    builder.append(timeCard.JSONStringify());
                }

                builder.append(
                "    ]\n" +
                "  }");
        return builder.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HourlyAdministrator)) return false;
        HourlyAdministrator hourlyAdmin = (HourlyAdministrator) o;
        return Double.compare(hourlyAdmin.hourlyRate, hourlyRate) == 0 &&
                Objects.equals(hourlyAdmin.getPersonId(), ((HourlyAdministrator) o).getPersonId());
    }
}
