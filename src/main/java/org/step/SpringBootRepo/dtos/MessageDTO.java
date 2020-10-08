package org.step.SpringBootRepo.dtos;

import org.step.SpringBootRepo.entities.Message;

import java.sql.Date;

public class MessageDTO {
    private Long id;
    private String content;
    private Date date;

    public MessageDTO() {}
    public MessageDTO(Long id, String content, Date date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }
    public MessageDTO(Message message) {
        this.id = message.getId();
        this.content = message.getContent();
        this.date = message.getDate();
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
}
