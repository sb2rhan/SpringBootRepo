package org.step.SpringBootRepo.dtos;

import org.step.SpringBootRepo.entities.User;

public class UserDTO {
    private Long id;
    private String username;

    public UserDTO() {}
    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }
    public UserDTO(User user) {
        this(user.getId(), user.getUsername());
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
}
