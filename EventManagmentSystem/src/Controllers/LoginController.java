package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import managers.AuthManager;

public class LoginController {

    @FXML
    private Button loginBtn;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    
    @FXML
    public void loginBtnOnAction(){
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (AuthManager.login(username, password) != null) {
            
        }
    }
    

    @FXML
    private void regiBtnOnAction(ActionEvent event) {
        
    }

}
