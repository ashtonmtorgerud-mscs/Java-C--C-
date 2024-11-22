package domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HourlyAdministratorTest {
    HourlyAdministrator hourlyAdmin;

    @BeforeEach
    void setUp() {
        this.hourlyAdmin = new HourlyAdministrator(
                1001, "John", "Smith", "JSmith", LocalDateTime.of(2000,5,4, 1, 1),
                "1111111", "1-555-555-5555", LocalDateTime.of(2020, 8, 2, 12, 1), 20.0);

        this.hourlyAdmin.addTimeCard( new TimeCard(
                        LocalDateTime.of(2018, 10, 23, 8, 0),
                        LocalDateTime.of(2018, 10, 23, 18, 0)));
        TimeCard testCard = this.hourlyAdmin.getTimeCard(0);


    }

    @Test
    void AddTimeCard(){
//        Trying to get from an invalid index will crash the program. Unless it's meaning to use a Try Catch? is there something I'm missing?
        this.hourlyAdmin.addTimeCard(new TimeCard(LocalDateTime.of(2018, 10, 23, 8, 30),
                LocalDateTime.of(2018, 10, 23, 18, 0)));
        String timeCardString = this.hourlyAdmin.getTimeCard(0).toString();
//        System.out.println(timeCardString);
        int splitterLine = this.hourlyAdmin.getTimeCard(0).toString().indexOf("startDateTime");
        assertEquals(this.hourlyAdmin.getTimeCard(0).toString().substring(splitterLine), "startDateTime= 2018/10/23 08:00AM, endDateTime= 2018/10/23 18:00PM");
    }

    @Test
    void getTimeCard(){
//        TimeCard timeCard = this.hourlyAdmin.getTimeCard(1);
//        assertNull(timeCard);
//        Trying the above two just crashes the program, it doesn't return a value of null

        this.hourlyAdmin.addTimeCard(new TimeCard(LocalDateTime.of(2018, 10, 23, 8, 30),
                LocalDateTime.of(2018, 10, 23, 18, 0)));
        String timeCardString = this.hourlyAdmin.getTimeCard(0).toString();
        int splitterLine = this.hourlyAdmin.removeAndGetTimeCard(0).toString().indexOf("startDateTime");
//        assertEquals(this.hourlyAdmin.getTimeCard(0).toString().substring(splitterLine), "startDateTime= 2018/10/23 08:30AM, endDateTime= 2018/10/23 18:00PM");
        ArrayList<TimeCard> cards = this.hourlyAdmin.getTimeCards();
        assertEquals(cards.size(), 1);
    }

    @Test
    void deleteTimeCard(){
        //If it's okay, I want to attempt to do this one in a different way
        int initialSize = this.hourlyAdmin.timeCards.size();
        this.hourlyAdmin.removeTimeCard(0);
        assertNotEquals(initialSize, this.hourlyAdmin.timeCards.size());
    }

    @Test
    void getTimeCards(){
        ArrayList<TimeCard> cards = this.hourlyAdmin.getTimeCards();
        assertEquals(cards.size(), this.hourlyAdmin.timeCards.size());
    }

    void toStringTest(){
        String strungifiededItWas = "Id= 10001, startDateTime= 2018/10/23 08:30AM, endDateTime= 2018/10/23 18:00PM";
        assertEquals(this.hourlyAdmin.toString(), strungifiededItWas);
    }

    @Test
    void calcTotalHours(){
        TimeCard newCard = new TimeCard(
                LocalDateTime.of(2018, 10, 23, 8, 0),
                LocalDateTime.of(2018, 10, 23, 18, 0));
        this.hourlyAdmin.addTimeCard(newCard);
        this.hourlyAdmin.addTimeCard(newCard);
        assertEquals(this.hourlyAdmin.getTotalHours(), 30);
    }


    @Test
    void calcGrossPay(){
        TimeCard newCard = new TimeCard(
                LocalDateTime.of(2018, 10, 23, 8, 0),
                LocalDateTime.of(2018, 10, 23, 18, 0));
        this.hourlyAdmin.addTimeCard(newCard);
        this.hourlyAdmin.addTimeCard(newCard);
        assertEquals(this.hourlyAdmin.calcGrossPay(), 600);
    }



}