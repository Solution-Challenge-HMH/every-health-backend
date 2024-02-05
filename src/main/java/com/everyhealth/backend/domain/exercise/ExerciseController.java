package com.everyhealth.backend.domain.exercise;

import com.everyhealth.backend.domain.exercise.dto.ExerciseResponse;
import com.everyhealth.backend.domain.exercise.service.ExerciseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    // 오늘의 추천 운동 조회
    @GetMapping("/recommended")
    public ExerciseResponse getRecommendedExercise() {
        return exerciseService.getRecommendedExercise();
    }

}
