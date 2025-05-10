package Controllers;

import data.Database;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;
import models.Event;
import models.Room;
import java.io.IOException;

import static data.Database.events;

public class OrganizerDashboard {
    @FXML
private TextField CreateEventField;
    @FXML
    private TextField EventtoUpdateField;
    @FXML
    private TextField NewEventField;
    @FXML
    private TextField NewRoomField;

    @FXML
    private TextField CreateRoomField;
    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TableColumn<Event, String> eventColumn;

    @FXML
    private TableColumn<Event, String> eventIDColumn;

    @FXML
    private TableColumn<Event, String> roomColumn;

    @FXML
    private TableColumn<Event, String> categoryColumn;
    @FXML
    private TextField EventDeleteField;

    @FXML
    private TableColumn<Event, String> attendeesColumn;

    @FXML
    public void initialize() {
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));

        eventIDColumn.setCellValueFactory(new PropertyValueFactory<>("eventID"));

        roomColumn.setCellValueFactory(cellData -> {
            var room = cellData.getValue().getRoom();
            String roomText = (room != null) ? "Room " + room.getRoomNum() : "No Room";
            return new SimpleStringProperty(roomText);
        });
        eventTable.setItems(FXCollections.observableArrayList(events));
    }

    public void Goback(ActionEvent actionEvent) {
        // close current scene
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
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

    public void CreatesField(ActionEvent actionEvent) {
        String eventname = CreateEventField.getText();
        String roomText = CreateRoomField.getText();
        String roomNumberString = roomText.replace("Room ", "").trim();
        try {
            int roomNum = Integer.parseInt(roomNumberString);
            Room room = new Room(roomNum, "Default");

            Event newEvent = new Event(eventname, null, null, room, null, null, 0.0);
            Database.events.add(newEvent);
            eventTable.getItems().add(newEvent);
        } catch (NumberFormatException e) {
            System.out.println("Invalid room number format: " + roomText);
        }
    }


    @FXML
    private void DeleteEvent(ActionEvent actionEvent) {
        String eventToDelete = EventDeleteField.getText().trim();

        if (eventToDelete.isEmpty()) {
            return;
        }

        // Loop through events
        Event eventToRemove = null; // buffer
        for (Event event : Database.events) {
            if (event.getEventName().equalsIgnoreCase(eventToDelete) || event.getEventID().equals(eventToDelete)) {
                eventToRemove = event;
                break;
            }
        }

        // If the event was found, delete it
        if (eventToRemove != null) {
            Database.events.remove(eventToRemove);
            eventTable.getItems().remove(eventToRemove);
        }
    }

    @FXML
    public void Update(ActionEvent actionEvent) {
        String eventToUpdate = EventtoUpdateField.getText().trim();
        String newEventName = NewEventField.getText().trim();
        String newRoomText = NewRoomField.getText().trim();
        Event eventToUpdate2 = null;

        if (eventToUpdate.isEmpty()) return;

        for (Event event : Database.events) {
            if (event.getEventName().equalsIgnoreCase(eventToUpdate) || event.getEventID().equals(eventToUpdate)) {
                eventToUpdate2 = event;
                break;
            }
        }

        if (eventToUpdate2 != null) {
            if (!newEventName.isEmpty()) {
                eventToUpdate2.setEventName(newEventName);
            }

            if (!newRoomText.isEmpty()) {
                try {
                    int newRoomNumber = Integer.parseInt(newRoomText.replace("Room", "").trim());
                    eventToUpdate2.setRoom(new Room(newRoomNumber, "Updated Room"));
                } catch (NumberFormatException e) {
                    // Handle invalid room number
                    return;
                }
            }

            eventTable.refresh(); // Refresh  table  to show updated data
        }
    }

}
