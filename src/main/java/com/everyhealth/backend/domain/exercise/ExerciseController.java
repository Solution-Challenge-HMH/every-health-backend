package com.everyhealth.backend.domain.exercise;

import com.everyhealth.backend.domain.exercise.dto.ExerciseResponse;
import com.everyhealth.backend.domain.exercise.service.ExerciseService;
import com.everyhealth.backend.global.config.user.UserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Exercise API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @Operation(summary = "추천 운동 목록 조회")
    @GetMapping
    public List<ExerciseResponse> getExerciseList(
            @AuthenticationPrincipal UserDetails userDetails) {
        return exerciseService.getExerciseList(userDetails);
    }

    @Operation(summary = "운동 찜하기")
    @GetMapping("{exerciseId}/bookmark")
    public void addBookmark(
            @AuthenticationPrincipal UserDetails userDetails, @PathVariable Long exerciseId) {
        exerciseService.addBookmark(userDetails, exerciseId);
    }

    @Operation(summary = "운동 찜하기 취소")
    @DeleteMapping("{exerciseId}/bookmark")
    public void deleteBookmark(
            @AuthenticationPrincipal UserDetails userDetails, @PathVariable Long exerciseId) {
        exerciseService.deleteBookmark(userDetails, exerciseId);
    }

    // 오늘의 추천 운동 조회
    @Operation(summary = "오늘의 추천 운동 조회")
    @GetMapping("/recommended")
    public ExerciseResponse getRecommendedExercise(
            @AuthenticationPrincipal UserDetails userDetails) {
        return exerciseService.getRecommendedExercise(userDetails);
    }

    @Operation(summary = "id로 운동 조회")
    @GetMapping("/{exerciseId}")
    public ExerciseResponse getExercise(@PathVariable Long exerciseId) {
        return exerciseService.getExercise(exerciseId);
    }
}
