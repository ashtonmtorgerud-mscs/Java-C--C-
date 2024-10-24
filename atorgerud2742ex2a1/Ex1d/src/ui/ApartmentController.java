package ui;
import domain.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.io.IOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ApartmentController {

    public ComboBox<Apartment> apartmentComboBox;
    public TextField apartmentNumberTextField;
    public TextField squareFeetTextField;
    public TextField bathroomsTextField;
    public TextField priceTextField;
    public TextField updatedTextField;
    public ComboBox<Person> administratorComboBox;
    public ComboBox<Person> tenantComboBox;
    DbContext dbContext = new DbContext();
    List<Apartment> apartments = new ArrayList<>();
    List<Person> people = new ArrayList<>();

    public void initialize(){
        this.apartmentComboBox.getItems().clear();
        apartments = DbContext.getApartments();
        this.people = DbContext.getPeople();
        getApartments();
        apartmentComboBox.getSelectionModel().select(0);
        getApartment();
    }

    public void getApartments(){

        for (Apartment apartment : apartments){
            this.apartmentComboBox.getItems().add(apartment);
        }
        for (Person person : people){
            System.out.println(person.getFullNameID());
            this.administratorComboBox.getItems().add(person);
            this.tenantComboBox.getItems().add(person);
        }


    }

    public void getApartment(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//        apartmentComboBox.getItems().clear();
        int apartmentIndex = apartmentComboBox.getSelectionModel().getSelectedIndex();
        if (apartmentIndex >= 0 && apartmentIndex < apartmentComboBox.getItems().size()){
            Apartment selectedApartment = this.apartmentComboBox.getSelectionModel().getSelectedItem();
//            administratorComboBox.getItems().clear();
//            tenantComboBox.getItems().clear();
            apartmentNumberTextField.setText(String.valueOf(selectedApartment.getApartmentNum()));
            squareFeetTextField.setText(String.valueOf(selectedApartment.getSquareFeet()));
            bathroomsTextField.setText(String.valueOf(selectedApartment.getBathrooms()));
            priceTextField.setText(String.valueOf(selectedApartment.getPrice()));
            updatedTextField.setText(String.valueOf(selectedApartment.getUpdated()));
            updatedTextField.setText(String.valueOf(selectedApartment.getUpdated().format(formatter)));
        }

        int selectedPersonIndex;
        Apartment selectedApartment = this.apartments.get(apartmentIndex);
        for (int i = 0; i < this.administratorComboBox.getItems().size(); i++){
            Person person = this.administratorComboBox.getItems().get(i);
            if (person.equals(selectedApartment.getAdministrator())){
                selectedPersonIndex = i;
                administratorComboBox.getSelectionModel().select(i);
            }
            if (person.equals(selectedApartment.getTenant())){
                selectedPersonIndex = i;
                tenantComboBox.getSelectionModel().select(i);
            }
        }


//        apartmentNumberTextField.setText(String.valueOf(apartmentIndex));
//        tenantComboBox.getItems().clear();

    }

    public void apartmentComboBoxAction(){

    }

    public void apartmentComboBoxAction(ActionEvent actionEvent){
        getApartment();
    }

    public void SaveApartment(){
        int apartmentIndex = apartmentComboBox.getSelectionModel().getSelectedIndex();
//        int aptID = Integer.parseInt(apartmentComboBox.getSelectionModel().toString().substring(11));
        String apartmentNumber = apartmentNumberTextField.getText();
        int squareFeet = Integer.parseInt(squareFeetTextField.getText());
        int bathrooms = Integer.parseInt(bathroomsTextField.getText());
        String price = priceTextField.getText();
        LocalDateTime updated = LocalDateTime.now();
        this.apartmentComboBox.getSelectionModel().getSelectedItem().setApartmentNum(apartmentNumber);
        this.apartmentComboBox.getSelectionModel().getSelectedItem().setSquareFeet(squareFeet);
        this.apartmentComboBox.getSelectionModel().getSelectedItem().setBathrooms(bathrooms);
        this.apartmentComboBox.getSelectionModel().getSelectedItem().setPrice(Double.valueOf(price));
        this.apartmentComboBox.getSelectionModel().getSelectedItem().setUpdated(LocalDateTime.now());
        this.apartmentComboBox.getSelectionModel().getSelectedItem().setAdministrator(this.administratorComboBox.getSelectionModel().getSelectedItem());
        this.apartmentComboBox.getSelectionModel().getSelectedItem().setTenant(this.tenantComboBox.getSelectionModel().getSelectedItem());
//        getApartments();
        apartmentComboBox.getSelectionModel().select(apartmentIndex);
        getApartment();
    }

    public void GetInvoices(){
        String[] args = DbContext.theArgs;

        try {
            DbContext.selectedApartment = apartments.get(apartmentComboBox.getSelectionModel().getSelectedIndex());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("invoice.fxml"));
            Parent root = FXMLLoader.load(getClass().getResource("InvoiceView.fxml"));
            Scene scene = new Scene(root);
            Stage primaryStage = new Stage();
            primaryStage.setScene(scene);
            primaryStage.setTitle("ATorgerud2742 Ex1D Invoices");
            primaryStage.show();
        } catch (IOException e) {

        }
    }
}
