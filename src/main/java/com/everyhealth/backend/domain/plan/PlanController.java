package com.everyhealth.backend.domain.plan;

import com.everyhealth.backend.domain.plan.dto.CalendarResponse;
import com.everyhealth.backend.domain.plan.dto.PlanRequest;
import com.everyhealth.backend.domain.plan.dto.PlanResponse;
import com.everyhealth.backend.domain.plan.dto.SaveRecordRequest;
import com.everyhealth.backend.domain.plan.service.PlanService;
import com.everyhealth.backend.global.config.user.UserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Plan API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/plan")
public class PlanController {

    private final PlanService planService;

    // 일정 추가하기
    @Operation(summary = "일정 추가하기")
    @PostMapping
    public PlanResponse createPlan(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody PlanRequest planRequest) {
        log.info("일정 추가하기");
        return planService.createPlan(userDetails, planRequest);
    }

    // 운동 기록 저장하기
    @Operation(summary = "운동 기록 저장하기")
    @PatchMapping("/{planId}")
    public void updatePlan(
            @PathVariable Long planId, @RequestBody SaveRecordRequest saveRecordRequest) {
        log.info("운동 기록 저장하기");
        planService.updatePlan(planId, saveRecordRequest);
    }

    // 일정 삭제하기
    @Operation(summary = "일정 삭제하기")
    @DeleteMapping("/{planId}")
    public void deletePlan(@PathVariable Long planId) {
        log.info("일정 삭제하기");
        planService.deletePlan(planId);
    }

    // 오늘의 운동 정보 보기
    @Operation(summary = "날짜별 운동 정보 보기")
    @GetMapping
    public CalendarResponse getDayPlan(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) LocalDate date) {
        log.info("오늘의 운동 정보 보기");
        if (date == null) {
            date = LocalDate.now();
        }
        return planService.getDayPlan(userDetails, date);
    }

    // 캘린더 일정 조회
    @Operation(summary = "캘린더 일정 조회")
    @GetMapping("/calendar")
    public List<CalendarResponse> getCalendarPlan(
            @AuthenticationPrincipal UserDetails userDetails) {
        log.info("캘린더 일정 조회");
        return planService.getCalendarPlan(userDetails);
    }
}
