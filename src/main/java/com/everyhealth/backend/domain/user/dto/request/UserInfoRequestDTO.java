package com.everyhealth.backend.domain.user.dto.request;

import com.everyhealth.backend.domain.user.domain.Gender;
import com.everyhealth.backend.global.entity.PhysicalAbilityLevel;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoRequestDTO {

    private String nickname;

    private Gender gender;

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
