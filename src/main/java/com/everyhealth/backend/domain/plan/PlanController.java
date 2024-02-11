package com.everyhealth.backend.domain.plan;

import com.everyhealth.backend.domain.plan.dto.CalendarResponse;
import com.everyhealth.backend.domain.plan.dto.PlanRequest;
import com.everyhealth.backend.domain.plan.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void createPlan(@RequestBody PlanRequest planRequest) {
        log.info("일정 추가하기");
        planService.createPlan(planRequest);
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
    public CalendarResponse getTodayPlan() {
        log.info("오늘의 운동 정보 보기");
        return planService.getTodayPlan();
    }


    // 캘린더 일정 조회
    @Operation(summary = "캘린더 일정 조회")
    @GetMapping("/calendar")
    public List<CalendarResponse> getCalendarPlan() {
        log.info("캘린더 일정 조회");
        return planService.getCalendarPlan();
    }

}
