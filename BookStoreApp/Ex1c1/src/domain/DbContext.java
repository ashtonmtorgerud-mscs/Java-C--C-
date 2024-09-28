package domain;

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

}
