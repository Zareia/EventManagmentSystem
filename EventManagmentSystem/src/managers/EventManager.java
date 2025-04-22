package managers;

import java.util.Scanner;

import data.Database;
import models.Event;
import models.Room;
import models.Wallet;
import users.Attendee;

public class EventManager implements Manageable<Event> {

    @Override
    public void create(Event event) {
        Database.events.add(event);
        System.out.println("Event created: " + event.getEventName());
    }

    @Override
    public Event read(String ID) {
        for(Event event : Database.events){
            if (event.getEventID().equals(ID)) {
                return event;
            }
        }
        return null;
    }

    @Override
    public void update(String ID, Event updatedObj) {
        for (int i = 0; i < Database.events.size(); i++) {
            if (Database.events.get(i).getEventID().equals(ID)) {
                Database.events.set(i, updatedObj);
                System.out.println("Event updated: " + updatedObj.getEventName());
                return;
            }
        }
        System.out.println("Event not found.");
    }

    @Override
    public void delete(String ID) {
        if (Database.events.removeIf(event -> event.getEventID().equals(ID))) {
            System.out.println("Event deleted.");
        } else {
            System.out.println("Event not found.");
        }
    }

    public static void manageEvents(Scanner input) {
        System.out.println("Event Management:");
        System.out.println("1. Add Event");
        System.out.println("2. Update Event");
        System.out.println("3. Delete Event");
        System.out.println("4. Back");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Enter Event Name: ");
                String eventName = input.nextLine();
                System.out.print("Enter Event Date: ");
                String eventDate = input.nextLine();
                // Add logic to select category, room, organizer, attendee, and price
                new EventManager().create(new Event(eventName, eventDate, null, null, null, null, 0.0));
                break;
            case 2:
                System.out.print("Enter Event ID to Update: ");
                String eventId = input.nextLine();
                Event eventToUpdate = new EventManager().read(eventId);
                if (eventToUpdate != null) {
                    System.out.print("Enter New Event Name: ");
                    eventToUpdate.setEventName(input.nextLine());
                    new EventManager().update(eventId, eventToUpdate);
                } else {
                    System.out.println("Event not found.");
                }
                break;
            case 3:
                System.out.print("Enter Event ID to Delete: ");
                String eventIdToDelete = input.nextLine();
                new EventManager().delete(eventIdToDelete);
                break;
            case 4:
                System.out.println("Returning to Organizer Dashboard...");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

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
                    manageEvents(input);
                    break;
                case 3:
                    Database.events.stream()
                            .filter(event -> event.getOrganizer().getUsername().equals("OrganizerUsername")) // Replace with actual organizer username
                            .forEach(event -> System.out.println(event));
                    break;
                case 4:
                    manageRooms(input);
                    break;
                case 5:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void manageRooms(Scanner input) {
        System.out.println("Room Management:");
        System.out.println("1. Add Room");
        System.out.println("2. Update Room");
        System.out.println("3. Delete Room");
        System.out.println("4. Back");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1: // Add Room
                System.out.print("Enter Room Number: ");
                int roomNum = input.nextInt();
                input.nextLine(); // Clear buffer
                System.out.print("Enter Available Hours: ");
                String availableHrs = input.nextLine();
                Database.rooms.add(new Room(roomNum, availableHrs));
                System.out.println("Room added successfully!");
                break;

            case 2: // Update Room
                System.out.print("Enter Room ID to Update: ");
                String roomId = input.nextLine();
                Room roomToUpdate = Database.rooms.stream()
                        .filter(room -> room.getRoomID().equals(roomId))
                        .findFirst()
                        .orElse(null);
                if (roomToUpdate != null) {
                    System.out.print("Enter New Room Number: ");
                    roomToUpdate.setRoomNum(input.nextInt());
                    input.nextLine(); // Clear buffer
                    System.out.print("Enter New Available Hours: ");
                    roomToUpdate.setAvailableHrs(input.nextLine());
                    System.out.println("Room updated successfully!");
                } else {
                    System.out.println("Room not found.");
                }
                break;

            case 3: // Delete Room
                System.out.print("Enter Room ID to Delete: ");
                String roomIdToDelete = input.nextLine();
                if (Database.rooms.removeIf(room -> room.getRoomID().equals(roomIdToDelete))) {
                    System.out.println("Room deleted successfully!");
                } else {
                    System.out.println("Room not found.");
                }
                break;

            case 4: // Back
                System.out.println("Returning to Organizer Dashboard...");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

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

                        // Assuming the logged-in user is an attendee
                        Attendee attendee = (Attendee) Database.attendees.stream()
                                .filter(a -> a.getUsername().equals(Database.currentUser.getUsername()))
                                .findFirst()
                                .orElse(null);

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
