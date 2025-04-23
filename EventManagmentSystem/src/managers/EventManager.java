package managers;

import java.util.Scanner;

import data.Database;
import models.Event;
import models.Room;
import models.Category;
import users.Organizer;

public class EventManager implements Manageable<Event> {

    public static void manageEvents(Scanner input) {
        System.out.println("Event Management:");
        System.out.println("1. Add Event");
        System.out.println("2. Update Event");
        System.out.println("3. Delete Event");
        System.out.println("4. Back");
        int choice = input.nextInt();
        input.nextLine();

        switch (choice) {
            case 1: // Add Event
                System.out.print("Enter Event Name: ");
                String eventName = input.nextLine();

                System.out.print("Enter Event Date (yyyy-mm-dd): ");
                String eventDate = input.nextLine();

                // Select Category
                System.out.println("Select a Category:");
                for (int i = 0; i < Category.getPredefinedCategories().size(); i++) {
                    System.out.println((i + 1) + ". " + Category.getPredefinedCategories().get(i));
                }
                System.out.print("Enter the nubmer of your choice (1,2,3,etc...): ");
                int categoryChoice = input.nextInt();
                input.nextLine(); // Clear buffer
                Category category = Category.getPredefinedCategories().get(categoryChoice - 1);

                // Select Room
                System.out.println("Available Rooms:");
                for (int i = 0; i < Database.rooms.size(); i++) {
                    System.out.println((i + 1) + ". " + Database.rooms.get(i));
                }
                System.out.print("Enter choice: ");
                int roomChoice = input.nextInt();
                input.nextLine(); // Clear buffer
                Room room = Database.rooms.get(roomChoice - 1);

                // Select Organizer
                System.out.println("Available Organizers:");
                for (int i = 0; i < Database.organizers.size(); i++) {
                    System.out.println((i + 1) + ". " + Database.organizers.get(i).getUsername());
                }
                System.out.print("Enter choice: ");
                int organizerChoice = input.nextInt();
                input.nextLine(); // Clear buffer
                Organizer organizer = Database.organizers.get(organizerChoice - 1);

                // Set Price
                System.out.print("Enter Ticket Price: ");
                double price = input.nextDouble();
                input.nextLine(); // Clear buffer

                // Create and Add Event
                Event newEvent = new Event(eventName, eventDate, category, room, organizer, null, price);
                new EventManager().create(newEvent);
                System.out.println("Event added successfully!");
                break;

            case 2: // Update Event
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

            case 3: // Delete Event
                System.out.print("Enter Event ID to Delete: ");
                String eventIdToDelete = input.nextLine();
                new EventManager().delete(eventIdToDelete);
                System.out.println("Event deleted successfully!");
                break;

            case 4: // Back
                System.out.println("Returning to Organizer Dashboard...");
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    

    public static void manageRooms(Scanner input) {
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

    

    @Override
    public void create(Event event) {
        Database.events.add(event);
    }

    @Override
    public Event read(String id) {
        for (Event e : Database.events) {
            if (e.getEventID().equals(id)) return e;
        }
        return null;
    }

    @Override
    public void update(String id, Event updatedEvent) {
        for (int i = 0; i < Database.events.size(); i++) {
            if (Database.events.get(i).getEventID().equals(id)) {
                Database.events.set(i, updatedEvent);
                return;
            }
        }
    }

    @Override
    public void delete(String id) {
        Database.events.removeIf(e -> e.getEventID().equals(id));
    }
}
