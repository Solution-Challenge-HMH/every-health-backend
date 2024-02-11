package com.everyhealth.backend.domain.plan.repository;

import com.everyhealth.backend.domain.plan.domain.Plan;
import com.everyhealth.backend.domain.user.domain.User;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByUserAndDate(User user, LocalDate date);
}
