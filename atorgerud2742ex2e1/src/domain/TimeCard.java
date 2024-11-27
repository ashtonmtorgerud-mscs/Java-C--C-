package domain;

import exceptions.TimeCardIllegalArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeCard implements JSONStringifiable {
    private int timeCardId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimeCard(LocalDateTime startTime, LocalDateTime endTime) {
        this.timeCardId = DbContext.getTimeCardID();
        this.startDateTime = startTime;
        this.endDateTime = endTime;

        if (this.startDateTime.isEqual(this.endDateTime) || this.startDateTime.isAfter(this.endDateTime)) {
            throw new TimeCardIllegalArgumentException("Start date and end time must be greater than end date");
        }
        this.startDateTime = startTime;
        this.endDateTime = endTime;

    }

    public TimeCard(int timeCardId, LocalDateTime startTime, LocalDateTime endTime) {
        this.timeCardId = timeCardId;
        this.startDateTime = startTime;
        this.endDateTime = endTime;

        if (this.startDateTime.isEqual(this.endDateTime) || this.startDateTime.isAfter(this.endDateTime)) {
            throw new TimeCardIllegalArgumentException("Start date and end time must be greater than end date");
        }
        this.startDateTime = startTime;
        this.endDateTime = endTime;
    }

    public TimeCard(TimeCard timeCard) {
    }

    public TimeCard copy() { return new TimeCard(this.timeCardId, this.startDateTime, this.endDateTime); }

    public double calcHours(){
//        System.out.println(this.startDateTime.until(this.endDateTime, ChronoUnit.MINUTES) / 60.0);
        return this.startDateTime.until(this.endDateTime, ChronoUnit.MINUTES) / 60.00;
//        return  0.0;
    }

    public String JSONStringify(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");

        StringBuilder builder = new StringBuilder();
        builder.append("{\"id\":");
        builder.append(this.timeCardId);
        builder.append(",\"startDateTime\":\"");
        builder.append(formatter.format(this.startDateTime));
        builder.append("\",\"endDateTime\":\"");
        builder.append(formatter.format(this.endDateTime));
        builder.append("\"}");

        return builder.toString();
    }


    @Override
    public String toString() {
        String formatted = String.format("%.2f", calcHours());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");
        return "Id= " + timeCardId +
                ", startDateTime=" + startDateTime.format(formatter) +
                ", endDateTime=" + endDateTime.format(formatter) +
                ", hours=" + formatted;
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
