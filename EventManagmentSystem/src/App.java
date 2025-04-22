import data.Database;
import managers.CLIManager;


public class App {
    public static void main(String[] args) throws Exception {
        
        // Add dummy data
        Database.initializeDummyData();
        
        CLIManager.start();
    }
}
