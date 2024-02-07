package com.everyhealth.backend.global.entity;


public enum PhysicalAbilityLevel {
    UNABLE_TO_SIT("Unable to sit"),
    ABLE_TO_SIT("Able to sit"),
    ABLE_TO_STAND("Able to stand"),
    ABLE_TO_WALK("Able to walk"),
    ABLE_TO_RUN("Able to run");

    private final String name;

    PhysicalAbilityLevel(String name) {
        this.name = name;
    }

}
