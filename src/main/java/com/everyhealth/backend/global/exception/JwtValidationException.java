package com.everyhealth.backend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class JwtValidationException extends RuntimeException {

    private final HttpStatus status;
    private final String message;

    public JwtValidationException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
