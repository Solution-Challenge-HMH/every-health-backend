package com.everyhealth.backend.domain.plan.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class PlanRequest {
    private Long exerciseId;
    private LocalDate date;
    private Integer exerciseTime;
}
