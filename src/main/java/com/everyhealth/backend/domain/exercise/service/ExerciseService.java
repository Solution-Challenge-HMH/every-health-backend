package com.everyhealth.backend.domain.exercise.service;

import com.everyhealth.backend.domain.exercise.domain.Exercise;
import com.everyhealth.backend.domain.exercise.repository.ExerciseRepository;
import com.everyhealth.backend.domain.user.domain.User;
import com.everyhealth.backend.domain.user.repository.UserRepository;
import com.everyhealth.backend.global.config.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;

    public void getRecommendedExercise() {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        // TODO: 사용자의 신체 능력치를 기반으로 오늘의 운동 추천
    }
}
