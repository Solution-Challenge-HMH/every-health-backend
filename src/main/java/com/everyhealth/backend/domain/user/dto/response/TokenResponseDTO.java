package com.everyhealth.backend.domain.user.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenResponseDTO {

    private String accessToken;

    @Builder
    private TokenResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public static TokenResponseDTO from(String accessToken) {
        return TokenResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }
}
