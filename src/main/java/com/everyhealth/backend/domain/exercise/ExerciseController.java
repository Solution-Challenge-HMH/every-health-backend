package com.everyhealth.backend.domain.exercise;

import com.everyhealth.backend.domain.exercise.dto.ExerciseResponse;
import com.everyhealth.backend.domain.exercise.service.ExerciseService;
import com.everyhealth.backend.global.config.user.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseResponse> getExerciseList() {
        return exerciseService.getExerciseList();
    }

    @GetMapping("{exerciseId}/bookmark")
    public void addBookmark(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long exerciseId) {
        exerciseService.addBookmark(userDetails, exerciseId);
    }

    @DeleteMapping("{exerciseId}/bookmark")
    public void deleteBookmark(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long exerciseId) {
        exerciseService.deleteBookmark(userDetails, exerciseId);
    }

    // 오늘의 추천 운동 조회
    @GetMapping("/recommended")
    public ExerciseResponse getRecommendedExercise() {
        return exerciseService.getRecommendedExercise();
    }

}
