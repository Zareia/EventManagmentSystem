package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import managers.AuthManager;
import users.Admin;
import users.Attendee;
import users.Organizer;
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
    private Label loginValidationMsg;

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
                Parent root = FXMLLoader.load(getClass().getResource("loggedinattendee.fxml"));
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Attendee Dashboard");
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (loggedInUser instanceof Admin) {
            // Close the current login window
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.close();

            // Load the next screen
            try {
                Parent root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Admin Dashboard");
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (loggedInUser instanceof Organizer) {
            // Close the current login window
            Stage stage = (Stage) loginBtn.getScene().getWindow();
            stage.close();
            // open organizer dashboard
            try {
                Parent root = FXMLLoader.load(getClass().getResource("OrganizerDashboard.fxml")); //
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.setTitle("Organizerdashboard");
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            loginValidationMsg.setText("Wrong username or Password. Please Try Again.");
        }
    }
        
    

    @FXML
    private void regiBtnOnAction(ActionEvent event) {
        // Close the current window (stage)
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        // Load the registration scene
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));
            newStage.setTitle("Register");
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}