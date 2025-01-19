package service;

import model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class UserService {
    private final List<User> users;
    private final NotificationService notificationService;

    public UserService() {
        this.users = new ArrayList<>();
        this.notificationService = new NotificationService();
    }

    public User createUser(String name, String email) {
        User user = new User(name, email);
        users.add(user);
        notificationService.sendTelegramNotification("New user created: " + user.getName());
        return user;
    }

    public Optional<User> findByUuid(UUID uuid) {
        return users.stream()
                .filter(user -> user.getUuid().equals(uuid))
                .findFirst();
    }

    public boolean updateUser(UUID uuid, String name, String email, Boolean isDeleted) {
        Optional<User> userOpt = findByUuid(uuid);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (name != null) user.setName(name);
            if (email != null) user.setEmail(email);
            if (isDeleted != null) user.setDeleted(isDeleted);
            return true;
        }
        return false;
    }

    public boolean deleteUser(UUID uuid) {
        return updateUser(uuid, null, null, true);
    }

    public List<User> getAllActiveUsers(int page, int pageSize) {
        return users.stream()
                .filter(user -> !user.isDeleted())
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public int getTotalPages(int pageSize) {
        long activeUserCount = users.stream().filter(user -> !user.isDeleted()).count();
        return (int) Math.ceil((double) activeUserCount / pageSize);
    }
}

