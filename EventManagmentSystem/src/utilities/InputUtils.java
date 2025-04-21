package utilities;

import java.util.Scanner;

import users.User;

public class InputUtils {
    public static User.Gender parseGender(Scanner input) {
        while (true) {
            System.out.print("Gender (Male/Female): ");
            String genderInput = input.nextLine().trim();
            try {
                return User.Gender.valueOf(
                    genderInput.substring(0, 1).toUpperCase() + genderInput.substring(1).toLowerCase()
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid gender. Try again.");
            }
        }
    }

    public static User.UserRole parseRole(Scanner input) {
        while (true) {
            System.out.print("Role (Admin/Organizer/Attendee): ");
            String roleInput = input.nextLine().trim();
            try {
                return User.UserRole.valueOf(
                    roleInput.substring(0, 1).toUpperCase() + roleInput.substring(1).toLowerCase()
                );
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role. Try again.");
            }
        }
    }
}

