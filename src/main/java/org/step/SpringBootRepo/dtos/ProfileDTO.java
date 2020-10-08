package org.step.SpringBootRepo.dtos;

import org.step.SpringBootRepo.entities.Profile;

public class ProfileDTO {
    private Long id;
    private String description;
    private String username;

    public ProfileDTO() {
    }
    public ProfileDTO(Long id, String description, String username) {
        this.id = id;
        this.description = description;
        this.username = username;
    }
    public ProfileDTO(Profile profile) {
        this.id = profile.getId();
        this.description = profile.getDescription();
        this.username = profile.getUser().getUsername();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
