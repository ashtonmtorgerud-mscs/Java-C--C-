package ui;

import dataaccess.PeopleJSONParser;
import domain.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {


//    static String json = "{\n" +
//            "  \"person\": {\n" +
//            "    \"personId\": 101,\n" +
//            "    \"lastName\": \"Torgerud\",\n" +
//            "    \"firstName\": \"Ashton\",\n" +
//            "    \"userName\": \"Izanagi\"\n" +
//            "  }\n" +
//            "}";






    public static void main(String[] args) {

        ArrayList<Person> people = new ArrayList<Person>();

        try {
            PeopleJSONParser.readFile("atorgerud2742ex2d1/resources/people.json");

            people = PeopleJSONParser.getPeople();

        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e){
            System.out.println("I/O error occurred");
        }

        for (Person p : people) {
            System.out.println(p.toString());
            if (p.getClass() == HourlyAdministrator.class) {
                HourlyAdministrator hourlyAdministrator = (HourlyAdministrator) p;
                ArrayList<TimeCard> timeCards = hourlyAdministrator.getTimeCards();
                for (TimeCard timeCard : timeCards) {
                    System.out.println(timeCard.toString());
                }
            }
        }

        System.out.println("\n Exceptions: ");
        ArrayList<Exception> exceptions = PeopleJSONParser.getExceptions();
        for (Exception e : exceptions) {
            String msg = e.toString();
            if (e.getCause() != null) {msg += "\n\t" + e.getCause().toString();}
            System.out.println(msg);
        }

//        System.out.println("Person: " + person.toString());
    }
}


