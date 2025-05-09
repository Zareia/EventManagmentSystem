package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Loggedinattendee {

    @FXML
    private Button events;

    @FXML
    private Button tickets;

    @FXML
    public void initialize() {
    }

    @FXML
    private void EventBtnOnAction(ActionEvent event) {
        try {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            // Load the new scene
            Parent root = FXMLLoader.load(getClass().getResource("AtendeeEvents.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Available Events");
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void TicketBtnOnAction(){
        System.out.println("ticket is purchased");
    }
}