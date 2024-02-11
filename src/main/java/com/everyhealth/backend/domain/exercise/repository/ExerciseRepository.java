package com.everyhealth.backend.domain.exercise.repository;

import com.everyhealth.backend.domain.exercise.domain.Exercise;
import com.everyhealth.backend.global.entity.PhysicalAbilityLevel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    // TODO: PhysicalAbilityLevel에 따른 운동 리스트 조회를 특정 레벨에 따른 운동만 보여줄건지, 또는 특정 레벨 이하의 운동을 모두 보여줄건지 결정해야
    List<Exercise> findByPhysicalAbilityLevel(PhysicalAbilityLevel physicalAbilityLevel);
}
