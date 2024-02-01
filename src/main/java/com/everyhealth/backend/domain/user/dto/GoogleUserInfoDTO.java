package com.everyhealth.backend.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class GoogleUserInfoDTO {

    private String email;

    public GoogleUserInfoDTO(String email) {
        this.email = email;
    }
}