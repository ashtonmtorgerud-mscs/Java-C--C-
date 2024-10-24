package domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeCard {
    private int timeCardId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimeCard(LocalDateTime startTime, LocalDateTime endTime) {
        this.startDateTime = startTime;
        this.endDateTime = endTime;
        this.timeCardId = DbContext.getTimeCardID();
    }

    public TimeCard(int timeCardId, LocalDateTime startTime, LocalDateTime endTime) {
        this.startDateTime = startTime;
        this.endDateTime = endTime;
        this.timeCardId = timeCardId;
    }

    public TimeCard(TimeCard timeCard) {
    }

    public TimeCard copy() { return new TimeCard(this.timeCardId, this.startDateTime, this.endDateTime); }

    public double calcHours(){
//        System.out.println(this.startDateTime.until(this.endDateTime, ChronoUnit.MINUTES) / 60.0);
        return this.startDateTime.until(this.endDateTime, ChronoUnit.MINUTES) / 60.0;
//        return  0.0;
    }




    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mma");
        return "Id= " + timeCardId +
                ", startDateTime= " + startDateTime.format(formatter) +
                ", endDateTime= " + endDateTime.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeCard)) return false;
        TimeCard timeCard = (TimeCard) o;
        return timeCardId == timeCard.timeCardId && startDateTime.equals(timeCard.startDateTime) && endDateTime.equals(timeCard.endDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeCardId, startDateTime, endDateTime);
    }

}
