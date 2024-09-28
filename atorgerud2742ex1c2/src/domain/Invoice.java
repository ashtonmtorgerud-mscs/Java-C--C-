package domain;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private int invoiceId;
    private int status;
    private GDate invoiceDate;
    private GDate dueDate;
//    private LineItem[] lineItems;
    DbContext dbContext;
    private ArrayList<LineItem> lineItems = new ArrayList<>();

    public Invoice(int status, GDate invoiceDate, GDate dueDate) {
        this.invoiceId = dbContext.getInvoiceID();
        this.status = status;
        this.invoiceDate = new GDate(invoiceDate);
        this.dueDate = new GDate(dueDate);

    }

    public Invoice(Invoice invoice) {
        this.invoiceId = invoice.invoiceId;
        this.status = invoice.status;
        this.invoiceDate = invoice.invoiceDate;
        this.invoiceDate = new GDate(invoice.invoiceDate);
        this.dueDate = new GDate(invoice.dueDate);
    }


    public int getInvoiceId() {
        return this.invoiceId;
    }

    public String getStatus() {
        return this.status + "";
    }

    public GDate getInvoiceDate() {
        return this.invoiceDate;
    }

    public GDate getDueDate() {
        return this.dueDate;
    }

//    public LineItem[] getLineItems() {
//        return this.lineItems.toArray(new LineItem[0]);
//    }
    public List<LineItem> getLineItems() {
        List<LineItem> lineItems = new ArrayList<>();
        lineItems.addAll(this.lineItems);
        return lineItems;
    }

    public Invoice copy() {
        Invoice invoice = new Invoice(this.status, this.invoiceDate, this.dueDate);
        invoice.invoiceId = this.invoiceId;
        invoice.lineItems = this.lineItems;
        return invoice;
//        throw new UnsupportedOperationException();
    }

    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    public void addLineItem(double cost, String description) {
        LineItem lineItem = new LineItem(cost, description);
        this.lineItems.add(lineItem);
    }

    public LineItem removeLineItem(int index) {
        LineItem lineitem = null;
        if (index >= 0 && index < this.lineItems.size()) {
            lineitem = this.lineItems.get(index).copy();
            this.lineItems.remove(index);
        }
        return lineitem;
    }

    public LineItem removeLineItem(LineItem deletedlineItem) {

        LineItem removedLineItem = null;
        if (this.lineItems.contains(deletedlineItem)){removedLineItem = deletedlineItem.copy(); this.lineItems.remove(deletedlineItem);}

        return removedLineItem;
    }


    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceID=" + invoiceId +
                ", status=" + status +
                ", invoiceDate=" + invoiceDate +
                '}';
    }

    public String toShortString() {
        return "[" + invoiceId + "] " + status +
                ", " + invoiceDate +
                ' ';
    }

    public String lineItemsToString() {
        String output = "";
        for (LineItem lineItems: lineItems){
            output += lineItems.ToString();
        }
        return output;
    }


    public double total() {
        double total = 0.0;
        for (LineItem lineItem : lineItems){
            total += lineItem.getAmount();
        }
        return total;
    }
}