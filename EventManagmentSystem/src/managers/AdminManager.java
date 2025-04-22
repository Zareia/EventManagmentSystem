package managers;

import java.util.Scanner;

import data.Database;

public class AdminManager {

    public static void handleAdmin(Scanner input) {
        System.out.println("////////////////////////// Welcome, Admin! //////////////////////////");
        int choice = 0;

        while (choice != 4) {
            System.out.println("Admin Dashboard:");
            System.out.println("1. View All Rooms");
            System.out.println("2. View All Events");
            System.out.println("3. View All Attendees");
            System.out.println("4. Back to Main Menu");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    Database.rooms.forEach(room -> System.out.println(room));
                    break;
                case 2:
                    Database.events.forEach(event -> System.out.println(event));
                    break;
                case 3:
                    Database.attendees.forEach(attendee -> System.out.println(attendee.getUsername()));
                    break;
                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}