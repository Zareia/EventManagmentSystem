package managers;

import data.Database;
import users.Admin;
import users.Attendee;
import users.Organizer;
import users.User;

public class AuthManager {

    public static User login(String username, String password) {
        for (Admin admin : Database.admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                return admin;
            }
        }

        for (Organizer organizer : Database.organizers) {
            if (organizer.getUsername().equals(username) && organizer.getPassword().equals(password)) {
                return organizer;
            }
        }

        for (Attendee attendee : Database.attendees) {
            if (attendee.getUsername().equals(username) && attendee.getPassword().equals(password)) {
                return attendee;
            }
        }

        return null;
    }

    public static boolean isUsernameTaken(String username) {
        for (Admin admin : Database.admins)
            if (admin.getUsername().equals(username)) return true;

        for (Organizer organizer : Database.organizers)
            if (organizer.getUsername().equals(username)) return true;

        for (Attendee attendee : Database.attendees)
            if (attendee.getUsername().equals(username)) return true;

        return false;
    }

    public static boolean registerUser(String username, String password, String dateOfBirth,
                                       User.Gender gender, User.UserRole role,
                                       String address, String interests, double balance) {
        if (isUsernameTaken(username)) {
            System.out.println("Username already exists.");
            return false;
        }

        switch (role) {
            case Admin:
                Database.admins.add(new Admin(username, password, dateOfBirth, gender));
                break;
            case Organizer:
                Database.organizers.add(new Organizer(username, password, dateOfBirth, gender));
                break;
            case Attendee:
                Database.attendees.add(new Attendee(username, password, dateOfBirth, gender, interests, address, balance));
                break;
            default:
                System.out.println("Invalid user role.");
                return false;
        }

        System.out.println("User registered successfully.");
        return true;
    }

    public static boolean validatePassword(User user, String inputPassword) {
        return user.getPassword().equals(inputPassword);
    }
}


