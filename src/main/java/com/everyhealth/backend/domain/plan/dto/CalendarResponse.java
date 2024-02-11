package com.everyhealth.backend.domain.plan.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

public class CalendarResponse {
    private LocalDate date;
    private List<PlanResponse> planList;

    @Builder
    private CalendarResponse(LocalDate date, List<PlanResponse> planList) {
        this.date = date;
        this.planList = planList;
    }

    public static CalendarResponse of(LocalDate date, List<PlanResponse> planList) {
        return CalendarResponse.builder().date(date).planList(planList).build();
    }
}
