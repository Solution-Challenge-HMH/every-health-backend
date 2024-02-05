package com.everyhealth.backend.domain.exercise.domain;

import com.everyhealth.backend.global.entity.PhysicalAbilityLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "EXERCISE")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long id;

    @NotNull
    @Size(max = 30)
    private String name;

    private Integer time;

    private Integer difficulty;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String caution;

    private String reference;

    /*
     운동 능력에 따른 가능 여부
     */
    @Enumerated(EnumType.STRING)
    private PhysicalAbilityLevel physicalAbilityLevel;

    private boolean core;

    private boolean upperArm;

    private boolean lowerArm;

    private boolean upperLeg;

    private boolean lowerLeg;

}
