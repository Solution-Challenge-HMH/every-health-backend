package com.everyhealth.backend.global.entity;


import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public enum PhysicalAbilityLevel {
    UNABLE_TO_SIT("앉기 불가능"),
    SITTING("앉기 가능"),
    STANDING("서기 가능"),
    WALKING("걷기 가능"),
    RUNNING("달리기 가능");

    private final String name;

}
