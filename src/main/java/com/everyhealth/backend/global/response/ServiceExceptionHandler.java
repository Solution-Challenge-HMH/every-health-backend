package com.everyhealth.backend.global.response;

import com.everyhealth.backend.global.exception.JwtValidationException;
import com.everyhealth.backend.global.exception.ResourceNotFound;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ApiResponse<Object> handleAllExceptions(Exception ex, WebRequest request) {
        return ApiResponse.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(ResourceNotFound.class)
    public final ApiResponse<Object> handleNotFoundException(
            ResourceNotFound ex, WebRequest request) {
        return ApiResponse.createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public final ApiResponse<Object> handleDuplicateKeyException(
            DuplicateKeyException ex, WebRequest request) {
        return ApiResponse.createErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(JwtValidationException.class)
    public final ApiResponse<Object> handleJwtValidationException(
            JwtValidationException ex, WebRequest request) {
        return ApiResponse.createErrorResponse(ex.getStatus(), ex.getMessage());
    }
}
