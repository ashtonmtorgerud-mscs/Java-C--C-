package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class DbContext {

    private static int lineItemID = 10001;
    private static int invoiceID = 10001;
    private static Boolean scaleID = false;

    public static int getLineItemID() {
        if (scaleID != true){
//            return lineItemID;
            return lineItemID;
        }
        return lineItemID++;
    }

    public static int getInvoiceID() {
        if (scaleID = false){
            return invoiceID;
        }
        return invoiceID++;
    }

    public static void flipIDScaling()
    {
        scaleID = !scaleID;
    }

    public static List<Invoice> getInvoices(){

        List<Invoice> invoices = new ArrayList<>();
        GDate date1 = new GDate(2019, 9, 1);
        GDate date2 = new GDate(2019, 9, 11);

        Invoice invoice1 = new Invoice(1, date1, date2);

        invoice1.addLineItem(new LineItem(1.0, "description1") );
        invoice1.addLineItem(new LineItem(2.0, "description2") );
        invoice1.addLineItem(new LineItem(3.0, "description3") );
        invoice1.addLineItem(new LineItem(4.0, "description4") );
//        this.invoices = new List<Invoice>( );
        invoices.add(invoice1.copy());

        Invoice invoice2 = new Invoice(2, date1, date2);
        List<LineItem> lineItems = invoice1.getLineItems();
        for (LineItem lineItem : lineItems){
            invoice2.addLineItem(lineItem);
        }
        invoices.add(invoice2.copy());

        return invoices;
    }
}
