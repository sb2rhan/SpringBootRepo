package org.step.SpringBootRepo.entities;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
@DynamicUpdate
public class Profile {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "description", nullable = false, length = 512)
    private String description;

    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_profile_user"))
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "profile"
    )
    private List<Message> messages = new ArrayList<>();

    public Profile() {}

    private Profile(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public static ProfileBuilder builder() {
        return new ProfileBuilder();
    }

    public static class ProfileBuilder {
        private Long id;
        private String description;

        public ProfileBuilder() {}

        public ProfileBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ProfileBuilder description(String description) {
            this.description = description;
            return this;
        }

        public Profile build() {
            return new Profile(id, description);
        }
    }

    @Override
    public String toString() {
        return String.format("Profile{id: %d, description: %s}", this.getId(), this.getDescription());
    }
}
