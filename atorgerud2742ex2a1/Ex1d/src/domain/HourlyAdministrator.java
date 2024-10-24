package domain;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class HourlyAdministrator extends Administrator {

    double hourlyRate;
    ArrayList<TimeCard> timeCards = new ArrayList<TimeCard>();

    public HourlyAdministrator(int personId, String firstName, String lastName, String username, LocalDateTime birthDate, String ssn, String phone, LocalDateTime employmentStartDate, double hourlyRate) {
        super(personId, firstName, lastName, username, birthDate, ssn, phone, employmentStartDate);
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(LocalDateTime startDate, LocalDateTime endDate) {
        this.timeCards.add(new TimeCard(startDate, endDate));
    }

    public void addTimeCard(TimeCard timeCard) {
        this.timeCards.add(timeCard);
    }

    public void removeTimeCard(int index) {
        this.timeCards.remove(index);
    }

    public TimeCard removeAndGetTimeCard(int index) {
        TimeCard timeCard = this.timeCards.remove(index);
        return this.timeCards.get(index);
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HourlyAdministrator)) return false;
        HourlyAdministrator hourlyAdmin = (HourlyAdministrator) o;
        return Double.compare(hourlyAdmin.hourlyRate, hourlyRate) == 0 &&
                Objects.equals(hourlyAdmin.getPersonId(), ((HourlyAdministrator) o).getPersonId());
    }
}
