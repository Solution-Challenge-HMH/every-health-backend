package com.everyhealth.backend.global.response;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.ContentCachingRequestWrapper;

@RestControllerAdvice
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {
    private final List<String> swaggerPatterns =
            List.of("/v3/api-docs", "/swagger-ui/", "/swagger-ui.html");

    @Override
    public boolean supports(
            MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        HttpServletResponse servletResponse =
                ((ServletServerHttpResponse) response).getServletResponse();
        ContentCachingRequestWrapper servletRequest =
                new ContentCachingRequestWrapper(
                        ((ServletServerHttpRequest) request).getServletRequest());
        String requestPath = servletRequest.getRequestURL().toString();
        boolean isSwaggerRequest = swaggerPatterns.stream().anyMatch(requestPath::contains);

        if (isSwaggerRequest) {
            return body;
        }

        HttpStatus status = HttpStatus.resolve(servletResponse.getStatus());
        if (status == null) {
            return body;
        }
        if (status.is2xxSuccessful()) {
            status = statusProvider(servletRequest.getMethod());
            servletResponse.setStatus(status.value());
            return ApiResponse.createSuccessResponse(status, body);
        }
        return body;
    }

    private HttpStatus statusProvider(String method) {
        if (method.equals("POST")) return HttpStatus.CREATED;
        if (method.equals("DELETE")) return HttpStatus.NO_CONTENT;
        return HttpStatus.OK;
    }
}
