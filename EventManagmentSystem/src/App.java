// import data.Database;
// import managers.CLIManager;


// public class App {
//     public static void main(String[] args) throws Exception {
        
//         // Add dummy data
//         Database.initializeDummyData();
        
//         CLIManager.start();
//     }
// }
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //loads the first scene (wich is the login page)
        Parent root = FXMLLoader.load(getClass().getResource("EMSgui.fxml"));
        primaryStage.setTitle("Event Managment System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}