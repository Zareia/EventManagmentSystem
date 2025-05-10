package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import managers.AuthManager;
import users.User;

import java.io.IOException;

public class Registration {

    @FXML
    private TextField userfield;

    @FXML
    private TextField passwordfield;

    @FXML
    public void registerUser() {
        String username = userfield.getText();
        String password = passwordfield.getText();

        // Assuming you want to use a default role (e.g., UserRole.USER)
        User.UserRole role = User.UserRole.Attendee;

        // Call your AuthManager method with all required parameters
        AuthManager.registerUser(username, password, null, null, role, null, null, 0);
    }

    @FXML
    public void handleRegister(ActionEvent actionEvent) {
        registerUser();
    }

    public void goback(ActionEvent actionEvent) {

        // close current register scene

        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();

        // go back to LOG IN

        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml")); //
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Log-in Menu");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}