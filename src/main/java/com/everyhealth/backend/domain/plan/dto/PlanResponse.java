package com.everyhealth.backend.domain.plan.dto;

import com.everyhealth.backend.domain.plan.domain.Plan;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlanResponse {
    private Long planId;
    private Long exerciseId;
    private String exerciseName;
    private String exerciseDescription;
    private Integer plannedTime;
    private Integer doneTime;

    @Builder
    private PlanResponse(
            Long planId,
            Long exerciseId,
            String exerciseName,
            String exerciseDescription,
            Integer plannedTime,
            Integer doneTime) {
        this.planId = planId;
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.plannedTime = plannedTime;
        this.doneTime = doneTime;
    }

    public static PlanResponse fromDetail(Plan plan) {
        return PlanResponse.builder()
                .planId(plan.getId())
                .exerciseId(plan.getExercise().getId())
                .exerciseName(plan.getExercise().getName())
                .exerciseDescription(plan.getExercise().getDescription())
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
