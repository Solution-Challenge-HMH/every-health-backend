package com.everyhealth.backend.domain.plan.dto;

import com.everyhealth.backend.domain.plan.domain.Plan;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PlanResponse {
    private Long planId;
    private LocalDate date;
    private Long exerciseId;
    private String exerciseName;
    private String exerciseDescription;
    private Integer exerciseTime;

    @Builder
    private PlanResponse(
            Long planId,
            LocalDate date,
            Long exerciseId,
            String exerciseName,
            String exerciseDescription,
            Integer exerciseTime) {
        this.planId = planId;
        this.date = date;
        this.exerciseId = exerciseId;
        this.exerciseName = exerciseName;
        this.exerciseDescription = exerciseDescription;
        this.exerciseTime = exerciseTime;
    }

    public static PlanResponse fromDetail(Plan plan) {
        return PlanResponse.builder()
                .planId(plan.getId())
                .exerciseId(plan.getExercise().getId())
                .exerciseName(plan.getExercise().getName())
                .exerciseDescription(plan.getExercise().getDescription())
                .exerciseTime(plan.getExerciseTime())
                .build();
    }

    public static PlanResponse fromPreview(Plan plan) {
        return PlanResponse.builder()
                .planId(plan.getId())
                .date(plan.getDate())
                .exerciseId(plan.getExercise().getId())
                .build();
    }
}
