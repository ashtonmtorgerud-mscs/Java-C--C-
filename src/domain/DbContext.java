package domain;

public class DbContext {

    private static int lineItemID = 1;
    private static int invoiceID = 1;


    public static int getLineItemID() {
        return lineItemID++;
    }

    public static int getInvoiceID() {
        return invoiceID++;
    }
}
