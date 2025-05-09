package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import managers.AuthManager;
import users.User;

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
} 