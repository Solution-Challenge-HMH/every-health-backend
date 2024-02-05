package com.everyhealth.backend.domain.exercise.service;

import com.everyhealth.backend.domain.exercise.domain.Exercise;
import com.everyhealth.backend.domain.exercise.dto.ExerciseResponse;
import com.everyhealth.backend.domain.exercise.repository.ExerciseRepository;
import com.everyhealth.backend.domain.user.domain.PhysicalInfomation;
import com.everyhealth.backend.domain.user.domain.User;
import com.everyhealth.backend.domain.user.repository.UserRepository;
import com.everyhealth.backend.global.config.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;


    // 오늘의 추천 운동 조회
    @Transactional(readOnly = true)
    public ExerciseResponse getRecommendedExercise() {
        User user = userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        List<Exercise> exerciseList = exerciseRepository.findByPhysicalAbilityLevel(user.getPhysicalInfomation().getPhysicalAbilityLevel()).stream()
                .filter(exercise -> matchesUserPhysicalInformation(exercise, user.getPhysicalInfomation()))
                .collect(Collectors.toList());
        // 랜덤 운동 추천
        Collections.shuffle(exerciseList);
        return exerciseList.stream().findFirst().map(ExerciseResponse::from).orElseThrow(() -> new IllegalArgumentException("추천 운동을 찾을 수 없습니다."));
    }


    private boolean matchesUserPhysicalInformation(Exercise exercise, PhysicalInfomation physicalInformation) {
        return exercise.isCore() == physicalInformation.isCore() &&
                exercise.isUpperArm() == physicalInformation.isRightUpperArm() &&
                exercise.isLowerArm() == physicalInformation.isRightLowerArm() &&
                exercise.isUpperLeg() == physicalInformation.isRightUpperLeg() &&
                exercise.isLowerLeg() == physicalInformation.isRightLowerLeg();
    }

}
