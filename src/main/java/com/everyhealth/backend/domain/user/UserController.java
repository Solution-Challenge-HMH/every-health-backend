package com.everyhealth.backend.domain.user;

import com.everyhealth.backend.domain.user.dto.request.PhysicalInfomationRequestDTO;
import com.everyhealth.backend.domain.user.dto.response.LoginResponseDTO;
import com.everyhealth.backend.domain.user.service.UserService;
import com.everyhealth.backend.global.config.user.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public LoginResponseDTO googleLogin(@RequestBody Map<String, String> token) {
        return userService.googleLogin(token);
    }

    @PostMapping("info")
    public void setPhysicalInfo(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody PhysicalInfomationRequestDTO physicalInfo) {
        userService.setPhysicalInfo(userDetails, physicalInfo);
    }
}
