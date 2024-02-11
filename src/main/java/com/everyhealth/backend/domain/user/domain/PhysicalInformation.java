package com.everyhealth.backend.domain.user.domain;

import com.everyhealth.backend.domain.user.dto.request.UserInfoRequestDTO;
import com.everyhealth.backend.global.entity.PhysicalAbilityLevel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class PhysicalInformation {
    @Id
    @GeneratedValue
    @Column(name = "physical_infomation_id")
    private Long id;

    @OneToOne
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

    // 생성자
    @Builder
    private PhysicalInformation(
            User user,
            PhysicalAbilityLevel physicalAbilityLevel,
            boolean core,
            boolean rightUpperArm,
            boolean rightLowerArm,
            boolean leftUpperArm,
            boolean leftLowerArm,
            boolean rightUpperLeg,
            boolean rightLowerLeg,
            boolean leftUpperLeg,
            boolean leftLowerLeg) {
        this.user = user;
        this.physicalAbilityLevel = physicalAbilityLevel;
        this.core = core;
        this.rightUpperArm = rightUpperArm;
        this.rightLowerArm = rightLowerArm;
        this.leftUpperArm = leftUpperArm;
        this.leftLowerArm = leftLowerArm;
        this.rightUpperLeg = rightUpperLeg;
        this.rightLowerLeg = rightLowerLeg;
        this.leftUpperLeg = leftUpperLeg;
        this.leftLowerLeg = leftLowerLeg;
    }

    public static PhysicalInformation of(User user, UserInfoRequestDTO userInfo) {
        return PhysicalInformation.builder()
                .user(user)
                .physicalAbilityLevel(userInfo.getPhysicalAbilityLevel())
                .core(userInfo.isCore())
                .rightUpperArm(userInfo.isRightUpperArm())
                .rightLowerArm(userInfo.isRightLowerArm())
                .leftUpperArm(userInfo.isLeftUpperArm())
                .leftLowerArm(userInfo.isLeftLowerArm())
                .rightUpperLeg(userInfo.isRightUpperLeg())
                .rightLowerLeg(userInfo.isRightLowerLeg())
                .leftUpperLeg(userInfo.isLeftUpperLeg())
                .leftLowerLeg(userInfo.isLeftLowerLeg())
                .build();
    }

    public void updatePhysicalInfo(UserInfoRequestDTO physicalInfo) {
        this.physicalAbilityLevel = physicalInfo.getPhysicalAbilityLevel();
        this.core = physicalInfo.isCore();
        this.rightUpperArm = physicalInfo.isRightUpperArm();
        this.rightLowerArm = physicalInfo.isRightLowerArm();
        this.leftUpperArm = physicalInfo.isLeftUpperArm();
        this.leftLowerArm = physicalInfo.isLeftLowerArm();
        this.rightUpperLeg = physicalInfo.isRightUpperLeg();
        this.rightLowerLeg = physicalInfo.isRightLowerLeg();
        this.leftUpperLeg = physicalInfo.isLeftUpperLeg();
        this.leftLowerLeg = physicalInfo.isLeftLowerLeg();
    }
}
