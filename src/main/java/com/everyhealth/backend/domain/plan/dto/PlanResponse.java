package com.everyhealth.backend.domain.plan.dto;

import com.everyhealth.backend.domain.plan.domain.Plan;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlanResponse {
    private Long planId;
    private Long exerciseId;
    private String exerciseName;
    private Integer plannedTime;
    private Integer doneTime;

    @Builder
    private PlanResponse(
            Long planId,
            Long exerciseId,
            String exerciseName,
            Integer plannedTime,
            Integer doneTime) {
        this.planId = planId;
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.plannedTime = plannedTime;
        this.doneTime = doneTime;
    }

    public static PlanResponse fromDetail(Plan plan) {
        return PlanResponse.builder()
                .planId(plan.getId())
                .exerciseId(plan.getExercise().getId())
                .exerciseName(plan.getExercise().getName())
                .plannedTime(plan.getPlannedTime())
                .doneTime(plan.getDoneTime())
                .build();
    }

    public static PlanResponse fromPreview(Plan plan) {
        return PlanResponse.builder()
                .planId(plan.getId())
                .exerciseId(plan.getExercise().getId())
                .build();
    }
}
