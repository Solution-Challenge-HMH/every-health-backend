package com.everyhealth.backend.domain.plan.service;

import com.everyhealth.backend.domain.plan.domain.Plan;
import com.everyhealth.backend.domain.plan.dto.PlanRequest;
import com.everyhealth.backend.domain.plan.dto.CalendarResponse;
import com.everyhealth.backend.domain.plan.dto.PlanResponse;
import com.everyhealth.backend.domain.plan.repository.PlanRepository;
import com.everyhealth.backend.domain.exercise.repository.ExerciseRepository;
import com.everyhealth.backend.domain.user.repository.UserRepository;
import com.everyhealth.backend.global.config.jwt.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final PlanRepository planRepository;


    // 일정 추가하기
    public void createPlan(PlanRequest planRequest) {
        Plan plan = Plan.of(
                userRepository.findById(SecurityUtil.getCurrentUserId()).orElseThrow(
                        () -> new IllegalArgumentException("사용자를 찾을 수 없습니다.")
                ),
                exerciseRepository.findById(planRequest.getExerciseId()).orElseThrow(
                        () -> new IllegalArgumentException("운동을 찾을 수 없습니다.")
                ),
                planRequest.getDate(),
                planRequest.getExerciseTime()

        );
        planRepository.save(plan);
    }


    // 일정 삭제하기
    public void deletePlan(Long planId) {
        Plan plan = planRepository.findById(planId).orElseThrow(
                () -> new IllegalArgumentException("캘린더 일정을 찾을 수 없습니다.")
        );
        planRepository.delete(plan);
    }


    // 오늘의 운동 정보 보기
    public CalendarResponse getTodayPlan() {
        List<PlanResponse> planResponses = planRepository.findByUserIdAndDate(
                SecurityUtil.getCurrentUserId(),
                LocalDate.now()
        ).stream().map(PlanResponse::fromDetail).toList();
        return CalendarResponse.of(LocalDate.now(), planResponses);
    }


    // 켈린더 정보 보기
    public List<CalendarResponse> getCalendarPlan() {
        List<PlanResponse> planResponses = planRepository.findByUserIdAndDateBetween( // 오늘 날짜 기준 같은 달의 plan list
                SecurityUtil.getCurrentUserId(),
                LocalDate.now().withDayOfMonth(1), // 이번 달의 1일
                LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()) // 이번 달의 마지막 날
        ).stream().map(PlanResponse::fromPreview).toList();
        return planResponses.stream()
                .map(planResponse -> CalendarResponse.of(planResponse.getDate(), planResponses))
                .toList();
    }
}
