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

    private boolean isBookmarked;

    @Builder
    private ExerciseResponse(
            Long id,
            String name,
            Integer time,
            Integer difficulty,
            String description,
            String caution,
            String reference,
            boolean isBookmarked) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.difficulty = difficulty;
        this.description = description;
        this.caution = caution;
        this.reference = reference;
        this.isBookmarked = isBookmarked;
    }

    public static ExerciseResponse ofList(Exercise exercise, boolean isBookmarked) {
        return ExerciseResponse.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .time(exercise.getTime())
                .isBookmarked(isBookmarked)
                .build();
    }

    public static ExerciseResponse fromSimple(Exercise exercise) {
        return ExerciseResponse.builder()
                .id(exercise.getId())
                .name(exercise.getName())
                .time(exercise.getTime())
                .difficulty(exercise.getDifficulty())
                .build();
    }

    public static ExerciseResponse fromDetail(Exercise exercise) {
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
