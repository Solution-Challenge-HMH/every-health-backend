package com.everyhealth.backend.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private HttpStatus status;
    private String message;
    private T data;

    public static <T> ApiResponse<T> createSuccessResponse(HttpStatus status, T data) {
        return new ApiResponse<>(status, "success", data);
    }

    public static <T> ApiResponse<T> createErrorResponse(HttpStatus status, String message) {
        return new ApiResponse<>(status, message, null);
    }
}
