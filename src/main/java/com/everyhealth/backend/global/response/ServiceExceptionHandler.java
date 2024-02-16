package com.everyhealth.backend.global.response;

import com.everyhealth.backend.global.exception.JwtValidationException;
import com.everyhealth.backend.global.exception.ResourceNotFound;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public final ResponseEntity<ApiResponse<?>> handleNotFoundException(
            ResourceNotFound ex, WebRequest request) {
        ApiResponse<?> errorResponse =
                ApiResponse.createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public final ResponseEntity<ApiResponse<?>> handleDuplicateKeyException(
            DuplicateKeyException ex, WebRequest request) {
        ApiResponse<?> errorResponse =
                ApiResponse.createErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(JwtValidationException.class)
    public final ResponseEntity<ApiResponse<?>> handleJwtValidationException(
            JwtValidationException ex, WebRequest request) {
        ApiResponse<?> errorResponse =
                ApiResponse.createErrorResponse(ex.getStatus(), ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }
}
