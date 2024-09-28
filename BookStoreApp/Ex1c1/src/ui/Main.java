package ui;

import domain.DbContext;
import domain.GDate;
import domain.Invoice;

public class Main {
    public static void main(String[] args) {

        //Constructors
        GDate date1 = new GDate();
//        System.out.println("Gdate():\t\t\t\t\t" + date1);
        GDate date2 = new GDate(2000, 1,1);
//        System.out.println("Gdate(2000, 1, 1):\t\t\t" + date2);
//        GDate date3 = new GDate(date1);
//        System.out.println("GDate(date1):\t\t\t\t" + date3);
//        GDate date4 = new GDate(2451545);
//        System.out.println("GDate(2451545):\t\t\t\t" + date4);
//        System.out.println("GDate().copy():\t\t\t\t" + date1.copy());
//
//        //Comparisons
//        System.out.print(date1);
//        System.out.print(date1.equals(date3) ? " = " : " != ");
//        System.out.println(date3);
//        System.out.print(date1);
//        GDate date6 = new GDate(2000,1,2);
//        System.out.print(date1.equals(date6) ? " = " : " != ");
//
//        System.out.print(date1.copy());
//
//        //Greater Than
//        GDate date7 = new GDate().copy();
//        System.out.println(date7 + (date7.greaterThan(date1) ? " > " : " <= ") + date1 );
//        GDate date8 = new GDate(2000, 1, 2);
//        System.out.println(date8 + (date8.greaterThan(date1) ? " > " : " <= ") + date1 );
//        GDate date9 = new GDate(2000, 1, 2);
//        System.out.println(date8 + " - " + (date8.greaterThan(date1) ? " > " : " <= ") + date1 );
//
//        //Add
//        GDate date12 = new GDate(2001, 1, 1);
//        System.out.println(date12 + " - " + date1 + " = " + date12.diff(date1));
//        GDate date13 = new GDate(2002, 1, 1);
//        GDate date13Sub = new GDate(2001, 1, 1);
//        System.out.println(date13 + " - " + date13Sub + " = " + date13.diff(date13Sub));
//        GDate date10 = new GDate(2000, 1, 1);
//        System.out.println(date10 + " + 60 = " + date10.add(60));
//        GDate date11 = new GDate(2001, 1, 1);
//        System.out.println(date11 + " + 60 = " + date11.add(60));
//
//        //Day of
//        System.out.println("Year of " + date1 + " = " + date1.year());
//        System.out.println("Month of " + date1 + " = " + date1.month());
//        System.out.println("Day of " + date1 + " = " + date1.day());
//        System.out.println("Day of " + date1 + " = " + date1.day());
        DbContext.flipIDScaling();
        
        Invoice invoice1 = new Invoice(1001, date1, date2);
        invoice1.addLineItem(90000, "20 minutes of rent (I love the housing market)");
        System.out.println(invoice1.toString());
        System.out.println(invoice1.lineItemsToString());
        Invoice invoice2 = new Invoice(1002, date2, date1);
        Invoice invoice3 = new Invoice(invoice2);
        invoice3.addLineItem(70, "Persona 3 Reload");
        invoice3.addLineItem(60, "Shin Megami Tensei V Vengeance");
        System.out.println("");
        System.out.println(invoice3.toString());
        System.out.println(invoice3.lineItemsToString());
        System.out.println("Total games cost: " + invoice3.total());

    }
}