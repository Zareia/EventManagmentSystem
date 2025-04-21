package managers;

import java.util.Scanner;

import users.User;
import utilities.InputUtils;

public class CLIManager {
    public static void start() {
        Scanner input = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("//////////////////// Event Management System ////////////////////");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int mainChoice = input.nextInt();
            input.nextLine();

            switch (mainChoice) {
                case 1:
                    User loggedInUser = handleLogin(input);
                    if (loggedInUser != null) {
                        routeUser(loggedInUser, input);
                    }
                    break;
                case 2:
                    handleRegistration(input);
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }

    private static User handleLogin(Scanner input) {
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

    private static void handleRegistration(Scanner input) {
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

        boolean success = AuthManager.registerUser(username, password, dob, gender, role, address, interests, balance);

        if (success) {
            System.out.println("Registration successful! You can now login.");
        }
    }

    private static void routeUser(User user, Scanner input) {
        switch (user.getRole()) {
            case Admin:
                handleAdmin(input);
                break;
            case Organizer:
                handleOrganizer(input);
                break;
            case Attendee:
                handleAttendee(input);
                break;
        }
    }

    private static void handleAdmin(Scanner input) {
        System.out.println("////////////////////////// Welcome, Admin! //////////////////////////");
        int choice = 0;

        while (choice != 4) {
            System.out.println("Admin Dashboard:");
            System.out.println("1. View All Rooms");
            System.out.println("2. View All Events");
            System.out.println("3. View All Attendees");
            System.out.println("4. Back to Main Menu");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Call Admin's viewAllRooms method
                    System.out.println("Displaying all rooms...");
                    break;
                case 2:
                    // Call Admin's viewAllEvents method
                    System.out.println("Displaying all events...");
                    break;
                case 3:
                    // Call Admin's viewAllAttendees method
                    System.out.println("Displaying all attendees...");
                    break;
                case 4:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleOrganizer(Scanner input) {
        System.out.println("////////////////////////// Welcome, Organizer! //////////////////////////");
        int choice = 0;

        while (choice != 3) {
            System.out.println("Organizer Dashboard:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. View Your Events");
            System.out.println("3. Back to Main Menu");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Call Organizer's viewAvailableRooms method
                    System.out.println("Displaying available rooms...");
                    break;
                case 2:
                    // Call Organizer's getEvents method
                    System.out.println("Displaying your events...");
                    break;
                case 3:
                    System.out.println("Returning to Main Menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void handleAttendee(Scanner input) {
        System.out.println("////////////////////////// Welcome, Attendee! //////////////////////////");
        int choice = 0;

        while (choice != 3) {
            System.out.println("Attendee Dashboard:");
            System.out.println("1. View Events");
            System.out.println("2. Manage Wallet");
            System.out.println("3. Back to Main Menu");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    // Display events for the attendee
                    System.out.println("Displaying events...");
                    break;
                case 2:
                    // Manage wallet functionality
                    System.out.println("Managing wallet...");
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
