package org.step.SpringBootRepo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice(basePackages = {"org.step.SpringBootRepo"})
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private final String errorMessage = "Default error message";

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported
            (HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable
            (HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionResponseEntity exceptionResponse = new ExceptionResponseEntity();
        exceptionResponse.message = ex.getLocalizedMessage();
        exceptionResponse.path = request.getContextPath();
        exceptionResponse.cause = ex.getBindingResult().getAllErrors()
                .stream()
                .collect(Collectors.toMap(ObjectError::getObjectName,
                        oe -> Objects.requireNonNull(oe.getDefaultMessage()).isEmpty()
                                ? errorMessage : oe.getDefaultMessage()));

        return ResponseEntity.status(status)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .body(exceptionResponse);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<ExceptionResponseEntity> handleUserNotFoundException
            (UserNotFoundException ex, WebRequest request) {

        ExceptionResponseEntity exceptionResponse = new ExceptionResponseEntity();
        exceptionResponse.message = ex.getLocalizedMessage();
        exceptionResponse.path = request.getContextPath();
        exceptionResponse.cause = Map.of("User's id:", ex.getId().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Problem", ex.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .body(exceptionResponse);
    }

    @ExceptionHandler(value = {ProfileNotFoundException.class})
    protected ResponseEntity<ExceptionResponseEntity> handleProfileNotFoundException
            (ProfileNotFoundException ex, WebRequest request) {

        ExceptionResponseEntity exceptionResponse = new ExceptionResponseEntity();
        exceptionResponse.message = ex.getLocalizedMessage();
        exceptionResponse.path = request.getContextPath();
        exceptionResponse.cause = Map.of("Profile's id:", ex.getId().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Problem", ex.toString());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .body(exceptionResponse);
    }
}
