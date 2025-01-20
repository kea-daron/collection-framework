import controller.UserController;
import service.NotificationService;

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();

        UserController controller = new UserController(notificationService);

        controller.processUser();
    }
}