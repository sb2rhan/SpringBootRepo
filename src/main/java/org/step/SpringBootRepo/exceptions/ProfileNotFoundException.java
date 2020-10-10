package org.step.SpringBootRepo.exceptions;

public class ProfileNotFoundException extends RuntimeException {
    private Long id;

    public ProfileNotFoundException() { super(); }
    public ProfileNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
