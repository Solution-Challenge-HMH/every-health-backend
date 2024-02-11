package com.everyhealth.backend.domain.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDTO {

    private String accessToken;

    private boolean isMember;

    @Builder
    private LoginResponseDTO(String accessToken, boolean isMember) {
        this.accessToken = accessToken;
        this.isMember = isMember;
    }

    public static LoginResponseDTO of(String accessToken, boolean isMember) {
        return LoginResponseDTO.builder().accessToken(accessToken).isMember(isMember).build();
    }
}
