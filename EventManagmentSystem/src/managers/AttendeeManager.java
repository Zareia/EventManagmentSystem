package managers;

import java.util.Scanner;

import data.Database;
import models.Event;
import models.Wallet;
import users.Attendee;

// shows the attendees Dashboard and all the actions that an attendee can make
public class AttendeeManager {
    public static void handleAttendee(Scanner input) {
        System.out.println("////////////////////////// Welcome, Attendee! //////////////////////////");
        int choice = 0;

        while (choice != 3) {
            System.out.println("Attendee Dashboard:");
            System.out.println("1. View Events");
            System.out.println("2. Buy Ticket");
            System.out.println("3. Back to Main Menu");
            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    Database.events.forEach(event -> System.out.println(event));
                    break;
                case 2:
                    System.out.print("Enter Event ID to Buy Ticket: ");
                    String eventId = input.nextLine();
                    Event event = Database.events.stream()
                            .filter(e -> e.getEventID().equals(eventId))
                            .findFirst()
                            .orElse(null);

                    if (event != null) {
                        System.out.println("Event: " + event.getEventName());
                        System.out.println("Ticket Price: " + event.getPrice());
                        
                        Attendee attendee = null;
                        for (Attendee a : Database.attendees) {
                            if (a.getUsername().equals(Database.currentUser.getUsername())) {
                                attendee = a;
                                break;
                            }
                        }
                        if (attendee != null) {
                            Wallet wallet = attendee.getWallet();
                            if (wallet.withdraw(event.getPrice())) {
                                System.out.println("Ticket purchased successfully for event: " + event.getEventName());
                                System.out.println("Remaining Balance: " + wallet.getBalance());
                            } else {
                                System.out.println("Insufficient balance. Please add funds to your wallet.");
                            }
                        } else {
                            System.out.println("Attendee not found.");
                        }
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
