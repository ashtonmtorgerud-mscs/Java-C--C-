package dataaccess;

import domain.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PeopleJSONParser {
    private static String json = "{}";

    public static void readFile(String path) throws FileNotFoundException, IOException {
        try (BufferedReader in = new BufferedReader(
                new FileReader(path))) {
//                    String line = in.readLine();
//                    return line;
            String line = "";
            StringBuilder sbJSON = new StringBuilder(400);
            while ((line = in.readLine()) != null) {
                sbJSON.append(line + "\n");
            }
            json = sbJSON.toString();
        }
    }

    public static ArrayList<Person> getPeople(){
        ArrayList<Person> people = new ArrayList<Person>();
        JSONObject obj = new JSONObject(json);
        JSONArray jsonPeople = obj.getJSONArray("people");
        if (jsonPeople != null) {
            for (int i = 0; i < jsonPeople.length(); i++) {
                JSONObject jsonPerson = jsonPeople.getJSONObject(i);
                String subclass = jsonPerson.getString("subclass");
                switch (subclass) {
                    case "person": people.add(getPerson(jsonPerson)); break;
                    case "tenant": people.add(getTenant(jsonPerson)); break;
                    case "contractAdministrator": people.add(getContractAdministrator(jsonPerson)); break;
                    case "hourlyAdministrator": people.add(getHourlyAdministrator(jsonPerson)); break;

                }
            }
        }
        return people;
    }






    public static Person getPerson(JSONObject jsonPerson) {
//        JSONObject obj = new JSONObject(json).getJSONObject("person");
        JSONObject obj = jsonPerson;
        int personID = obj.getInt("personId");
        String lastName = obj.getString("lastName");
        String firstName = obj.getString("firstName");
        String userName = obj.getString("userName");

//        System.out.println(obj.toString());

        Person person = null;
        person = new Person(personID, lastName, firstName, userName);
        return person;
    }

    public static Tenant getTenant(JSONObject jsonPerson) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        JSONObject obj = new JSONObject(json).getJSONObject("tenant");
        JSONObject obj = jsonPerson;

        int personID = obj.getInt("personId");
        String lastName = obj.getString("lastName");
        String firstName = obj.getString("firstName");
        String userName = obj.getString("userName");
        String strBirthDate = obj.getString("birthDate");
        LocalDate birthDate = LocalDate.parse(strBirthDate, formatter);
        LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));
        String ssn = obj.getString("ssn");
        String phone = obj.getString("phone");
        String employer = obj.getString("employer");
        String occupation = obj.getString("occupation");
        long grossPay = obj.getLong("grossPay");
        String strEmploymentStartDate = obj.getString("employmentStartDate");
        LocalDate employmentStartDate = LocalDate.parse(strEmploymentStartDate, formatter);
        LocalDateTime employmentStartDateTime = LocalDateTime.of(employmentStartDate, LocalTime.of(0, 0));


//        LocalDateTime

        Tenant tenant = null;
        tenant = new Tenant(personID, lastName, firstName, userName, birthDateTime, ssn, phone, employer, occupation, grossPay, employmentStartDateTime);
        return tenant;
    }

    public static HourlyAdministrator getHourlyAdministrator(JSONObject jsonPerson) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mma");
//        JSONObject obj = new JSONObject(json).getJSONObject("hourlyAdministrator");
        JSONObject obj = jsonPerson;


        int personID = obj.getInt("personId");
        String lastName = obj.getString("lastName");
        String firstName = obj.getString("firstName");
        String userName = obj.getString("userName");
        String strBirthDate = obj.getString("birthDate");
        LocalDate birthDate = LocalDate.parse(strBirthDate, formatter);
        LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));
        String ssn = obj.getString("ssn");
        String phone = obj.getString("phone");
        String strEmploymentStartDate = obj.getString("employmentStartDate");
        LocalDate employmentStartDate = LocalDate.parse(strEmploymentStartDate, formatter);
        LocalDateTime employmentStartDateTime = LocalDateTime.of(employmentStartDate, LocalTime.of(0, 0));
        double hourlyRate = obj.getDouble("hourlyRate");

        HourlyAdministrator hourlyAdministrator = null;
        hourlyAdministrator = new HourlyAdministrator(personID, firstName, lastName, userName, birthDateTime, ssn, phone, employmentStartDateTime, hourlyRate);

        JSONArray jsonTimeCards = obj.getJSONArray("timeCards");
        if (jsonTimeCards != null){
            for (int i = 0; i < jsonTimeCards.length(); i++){
                JSONObject jsonTimeCard = jsonTimeCards.getJSONObject(i);
                int id = jsonTimeCard.getInt("id");
                LocalDateTime startDateTime = LocalDateTime.parse(jsonTimeCard.getString("startDateTime"), dtformatter);
                LocalDateTime endDateTime = LocalDateTime.parse(jsonTimeCard.getString("startDateTime"), dtformatter);
                hourlyAdministrator.addTimeCard(new TimeCard(id, startDateTime, endDateTime));
            }
        }


        return hourlyAdministrator;
    }


    public static ContractAdministrator getContractAdministrator(JSONObject jsonPerson) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dtformatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mma");
//        JSONObject obj = new JSONObject(json).getJSONObject("contractAdministrator");
        JSONObject obj = jsonPerson;


        int personID = obj.getInt("personId");
        String lastName = obj.getString("lastName");
        String firstName = obj.getString("firstName");
        String userName = obj.getString("userName");
        String strBirthDate = obj.getString("birthDate");
        LocalDate birthDate = LocalDate.parse(strBirthDate, formatter);
        LocalDateTime birthDateTime = LocalDateTime.of(birthDate, LocalTime.of(0, 0));
        String ssn = obj.getString("ssn");
        String phone = obj.getString("phone");
        String strEmploymentStartDate = obj.getString("employmentStartDate");
        LocalDate employmentStartDate = LocalDate.parse(strEmploymentStartDate, formatter);
        LocalDateTime employmentStartDateTime = LocalDateTime.of(employmentStartDate, LocalTime.of(0, 0));
        double monthlyRate = obj.getDouble("monthlyRate");

        ContractAdministrator contractAdministrator = null;
        contractAdministrator = new ContractAdministrator(personID, firstName, lastName, userName, birthDateTime, ssn, phone, employmentStartDateTime, monthlyRate);



        return contractAdministrator;
    }




}
