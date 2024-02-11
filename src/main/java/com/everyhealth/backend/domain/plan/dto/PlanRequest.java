package com.everyhealth.backend.domain.plan.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PlanRequest {
    private Long exerciseId;
    private LocalDate date;
    private Integer exerciseTime;

}
