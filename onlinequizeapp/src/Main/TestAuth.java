package Main;

import dao.UserDAO;

import java.util.Scanner;

public class TestAuth {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1) Register  2) Login");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (choice == 1) {
            boolean success = userDAO.registerUser(username, password);
            if (success) {
                System.out.println(" User registered successfully!");
            } else {
                System.out.println(" Registration failed.");
            }
        } else if (choice == 2) {
            boolean valid = userDAO.authenticateUser(username, password);
            if (valid) {
                System.out.println(" Login successful!");
            } else {
                System.out.println(" Invalid username or password.");
            }
        }
    }
}
