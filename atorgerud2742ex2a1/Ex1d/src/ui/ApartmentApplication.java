package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

import static javafx.application.Application.launch;

public class ApartmentApplication extends Application {

    public static void main(String[] args) { launch(args); }



//    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ApartmentView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ATorgerud2742 Ex1D Apartment");
        primaryStage.show();
    }

    public void startInvoices(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("InvoiceView.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ATorgerud2742 Ex1D Invoice");
        primaryStage.show();
    }
}
