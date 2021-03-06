package org.step.SpringBootRepo.exceptions;

public class UserNotFoundException extends RuntimeException {
    private Long id;

    public UserNotFoundException() { super(); }
    public UserNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
