package managers;

import java.util.Scanner;

import data.Database;
import users.Admin;
import users.Attendee;
import users.Organizer;
import users.User;
import utilities.InputUtils;

public class AuthManager {

    public static User login(String username, String password) {
        // Check Admins
        for (Admin admin : Database.admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                admin.setRole(User.UserRole.Admin); // Ensure role is set
                Database.currentUser = admin; // Set the current user
                return admin;
            }
        }

        // Check Organizers
        for (Organizer organizer : Database.organizers) {
            if (organizer.getUsername().equals(username) && organizer.getPassword().equals(password)) {
                organizer.setRole(User.UserRole.Organizer); // Ensure role is set
                Database.currentUser = organizer; // Set the current user
                return organizer;
            }
        }

        // Check Attendees
        for (Attendee attendee : Database.attendees) {
            if (attendee.getUsername().equals(username) && attendee.getPassword().equals(password)) {
                attendee.setRole(User.UserRole.Attendee); // Ensure role is set
                Database.currentUser = attendee; // Set the current user
                return attendee;
            }
        }

        return null; // Login failed
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


    // public static User handleLogin(Scanner input) {
    //     System.out.print("Enter username: ");
    //     String username = input.nextLine();

    //     System.out.print("Enter password: ");
    //     String password = input.nextLine();

    //     User user = AuthManager.login(username, password);
    //     if (user == null) {
    //         System.out.println("Login failed. Invalid credentials.");
    //     } else {
    //         System.out.println("Welcome, " + user.getUsername() + " (" + user.getRole() + ")");
    //     }
    //     return user;
    // }

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
                admin.setRole(User.UserRole.Admin); 
                Database.admins.add(admin);
                System.out.println("Admin registered successfully!");
                break;
            case Organizer:
                Organizer organizer = new Organizer(username, password, dob, gender);
                organizer.setRole(User.UserRole.Organizer); 
                Database.organizers.add(organizer);
                System.out.println("Organizer registered successfully!");
                break;
            case Attendee:
                Attendee attendee = new Attendee(username, password, dob, gender, interests, address, balance);
                attendee.setRole(User.UserRole.Attendee); 
                Database.attendees.add(attendee);
                System.out.println("Attendee registered successfully!");
                break;
            default:
                System.out.println("Invalid role. Registration failed.");
                return;
        }
    }

    // routes user input to the handlermethods to open their dashboard based on their role
    public static void routeUser(User user, Scanner input) {
        switch (user.getRole()) {
            case Admin:
                AdminManager.handleAdmin(input);
                break;
            case Organizer:
                OrganizerManager.handleOrganizer(input);
                break;
            case Attendee:
                AttendeeManager.handleAttendee(input);
                break;
        }
    }









}


