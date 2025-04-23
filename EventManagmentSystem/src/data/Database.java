package data;

import java.util.ArrayList;

import models.Category;
import models.Event;
import models.Room;
import users.Admin;
import users.Attendee;
import users.Organizer;
import users.User;

public class Database {

public static ArrayList<Attendee> attendees = new ArrayList<>();
public static ArrayList<Organizer> organizers = new ArrayList<>();
public static ArrayList<Admin> admins = new ArrayList<>();
public static ArrayList<Event> events = new ArrayList<>();
public static ArrayList<Room> rooms = new ArrayList<>();

public static User currentUser; // Track the currently logged-in user

// dummy data for testing
public static void initializeDummyData() {
    // Add Admins
    admins.add(new Admin("admin1", "password123", "1980-01-01", Admin.Gender.Male));
    admins.add(new Admin("admin2", "password123", "1985-05-15", Admin.Gender.Female));

    // Add Organizers
    organizers.add(new Organizer("organizer1", "password123", "1990-03-10", Organizer.Gender.Male));
    organizers.add(new Organizer("organizer2", "password123", "1992-07-20", Organizer.Gender.Female));

    // Add Attendees
    attendees.add(new Attendee("attendee1", "password123", "2000-08-25", Attendee.Gender.Male, "Music, Sports", "123 Main St", 100.0));
    attendees.add(new Attendee("attendee2", "password123", "1998-11-30", Attendee.Gender.Female, "Art, Tech", "456 Elm St", 50.0));

    // Add Rooms
    rooms.add(new Room(101, "9 AM - 5 PM"));
    rooms.add(new Room(102, "10 AM - 6 PM"));

    // Add Events
    events.add(new Event("Music Concert", "2025-05-01", Category.MUSIC, rooms.get(0), organizers.get(0), null, 50.0));
    events.add(new Event("Tech Conference", "2025-06-15", Category.TECHNOLOGY, rooms.get(1), organizers.get(1), null, 75.0));

    System.out.println("\n");
    System.out.println("Dummy data initialized successfully!");
}

}
