package model;

import java.util.UUID;

public class User {
    private Long id;
    private UUID uuid = UUID.randomUUID();;
    private String name;
    private String email;
    private boolean isDeleted;
    private static Long idCounter = 1L;

    public User(String name, String email) {
        this.id = idCounter++;
        this.uuid = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.isDeleted = false;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public UUID getUuid() { return uuid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public boolean isDeleted() { return isDeleted; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }

    @Override
    public String toString() {
        return String.format("User{id=%d, uuid=%s, name='%s', email='%s', isDeleted=%s}",
                id, uuid, name, email, isDeleted);
    }
}

