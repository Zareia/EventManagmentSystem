package managers;

import java.util.Scanner;

import data.Database;
import users.User;
import users.Admin;
import users.Organizer;
import users.Attendee;
import utilities.InputUtils;

public class UserManager {

    public static User handleLogin(Scanner input) {
        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        User user = AuthManager.login(username, password);
        if (user == null) {
            System.out.println("Login failed. Invalid credentials.");
        } else {
            System.out.println("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
        }
        return user;
    }

    public static void handleRegistration(Scanner input) {
        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter date of birth (yyyy-mm-dd): ");
        String dob = input.nextLine();

        User.Gender gender = InputUtils.parseGender(input);

        User.UserRole role = InputUtils.parseRole(input);

        String address = "", interests = "";
        double balance = 0.0;

        if (role == User.UserRole.Attendee) {
            System.out.print("Enter your interests: ");
            interests = input.nextLine();
            System.out.print("Enter your address: ");
            address = input.nextLine();
            System.out.print("Initial wallet balance: ");
            balance = input.nextDouble();
            input.nextLine(); // clear buffer
        }

        // Add the user to the appropriate list in the Database
        switch (role) {
            case Admin:
                Admin admin = new Admin(username, password, dob, gender);
                admin.setRole(User.UserRole.Admin); // Set the role explicitly
                Database.admins.add(admin);
                System.out.println("Admin registered successfully!");
                break;
            case Organizer:
                Organizer organizer = new Organizer(username, password, dob, gender);
                organizer.setRole(User.UserRole.Organizer); // Set the role explicitly
                Database.organizers.add(organizer);
                System.out.println("Organizer registered successfully!");
                break;
            case Attendee:
                Attendee attendee = new Attendee(username, password, dob, gender, interests, address, balance);
                attendee.setRole(User.UserRole.Attendee); // Set the role explicitly
                Database.attendees.add(attendee);
                System.out.println("Attendee registered successfully!");
                break;
            default:
                System.out.println("Invalid role. Registration failed.");
                return;
        }
    }

    public static void routeUser(User user, Scanner input) {
        switch (user.getRole()) {
            case Admin:
                AdminManager.handleAdmin(input);
                break;
            case Organizer:
                EventManager.handleOrganizer(input);
                break;
            case Attendee:
                EventManager.handleAttendee(input);
                break;
        }
    }
}
