package org.step.SpringBootRepo.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",
            foreignKey = @ForeignKey(name = "fk_message_profile"), nullable = false)
    private Profile profile;

    public Message() {}

    private Message(Long id, String content, Date date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public static class MessageBuilder {
        private Long id;
        private String content;
        private Date date;

        public MessageBuilder() {}

        public MessageBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public MessageBuilder content(String content) {
            this.content = content;
            return this;
        }

        public MessageBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public Message build() {
            return new Message(id, content, date);
        }
    }
}
