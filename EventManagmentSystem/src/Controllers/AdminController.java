package Controllers;

import data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import users.Attendee;

public class AdminController {

    @FXML
    private Label wlcmLabel;
    @FXML
    private Button viewAllAttendeBtn;
    @FXML
    private Button viewAllRoomsBtn;
    @FXML
    private Button viewAllEventsBtn;

    @FXML
    private TableView<Attendee> attendeeTableView;
    @FXML
    private TableColumn<Attendee, String> usernameColumn;
    @FXML
    private TableColumn<Attendee, String> genderColumn;
    @FXML
    private TableColumn<Attendee, String> interestsColumn;
    @FXML
    private TableColumn<Attendee, String> addressColumn;
    @FXML
    private TableColumn<Attendee, Double> walletBalanceColumn;

    @FXML
    private void initialize() {
        // Initialize table columns
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        interestsColumn.setCellValueFactory(new PropertyValueFactory<>("interests"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        walletBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("walletBalance"));
    }

    @FXML
    private void viewAllAttendeBtnOnAction() {
        // Clear the table before displaying new data
        attendeeTableView.getItems().clear();

        // Fetch attendees from the database
        ObservableList<Attendee> attendees = FXCollections.observableArrayList(Database.attendees);

        // Display attendees in the table
        attendeeTableView.setItems(attendees);
    }
}
