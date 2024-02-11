package com.everyhealth.backend.domain.user;

import com.everyhealth.backend.domain.user.dto.request.LoginDto;
import com.everyhealth.backend.domain.user.dto.request.UserInfoRequestDTO;
import com.everyhealth.backend.domain.user.dto.response.LoginResponseDTO;
import com.everyhealth.backend.domain.user.service.UserService;
import com.everyhealth.backend.global.config.user.UserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "로그인")
    @PostMapping("login")
    public LoginResponseDTO googleLogin(@RequestBody LoginDto loginDto) {
        return userService.googleLogin(loginDto);
    }

    @Operation(summary = "회원가입 추가 정보 입력")
    @PostMapping("info")
    public void setPhysicalInfo(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UserInfoRequestDTO physicalInfo) {
        userService.setPhysicalInfo(userDetails, physicalInfo);
    }
}
