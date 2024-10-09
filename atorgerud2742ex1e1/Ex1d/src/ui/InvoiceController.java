package ui;

import domain.Invoice;
import domain.LineItem;
import domain.DbContext;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class InvoiceController {


    public TextField InvoiceIdTextField;
    public TextField statusTextField;
    public TextField invoiceDateTextField;
    public TextField dueDateTextField;
    public ComboBox<Invoice> invoicesComboBox;
    public ArrayList<Invoice> invoices = new ArrayList<>();
    public ListView<LineItem> lineItemsListView;
    public TextField lineItemDescriptionTextField;
    public TextField lineItemAmountTextField;
    public TextField totalAmountTextField;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void initData(ArrayList<Invoice> invoices) { this.invoices = invoices; }


    public InvoiceController(){

        this.invoices = (ArrayList<Invoice>) DbContext.getInvoices();

    }

    public void initialize(){
        for (Invoice invoice : this.invoices){
                invoicesComboBox.getItems().add(invoice);

        }
        invoicesComboBox.getSelectionModel().selectFirst();
        this.invoices = null;

        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();
        this.InvoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
        this.statusTextField.setText(invoice.getStatus().toString());
        this.invoiceDateTextField.setText(invoice.getInvoiceDate().format(formatter));
        this.dueDateTextField.setText(invoice.getDueDate().format(formatter));
        displayLineItems();
    }

    public void invoiceComboBoxItemSelected(ActionEvent actionEvent) {
        int index = this.invoicesComboBox.getSelectionModel().getSelectedIndex();
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();
        this.InvoiceIdTextField.setText(Integer.toString(invoice.getInvoiceId()));
        this.statusTextField.setText(invoice.getStatus().toString());
        this.invoiceDateTextField.setText(invoice.getInvoiceDate().format(formatter));
        this.dueDateTextField.setText(invoice.getDueDate().format(formatter));
        displayLineItems();
    }

    public void lineItemsListviewClicked(MouseEvent mouseEvent) {
        displayLineItem();
    }

    public void displayLineItems(){
        Invoice selectedInvoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();
        List<LineItem> lineItems = selectedInvoice.getLineItems();
        lineItemsListView.getItems().clear();
        lineItemsListView.getSelectionModel().clearSelection();
        double totalAmount = 0;
        for (LineItem lineItem : lineItems){
            totalAmount += lineItem.getAmount();
            lineItemsListView.getItems().add(lineItem);
        }

        totalAmountTextField.setText(String.valueOf(totalAmount));
    }



    public void displayLineItem(){
        int index = this.lineItemsListView.getSelectionModel().getSelectedIndex();

        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();
        int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();
        List<LineItem> lineItems = invoice.getLineItems();
        lineItemDescriptionTextField.setText(lineItems.get(lineItemIndex).getDescription());
        lineItemAmountTextField.setText(Double.toString(lineItems.get(lineItemIndex).getAmount()));
    }

    public void deleteButtonClicked(MouseEvent mouseEvent) {
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();

        int lineItemIndex = this.lineItemsListView.getSelectionModel().getSelectedIndex();

        if (lineItemIndex >= 0 && lineItemIndex < invoice.getLineItems().size()){
            invoice.removeLineItem(lineItemIndex);
            displayLineItems();
        }

    }


    public void saveEditsButtonClicked(MouseEvent mouseEvent) {
        Invoice invoice = this.invoicesComboBox.getSelectionModel().getSelectedItem();
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
        this.invoicesComboBox.getSelectionModel().getSelectedItem().addLineItem(lineItem);
        displayLineItems();
    }

}