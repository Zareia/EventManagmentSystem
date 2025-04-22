package managers;

import java.util.Scanner;

import users.User;


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
                    User loggedInUser = UserManager.handleLogin(input);
                    if (loggedInUser != null) {
                        UserManager.routeUser(loggedInUser, input);
                    }
                    break;
                case 2:
                    UserManager.handleRegistration(input);
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
}
