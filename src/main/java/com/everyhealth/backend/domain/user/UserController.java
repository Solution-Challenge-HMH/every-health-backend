package com.everyhealth.backend.domain.user;

import com.everyhealth.backend.domain.user.dto.response.TokenResponseDTO;
import com.everyhealth.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    public TokenResponseDTO googleLogin(@RequestBody Map<String, String> token) {
        return userService.googleLogin(token);
    }

}
