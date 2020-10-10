package org.step.SpringBootRepo.dtos;

import org.step.SpringBootRepo.entities.User;

public class UserFullDTO {
    private Long id;
    private String username;
    private String passwordHash;

    public UserFullDTO() {}
    public UserFullDTO(Long id, String username, String passwordHash) {
        this.id = id;
        this.username = username;
        this.passwordHash = passwordHash;
    }
    public UserFullDTO(User user) {
        this(user.getId(), user.getUsername(), user.getPassword());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
