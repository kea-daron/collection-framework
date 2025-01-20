package view;

import model.User;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class UserView {
    private final Scanner scanner;

    public UserView() {
        this.scanner = new Scanner(System.in);
    }

    public int showMenu() {
        System.out.println("\n=== User Management Console ===");
        System.out.println("1. Create user");
        System.out.println("2. Search user by UUID");
        System.out.println("3. Update user");
        System.out.println("4. Delete user");
        System.out.println("5. Display all users");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public String[] getUserInfo() {
        scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        return new String[]{name, email};
    }

    public UUID getUuid() {
        scanner.nextLine();
        System.out.print("Enter UUID: ");
        return UUID.fromString(scanner.nextLine());
    }

    public String[] getUpdateInfo() {
        System.out.print("Enter new name (press Enter to skip): ");
        String name = scanner.nextLine();
        System.out.print("Enter new email ( press Enter to skip): ");
        String email = scanner.nextLine();
        System.out.print("Enter new isDeleted status (true/false) (or press Enter to skip): ");
        String isDeleted = scanner.nextLine();
        return new String[]{name, email, isDeleted};
    }

    public void displayUser(User user) {
        System.out.println("\nUser Details:");
        System.out.println("ID: " + user.getId());
        System.out.println("UUID: " + user.getUuid());
        System.out.println("Name: " + user.getName());
        System.out.println("Email: " + user.getEmail());
    }

    public void displayUsers(List<User> users, int currentPage, int totalPages) {
        System.out.println("\nUsers List (Page " + currentPage + " of " + totalPages + "):");
        System.out.println("--------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-36s | %-20s | %-30s%n", "ID", "UUID", "Name", "Email");
        System.out.println("--------------------------------------------------------------------------------------------------------");

        for (User user : users) {
            System.out.printf("%-5d | %-36s | %-20s | %-30s%n",
                    user.getId(),
                    user.getUuid(),
                    user.getName(),
                    user.getEmail());
        }
        System.out.println("--------------------------------------------------------------------------------------------------------");

        if (totalPages > 1) {
            System.out.println("\nNavigation: [P]revious, [N]ext, e[X]it");
        }
    }

    public String getPageNavigation() {
        System.out.print("Enter navigation choice: ");
        return scanner.next().toLowerCase();
    }

    public void displayMessage(String message) {
        System.out.println("\n" + message);
    }
}