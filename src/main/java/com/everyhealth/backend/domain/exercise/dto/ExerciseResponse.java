package com.everyhealth.backend.domain.exercise.dto;

import com.everyhealth.backend.domain.exercise.domain.Exercise;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ExerciseResponse {
    private Long id;

    private String name;

    private Integer time;

    private Integer difficulty;

    private String description;

    private String caution;

    private String reference;

    @Builder
    private ExerciseResponse(
            Long id,
            String name,
            Integer time,
            Integer difficulty,
            String description,
            String caution,
            String reference) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.difficulty = difficulty;
        this.description = description;
        this.caution = caution;
        this.reference = reference;
    }

    public static ExerciseResponse from(Exercise exercise) {
        return ExerciseResponse.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .time(exercise.getTime())
                .difficulty(exercise.getDifficulty())
                .description(exercise.getDescription())
                .caution(exercise.getCaution())
                .reference(exercise.getReference())
                .build();
    }
}
