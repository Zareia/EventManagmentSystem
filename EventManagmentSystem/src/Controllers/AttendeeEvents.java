package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.Event;
import data.Database;

public class AttendeeEvents {

    @FXML
    private Label eventlist;

    @FXML
    public void initialize() {
        StringBuilder eventsText = new StringBuilder("Available Events:\n\n");

        for (Event event : Database.events) {
            // Append event name and date to the string
            // yet to be fixed
            eventsText = eventsText.append("Event: ").append(event.getEventName())
                    .append("\nDate: ").append(event.getEventDate())
                    .append("\nPrice: ").append(event.getPrice()).append(" egp")
                    .append("\n\n");
        }

        eventlist.setText(String.valueOf(eventsText));
    }
}
