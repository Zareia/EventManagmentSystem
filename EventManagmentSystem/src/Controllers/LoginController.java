package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import managers.AuthManager;
import users.Attendee;
import users.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passFeild;

    @FXML
    public void loginBtnOnAction(ActionEvent event) {
        String username = usernameField.getText();
        String password = passFeild.getText();

        // Try to log the user in
        User loggedInUser = AuthManager.login(username, password);

        // If login is successful
        if (loggedInUser instanceof Attendee) {
            // Close the current login window
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.close();

            // Load the next screen
            try {
                Parent root = FXMLLoader.load(getClass().getResource("loggedinattendee.fxml")); //
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setTitle("AttendeeDashboard");
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            // Handle failed login
            System.out.println("Login failed: Invalid username or password");
        }
    }

    @FXML
    private void regiBtnOnAction(ActionEvent event) {
        // Close the current window (stage)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        // Load the registration scene
        try {
            Parent root = FXMLLoader.load(getClass().getResource("registration.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Register");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}