package Controllers;

import java.io.IOException;

import data.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Event;
import models.Room;
import users.Attendee;


public class AdminController {

    @FXML
    private Button LogoutBtn;


    // Attendee TableView and Columns
    @FXML
    private TableView<Attendee> AttTableView;
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

    // Room TableView and Columns
    @FXML
    private TableView<Room> RoomTableView;
    @FXML
    private TableColumn<Room, Integer> roomIDColumn;
    @FXML
    private TableColumn<Room, String> roomNumberColumn;
    @FXML
    private TableColumn<Room, String> availableHoursColumn;

    // Event TableView and Columns
    @FXML
    private TableView<Event> EventTableView;
    @FXML
    private TableColumn<Event, String> eventNameColumn;
    @FXML
    private TableColumn<Event, String> eventRoomColumn;
    @FXML
    private TableColumn<Event, String> eventCategoryColumn;
    @FXML
    private TableColumn<Event, Double> eventPriceColumn;

    @FXML
    private void initialize() {
        // Initialize Attendee Table Columns
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        interestsColumn.setCellValueFactory(new PropertyValueFactory<>("interests"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        walletBalanceColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        ObservableList<Attendee> attendees = FXCollections.observableArrayList(Database.attendees);
        AttTableView.setItems(attendees);
        

        // Initialize Room Table Columns
        roomIDColumn.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        roomNumberColumn.setCellValueFactory(new PropertyValueFactory<>("RoomNum"));
        availableHoursColumn.setCellValueFactory(new PropertyValueFactory<>("AvailableHrs"));
        ObservableList<Room> rooms = FXCollections.observableArrayList(Database.rooms);
        RoomTableView.setItems(rooms);

        // Initialize Event Table Columns
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        eventRoomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        eventCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        eventPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        ObservableList<Event> events = FXCollections.observableArrayList(Database.events);
        EventTableView.setItems(events);
    }


    @FXML
    private void logoutBtnOnAction(ActionEvent action){
         // close current scene
        Stage stage = (Stage) ((javafx.scene.Node) action.getSource()).getScene().getWindow();
        stage.close();

        // go back
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); //
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Log in Menu");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
