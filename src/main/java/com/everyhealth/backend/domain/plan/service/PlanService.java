package com.everyhealth.backend.domain.plan.service;

import com.everyhealth.backend.domain.exercise.repository.ExerciseRepository;
import com.everyhealth.backend.domain.plan.domain.Plan;
import com.everyhealth.backend.domain.plan.dto.CalendarResponse;
import com.everyhealth.backend.domain.plan.dto.PlanRequest;
import com.everyhealth.backend.domain.plan.dto.PlanResponse;
import com.everyhealth.backend.domain.plan.dto.SaveRecordRequest;
import com.everyhealth.backend.domain.plan.repository.PlanRepository;
import com.everyhealth.backend.domain.user.repository.UserRepository;
import com.everyhealth.backend.global.config.user.UserDetails;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanService {

    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final PlanRepository planRepository;

    // 일정 추가하기
    public void createPlan(UserDetails userDetails, PlanRequest planRequest) {
        Plan plan =
                Plan.of(
                        userDetails.getUser(),
                        exerciseRepository
                                .findById(planRequest.getExerciseId())
                                .orElseThrow(() -> new IllegalArgumentException("운동을 찾을 수 없습니다.")),
                        planRequest.getDate(),
                        planRequest.getPlannedTime());

        planRepository.save(plan);
    }

    // 운동 기록 저장하기
    public void updatePlan(Long planId, SaveRecordRequest saveRecordRequest) {
        Plan plan =
                planRepository
                        .findById(planId)
                        .orElseThrow(() -> new IllegalArgumentException("캘린더 일정을 찾을 수 없습니다."));
        plan.saveDoneTime(saveRecordRequest.getDoneTime());
    }

    // 일정 삭제하기
    public void deletePlan(Long planId) {
        Plan plan =
                planRepository
                        .findById(planId)
                        .orElseThrow(() -> new IllegalArgumentException("캘린더 일정을 찾을 수 없습니다."));
        planRepository.delete(plan);
    }

    // 오늘의 운동 정보 보기
    public CalendarResponse getTodayPlan(UserDetails userDetails) {
        List<PlanResponse> planResponses =
                planRepository.findByUserAndDate(userDetails.getUser(), LocalDate.now()).stream()
                        .map(PlanResponse::fromDetail)
                        .toList();
        return CalendarResponse.of(LocalDate.now(), planResponses);
    }

    // 켈린더 정보 보기
    public List<CalendarResponse> getCalendarPlan(UserDetails userDetails) {
        LocalDate startDate = LocalDate.now().withDayOfMonth(1);
        LocalDate endDate = startDate.plusMonths(1);

        return startDate
                .datesUntil(endDate)
                .map(
                        date -> {
                            List<PlanResponse> planResponses =
                                    planRepository
                                            .findByUserAndDate(userDetails.getUser(), date)
                                            .stream()
                                            .map(PlanResponse::fromPreview)
                                            .collect(Collectors.toList());
                            return CalendarResponse.of(date, planResponses);
                        })
                .toList();
    }
}
