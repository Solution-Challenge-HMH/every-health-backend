package com.everyhealth.backend.domain.user.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Gender {
    F("Female"),
    M("Male"),
    N("None");

    private final String gender;
}
