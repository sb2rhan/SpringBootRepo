package org.step.SpringBootRepo.exceptions;

import java.util.Map;

public class ExceptionResponseEntity {
    public String message;
    public String path;
    public Map<String, String> cause;
}
