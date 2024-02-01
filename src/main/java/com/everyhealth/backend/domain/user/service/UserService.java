package com.everyhealth.backend.domain.user.service;

import com.everyhealth.backend.domain.user.domain.User;
import com.everyhealth.backend.domain.user.dto.GoogleUserInfoDTO;
import com.everyhealth.backend.domain.user.dto.response.TokenResponseDTO;
import com.everyhealth.backend.domain.user.helper.UserHelper;
import com.everyhealth.backend.domain.user.repository.UserRepository;
import com.everyhealth.backend.global.config.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenProvider tokenProvider;
    private final UserHelper userHelper;
    
    public TokenResponseDTO googleLogin(Map<String, String> token) {
        GoogleUserInfoDTO userInfoDTO = userHelper.getUserInfo(token.get("accessToken"));
        User user = userHelper.registerUserIfNeed(userInfoDTO);
        userHelper.checkIsMember(user);

        String accessToken = tokenProvider.createAccessToken(user.getId());

        return TokenResponseDTO.from(accessToken);
    }
}
