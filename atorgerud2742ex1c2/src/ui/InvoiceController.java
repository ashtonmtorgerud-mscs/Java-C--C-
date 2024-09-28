package ui;

import domain.GDate;
import domain.Invoice;
import domain.LineItem;
import domain.DbContext;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.List;


public class InvoiceController {


    public TextField InvoiceIdTextField;
    public TextField statusTextField;
    public TextField invoiceDateTextField;
    public TextField dueDateTextField;
    public ComboBox invoicesComboBox;
    public ArrayList<Invoice> invoices = new ArrayList<>();
    public ListView lineItemsListView;
    public TextField lineItemDescriptionTextField;
    public TextField lineItemAmountTextField;
    public TextField totalAmountTextField;

    public InvoiceController(){

        this.invoices = (ArrayList<Invoice>) DbContext.getInvoices();




//        GDate date1 = new GDate(2019, 9, 1);
//        GDate date2 = new GDate(2019, 9, 11);
//
//        Invoice invoice1 = new Invoice(1, date1, date2);
//
//        invoice1.addLineItem(new LineItem(1.0, "description1") );
//        invoice1.addLineItem(new LineItem(2.0, "description2") );
//        invoice1.addLineItem(new LineItem(3.0, "description3") );
//        invoice1.addLineItem(new LineItem(4.0, "description4") );
////        this.invoices = new List<Invoice>( );
//        this.invoices.add(invoice1);
//
//        Invoice invoice2 = new Invoice(2, date1, date2);
//        List<LineItem> lineItems = invoice1.getLineItems();
//        for (LineItem lineItem : lineItems){
//            invoice2.addLineItem(lineItem);
//        }
//        invoices.add(invoice2);

    }

//    public void loadButtonClicked(ActionEvent actionEvent) {
//        Invoice invoice = this.invoices.get(0);
//        this.InvoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
//        this.statusTextField.setText(invoice.getStatus().toString());
//        this.invoiceDateTextField.setText(invoice.getInvoiceDate().toString());
//        this.dueDateTextField.setText(invoice.getDueDate().toString());
//    }
//
//    public void loadButtonTwoClicked(ActionEvent actionEvent) {
//        Invoice invoice = this.invoices.get(1);
//        this.InvoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
//        this.statusTextField.setText(invoice.getStatus().toString());
//        this.invoiceDateTextField.setText(invoice.getInvoiceDate().toString());
//        this.dueDateTextField.setText(invoice.getDueDate().toString());
//    }

    public void initialize(){
        for (Invoice invoice : this.invoices){
            invoicesComboBox.getItems().add(invoice);
        }
        invoicesComboBox.getSelectionModel().selectFirst();

        Invoice invoice = this.invoices.get(0);
        this.InvoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
        this.statusTextField.setText(invoice.getStatus().toString());
        this.invoiceDateTextField.setText(invoice.getInvoiceDate().toString());
        this.dueDateTextField.setText(invoice.getDueDate().toString());
        displayLineItems();
    }

    public void invoiceComboBoxItemSelected(ActionEvent actionEvent) {
        int index = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(index);
        this.InvoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
        this.statusTextField.setText(invoice.getStatus().toString());
        this.invoiceDateTextField.setText(invoice.getInvoiceDate().toString());
        this.dueDateTextField.setText(invoice.getDueDate().toString());
        displayLineItems();
    }

    public void lineItemsListviewClicked(MouseEvent mouseEvent) {
        displayLineItem();
    }

    public void displayLineItems(){
        int invoiceIndex = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(invoiceIndex);
        List<LineItem> lineItems = invoice.getLineItems();
        lineItemsListView.getItems().clear();
        lineItemsListView.getSelectionModel().clearSelection();
        double totalAmount = 0;
        for (LineItem lineItem : lineItems){
            totalAmount += lineItem.getAmount();
            lineItemsListView.getItems().add(lineItem.getDescription() + " " + lineItem.getAmount());
        }

        totalAmountTextField.setText(String.valueOf(totalAmount));
    }



    public void displayLineItem(){
        int index = this.lineItemsListView.getSelectionModel().getSelectedIndex();
        if (index >= 0 && index < this.invoices.size()){
            Invoice invoice = this.invoices.get(index);
        }
        int invoiceIndex = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(invoiceIndex);
        List<LineItem> lineItems = invoice.getLineItems();
        lineItemDescriptionTextField.setText(lineItems.get(lineItemIndex).getDescription());
        lineItemAmountTextField.setText(Double.toString(lineItems.get(lineItemIndex).getAmount()));
    }

    public void deleteButtonClicked(MouseEvent mouseEvent) {
        int invoiceIndex = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(invoiceIndex);

        int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();

        if (lineItemIndex >= 0 && lineItemIndex < invoice.getLineItems().size()){
            invoice.removeLineItem(lineItemIndex);
            displayLineItems();
        }

    }


    public void saveEditsButtonClicked(MouseEvent mouseEvent) {
        int invoiceIndex = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoices.get(invoiceIndex);
        List<LineItem> lineItems = invoice.getLineItems();
        int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();
        LineItem replacingItem = new LineItem(Double.parseDouble(lineItemAmountTextField.getText()), lineItemDescriptionTextField.getText());//Does something need to be done about the ID? I'm not sure how to create a new one while setting the ID, and copying doesn't allow us to alter it. If we're supposed to make a new method for it, I'll do that next week.
        int savedIndex = lineItems.indexOf(replacingItem);
        for (int i = 0; i < lineItems.size(); i++){
            invoice.removeLineItem(lineItems.get(i));
        }
        int j = 0;
        for (LineItem lineItem : lineItems){
            if (lineItemIndex == j){
                invoice.addLineItem(replacingItem);
            }else {
                invoice.addLineItem(lineItem);
            }
            j++;
        }
        displayLineItems();
    }

    public void AddButtonClicked(MouseEvent mouseEvent) {
        LineItem lineItem = new LineItem(Double.parseDouble(lineItemAmountTextField.getText()), lineItemDescriptionTextField.getText());
        int invoiceIndex = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        this.invoices.get(invoiceIndex).addLineItem(lineItem);
        displayLineItems();
    }
}