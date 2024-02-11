package com.everyhealth.backend.domain.plan;

import com.everyhealth.backend.domain.plan.dto.CalendarResponse;
import com.everyhealth.backend.domain.plan.dto.PlanRequest;
import com.everyhealth.backend.domain.plan.service.PlanService;
import com.everyhealth.backend.global.config.user.UserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    public void createPlan(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody PlanRequest planRequest) {
        log.info("일정 추가하기");
        planService.createPlan(userDetails, planRequest);
    }

    // 일정 삭제하기
    @Operation(summary = "일정 삭제하기")
    @DeleteMapping("/{planId}")
    public void deletePlan(@PathVariable Long planId) {
        log.info("일정 삭제하기");
        planService.deletePlan(planId);
    }

    // 오늘의 운동 정보 보기
    @Operation(summary = "오늘의 운동 정보 보기")
    @GetMapping("/today")
    public CalendarResponse getTodayPlan(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("오늘의 운동 정보 보기");
        return planService.getTodayPlan(userDetails);
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
