package com.everyhealth.backend.domain.exercise.service;

import com.everyhealth.backend.domain.exercise.domain.Bookmark;
import com.everyhealth.backend.domain.exercise.domain.Exercise;
import com.everyhealth.backend.domain.exercise.dto.ExerciseResponse;
import com.everyhealth.backend.domain.exercise.repository.BookmarkRepository;
import com.everyhealth.backend.domain.exercise.repository.ExerciseRepository;
import com.everyhealth.backend.domain.user.domain.PhysicalInfomation;
import com.everyhealth.backend.domain.user.domain.User;
import com.everyhealth.backend.domain.user.repository.UserRepository;
import com.everyhealth.backend.global.config.jwt.SecurityUtil;
import com.everyhealth.backend.global.config.user.UserDetails;
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
    private final BookmarkRepository bookmarkRepository;
    private final UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<ExerciseResponse> getExerciseList(UserDetails userDetails) {
        User user = userDetails.getUser();

        List<Exercise> exerciseList = exerciseRepository.findByPhysicalAbilityLevel(user.getPhysicalInfomation().getPhysicalAbilityLevel()).stream()
                .filter(exercise -> matchesUserPhysicalInformation(exercise, user.getPhysicalInfomation())).toList();

        return exerciseList
                .stream()
                .map(ExerciseResponse::from)
                .toList();
    }

    public void addBookmark(UserDetails userDetails, Long exerciseId) {
        User user = userDetails.getUser();
        Exercise exercise = exerciseRepository.findById(exerciseId).get();
        Bookmark bookmark = Bookmark.of(user, exercise);
        bookmarkRepository.save(bookmark);
    }

    public void deleteBookmark(UserDetails userDetails, Long exerciseId) {
        User user = userDetails.getUser();
        Bookmark bookmark = bookmarkRepository.findByUserAndId(user, exerciseId).get();
        bookmarkRepository.delete(bookmark);
    }

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
        return exerciseMatchesPhysicalInfo(exercise.isCore(), physicalInformation.isCore(), physicalInformation.isCore())
                && exerciseMatchesPhysicalInfo(exercise.isUpperArm(), physicalInformation.isRightUpperArm(), physicalInformation.isLeftUpperArm())
                && exerciseMatchesPhysicalInfo(exercise.isLowerArm(), physicalInformation.isRightLowerArm(), physicalInformation.isLeftLowerArm())
                && exerciseMatchesPhysicalInfo(exercise.isUpperLeg(), physicalInformation.isRightUpperLeg(), physicalInformation.isLeftUpperLeg())
                && exerciseMatchesPhysicalInfo(exercise.isLowerLeg(), physicalInformation.isRightLowerLeg(), physicalInformation.isLeftLowerLeg());
    }

    private boolean exerciseMatchesPhysicalInfo(boolean exerciseRequirement, boolean leftPhysicalInfo, boolean rightPhysicalInfo) {
        // 운동의 신체 능력치가 false인 경우에는 사용자의 신체 능력치 상관 없음
        if (!exerciseRequirement) {
            return true;
        }
        // 운동의 신체 능력치가 true이면 해당 부위의 사용자 능력치도 true여야 함
        return leftPhysicalInfo && rightPhysicalInfo;
    }

}
