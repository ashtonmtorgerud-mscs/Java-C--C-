package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Invoice {
    private int invoiceId;
    private int status;
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
//    private LineItem[] lineItems;
    DbContext dbContext;
    private ArrayList<LineItem> lineItems = new ArrayList<>();
    public Apartment apartment = null;

    public Invoice(int status, LocalDateTime invoiceDate, LocalDateTime dueDate, Apartment apartment) {
        this.invoiceId = dbContext.getInvoiceID();
        this.status = status;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.apartment = apartment;

    }

    public Invoice(Invoice invoice) {
        this.invoiceId = invoice.invoiceId;
        this.status = invoice.status;
        this.invoiceDate = invoice.invoiceDate;
        this.invoiceDate = invoice.invoiceDate;
        this.dueDate = invoice.dueDate;
        this.apartment = invoice.apartment;
    }


    public int getInvoiceId() {
        return this.invoiceId;
    }

    public String getStatus() {
        return this.status + "";
    }

    public LocalDateTime getInvoiceDate() {
        return this.invoiceDate;
    }

    public LocalDateTime getDueDate() {
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
        Invoice invoice = new Invoice(this.status, this.invoiceDate, this.dueDate, null);
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return "Invoice{" +
                "invoiceID=" + invoiceId +
                ", status=" + status +
                ", invoiceDate=" + invoiceDate.format(formatter) +
                ", dueDate=" + dueDate.format(formatter) +
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceId() == invoice.getInvoiceId() && getStatus() == invoice.getStatus() && Objects.equals(getInvoiceDate(), invoice.getInvoiceDate()) && Objects.equals(getDueDate(), invoice.getDueDate()) && Objects.equals(dbContext, invoice.dbContext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getStatus(), getInvoiceDate(), getDueDate());
    }
}