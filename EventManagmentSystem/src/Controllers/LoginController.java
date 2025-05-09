package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import managers.AuthManager;
import users.User;

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
    public void loginBtnOnAction(){
        String username = usernameField.getText();
        String password = passFeild.getText();

        User loggedInUser = AuthManager.login(username, password);

        if (loggedInUser != null) {
            AuthManager.routeUser(loggedInUser, null);
            System.out.println("login succsful");
        }
        else{
            loginValidationMsg.setText("username or password are invalid please try again");
            
        }
    }
        @FXML
        private void regiBtnOnAction(ActionEvent event) {   
        
        }

}
