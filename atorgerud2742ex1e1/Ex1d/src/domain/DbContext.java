package domain;

import ui.ApartmentController;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DbContext {

    private static int lineItemID = 10001;
    private static int invoiceID = 10001;
    private static int apartmentID = 10001;
    private static Boolean scaleID = false;
    public static String[] theArgs = new String[0];
    public static Apartment selectedApartment = null;


    public static int getLineItemID() {
        if (!scaleID){
            return lineItemID;
        }
        return lineItemID++;
    }

    public static int getInvoiceID() {
        if (!scaleID){
            return invoiceID;
        }
        return invoiceID++;
    }

    public static int getApartmentID() {
        if (!scaleID){
            return apartmentID;
        }
        return apartmentID++;
    }

    public static void flipIDScaling()
    {
        scaleID = !scaleID;
    }


    public static List<Apartment> getApartments(){
        ArrayList <Person> people = DbContext.getPeople();
        List<Apartment> apartments = new ArrayList<>();
        Apartment apartment = new Apartment(101, "101", 1200, 1, 1000.01);
        apartment.setAdministrator(people.get(0));
        apartment.setTenant(people.get(1));
        apartments.add(apartment);
        apartment = new Apartment(102, "102", 1400, 2, 1100.00);
        apartment.setAdministrator(people.get(2));
        apartment.setTenant(people.get(3));
        apartments.add(apartment);
        return apartments;
    }



    public static List<Invoice> getInvoices(){

        List<Invoice> invoices = new ArrayList<>();
//        GDate date1 = new GDate(2019, 9, 1);
//        GDate date2 = new GDate(2019, 9, 11);
        LocalDateTime date1 = LocalDateTime.now();
        LocalDateTime date2 = LocalDateTime.now();


        Apartment apartment1 = DbContext.getApartments().get(0);
        Invoice invoice1 = new Invoice(1, date1, date2, apartment1);
        System.out.println("Invoice1 ID: " + invoice1.getInvoiceId());
        invoice1.addLineItem(new LineItem(1.0, "description1") );
        invoice1.addLineItem(new LineItem(2.0, "description2") );
        invoice1.addLineItem(new LineItem(3.0, "description3") );
        invoice1.addLineItem(new LineItem(4.0, "description4") );
//        this.invoices = new List<Invoice>( );
        System.out.println("Invoice1 ID: " + invoice1.getInvoiceId());
        invoices.add(invoice1);


        Apartment apartment2 = DbContext.getApartments().get(1);
        Invoice invoice2 = new Invoice(2, date1, date2, apartment2);
        List<LineItem> lineItems = invoice1.getLineItems();
        for (LineItem lineItem : lineItems){
            invoice2.addLineItem(lineItem);
        }


        invoices.add(invoice2);

        return invoices;
    }

    public static ArrayList<Person> getPeople(){
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person(100, "Yu", "Narukami", "Izanagi"));
        people.add(new Person(101, "Yosuke", "Hanamura", "Jiraiya"));
        people.add(new Person(107, "Chie", "Satonaka", "Tomoe"));
        people.add(new Person(103, "Yukiko", "Amagi", "KonohanaSakuya"));
        people.add(new Person(105, "Kanji", "Tatsumi", "Take-Mikazuchi"));
        return people;
    }



}
