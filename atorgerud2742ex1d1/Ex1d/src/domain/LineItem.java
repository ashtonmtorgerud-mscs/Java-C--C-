package domain;

import java.util.Objects;


public class LineItem {
    private int lineItemId;
    private double amount;
    private String description;


    public LineItem(double amount, String description) {
        this.lineItemId = DbContext.getLineItemID();
        this.amount = amount;
        this.description = description;
    }

    public LineItem copy() {
        LineItem lineItem = new LineItem(this);
        lineItem.lineItemId = this.lineItemId;
        return lineItem;
    }


    public int getLineItemId() {
        return this.lineItemId;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public LineItem(LineItem lineItem) {
        this.lineItemId = lineItem.getLineItemId();
        this.amount = lineItem.getAmount();
        this.description = lineItem.getDescription();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineitem = (LineItem) o;
        return getLineItemId() == lineitem.getLineItemId() &&
            Double.compare(lineitem.getAmount(), getAmount()) == 0 &&
            Objects.equals(getDescription(), lineitem.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLineItemId(), getAmount(), getDescription());
    }

    public String ToString(){
        String returnString = "";
        returnString += "LineItemId: " + this.lineItemId + "\n";
        returnString += "Amount: " + this.amount + "\n";
        returnString += "Description: " + this.description + "\n";
        return returnString;
    }


    public String TwoString(){
        String returnString = "";
        returnString += "LineItem{ ";
        returnString += "lineItemId = " + this.lineItemId + ", ";
        returnString += "amount = " + this.amount + ", ";
        returnString += "description = '" + this.description;
        returnString += "' }";
        return returnString;
    }
}