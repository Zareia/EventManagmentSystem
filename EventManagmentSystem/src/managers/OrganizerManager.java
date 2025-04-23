package managers;

import java.util.Scanner;

import data.Database;
import models.Event;


// Organizer Dashboard with all the actions that he could take
public class OrganizerManager {
    public static void handleOrganizer(Scanner input) {
        System.out.println("////////////////////////// Welcome, Organizer! //////////////////////////");
        int choice = 0;

        while (choice != 5) {
            System.out.println("Organizer Dashboard:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Manage Events");
            System.out.println("3. View Your Events");
            System.out.println("4. Manage Rooms");
            System.out.println("5. Back to Main Menu");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    Database.rooms.forEach(room -> System.out.println(room));
                    break;
                case 2:
                    EventManager.manageEvents(input);
                    break;
                case 3:
                    for (Event event : Database.events) {
                        if (event.getOrganizer().getUsername().equals(Database.currentUser.getUsername())) {
                            System.out.println(event);
                        }
                    }   
                    //future else statement
                    break;
                case 4:
                    EventManager.manageRooms(input);
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
