package com.everyhealth.backend.domain.plan.repository;

import com.everyhealth.backend.domain.plan.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByUserIdAndDate(Long userId, LocalDate date);
    List<Plan> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
}
