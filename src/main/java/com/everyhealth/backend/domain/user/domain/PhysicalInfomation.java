package com.everyhealth.backend.domain.user.domain;

import com.everyhealth.backend.global.entity.PhysicalAbilityLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PhysicalInfomation {
    @Id
    @GeneratedValue
    @Column(name = "physical_infomation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PhysicalAbilityLevel physicalAbilityLevel;

    private boolean core;

    private boolean rightUpperArm;

    private boolean rightLowerArm;

    private boolean leftUpperArm;

    private boolean leftLowerArm;

    private boolean rightUpperLeg;

    private boolean rightLowerLeg;

    private boolean leftUpperLeg;

    private boolean leftLowerLeg;
}
