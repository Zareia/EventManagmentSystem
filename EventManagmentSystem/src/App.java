import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

 import static data.Database.initializeDummyData;

 public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //loads the first scene (wich is the login page)
        Parent root = FXMLLoader.load(getClass().getResource("Controllers/Login.fxml"));
        primaryStage.setTitle("Event Managment System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        initializeDummyData();
        launch(args);
    }
}