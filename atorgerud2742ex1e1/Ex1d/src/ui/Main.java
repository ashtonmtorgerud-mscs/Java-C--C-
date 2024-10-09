package ui;

import domain.*;
import java.util.ArrayList;
import java.util.List;

public class Main {



    public static void main(String[] args) {

        DbContext.theArgs = args;

        DbContext context = new DbContext();
        context.flipIDScaling();
        ApartmentApplication appartmentApplication = new ApartmentApplication();
        appartmentApplication.main(args);


        ArrayList<Apartment> apartments = new ArrayList<Apartment>();
        for (Apartment apartment : apartments) {
            System.out.println(apartment.toString());
            ArrayList<Invoice> invoices = new ArrayList(DbContext.getInvoices());
            System.out.println("\tInvoices: ");
            for (Invoice invoice : invoices){
                System.out.println(invoice.toString());
            }

        }




    }
}