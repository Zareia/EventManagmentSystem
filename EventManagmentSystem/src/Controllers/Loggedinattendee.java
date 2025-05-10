package Controllers;

import data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Event;
import models.Wallet;
import users.Attendee;

import java.io.IOException;

public class Loggedinattendee {
    @FXML
    private TableView<Event> AttendeeCol;
    @FXML
    private javafx.scene.control.Label msglabel;

    @FXML
    private TableColumn<Event, String> EventCol;

    @FXML
    private TableColumn<Event, String> IDCol;
    @FXML
    private javafx.scene.control.TextField IDField;
    @FXML
    private TableColumn<Event, String> DateCol;

    @FXML
    private TableColumn<Event, Double> PriceCol;

    @FXML
    public void initialize() {
        // Link columns with Event class getters
        EventCol.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        IDCol.setCellValueFactory(new PropertyValueFactory<>("eventID"));
        DateCol.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        PriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        // Populate the table
        ObservableList<Event> eventList = FXCollections.observableArrayList(Database.events);
        AttendeeCol.setItems(eventList);
    }

    @FXML
    public void goBack(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Log in Menu");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void BuyTIcket(ActionEvent actionEvent) {
        String eventid = IDField.getText();
        if (eventid != null) {
            System.out.println("Event ID is entered");
        }
        Event event = Database.events.stream()
                .filter(e -> e.getEventID().equals(eventid))
                .findFirst()
                .orElse(null);
        if (event != null) {
            System.out.println("Event: " + event.getEventName());
            System.out.println("Ticket Price: " + event.getPrice());

        }
        Attendee attendee = null;
        for (Attendee a : Database.attendees) {
            if (a.getUsername().equals(Database.currentUser.getUsername())) {
                attendee = a;
                break;
            }
        }
        if (attendee != null) {
            Wallet wallet = attendee.getWallet();
            if (wallet.withdraw(event.getPrice())) {
                System.out.println("Ticket purchased successfully for event: " + event.getEventName());
                System.out.println("Remaining Balance: " + wallet.getBalance());
                msglabel.setText("Ticket purchased successfully! Remaining balance: " + wallet.getBalance() + " egp");
            } else {
                System.out.println("Insufficient balance. Please add funds to your wallet.");
                msglabel.setText("Insufficient balance. Please add funds to your wallet!");
            }

        }
    }
}