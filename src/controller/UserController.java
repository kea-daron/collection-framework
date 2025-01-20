package controller;

import model.User;
import service.UserService;
import view.UserView;
import service.NotificationService;

import java.util.List;
import java.util.UUID;

public class UserController {
    private final UserService userService;
    private final UserView userView;

    public UserController(NotificationService notificationService) {
        this.userService = new UserService(notificationService);
        this.userView = new UserView();
    }

    public void processUser() {
        boolean running = true;
        while (running) {
            int choice = userView.showMenu();
            switch (choice) {
                case 1 -> createUser();
                case 2 -> searchUser();
                case 3 -> updateUser();
                case 4 -> deleteUser();
                case 5 -> displayUsers();
                case 6 -> running = false;
                default -> userView.displayMessage("Invalid option. Please try again.");
            }
        }
    }

    private void createUser() {
        String[] userInfo = userView.getUserInfo();
        User user = userService.createUser(userInfo[0], userInfo[1]);
        userView.displayMessage("User created successfully with UUID: " + user.getUuid());
    }

    private void searchUser() {
        UUID uuid = userView.getUuid();
        userService.findByUuid(uuid)
                .ifPresentOrElse(
                        userView::displayUser,
                        () -> userView.displayMessage("User not found!")
                );
    }

    private void updateUser() {
        UUID uuid = userView.getUuid();
        String[] updateInfo = userView.getUpdateInfo();
        Boolean isDeleted = updateInfo[2].isEmpty() ? null : Boolean.parseBoolean(updateInfo[2]);

        boolean success = userService.updateUser(
                uuid,
                updateInfo[0].isEmpty() ? null : updateInfo[0],
                updateInfo[1].isEmpty() ? null : updateInfo[1],
                isDeleted
        );

        userView.displayMessage(success ? "User updated successfully!" : "User not found!");
    }

    private void deleteUser() {
        UUID uuid = userView.getUuid();
        boolean success = userService.deleteUser(uuid);
        userView.displayMessage(success ? "User deleted successfully!" : "User not found!");
    }

    private void displayUsers() {
        int currentPage = 1;
        int pageSize = 5;
        int totalPages = userService.getTotalPages(pageSize);

        while (true) {
            List<User> users = userService.getAllActiveUsers(currentPage, pageSize);
            userView.displayUsers(users, currentPage, totalPages);

            if (totalPages <= 1) break;

            String navigation = userView.getPageNavigation();
            if (navigation.equalsIgnoreCase("n") && currentPage < totalPages) {
                currentPage++;
            } else if (navigation.equalsIgnoreCase("p") && currentPage > 1) {
                currentPage--;
            } else if (navigation.equalsIgnoreCase("x")) {
                break;
            }
        }
    }
}