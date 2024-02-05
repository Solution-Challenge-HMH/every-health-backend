package com.everyhealth.backend.domain.exercise.repository;

import com.everyhealth.backend.domain.exercise.domain.Exercise;
import com.everyhealth.backend.global.entity.PhysicalAbilityLevel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long>{

    List<Exercise> findByPhysicalAbilityLevel(PhysicalAbilityLevel physicalAbilityLevel);
}
