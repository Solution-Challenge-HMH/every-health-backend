package com.everyhealth.backend.domain.user.service;

import com.everyhealth.backend.domain.user.domain.PhysicalInfomation;
import com.everyhealth.backend.domain.user.domain.User;
import com.everyhealth.backend.domain.user.dto.GoogleUserInfoDTO;
import com.everyhealth.backend.domain.user.dto.request.UserInfoRequestDTO;
import com.everyhealth.backend.domain.user.dto.response.LoginResponseDTO;
import com.everyhealth.backend.domain.user.helper.UserHelper;
import com.everyhealth.backend.domain.user.repository.PhysicalInfomationRepository;
import com.everyhealth.backend.domain.user.repository.UserRepository;
import com.everyhealth.backend.global.config.jwt.TokenProvider;
import com.everyhealth.backend.global.config.user.UserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final PhysicalInfomationRepository physicalInfomationRepository;
    private final UserHelper userHelper;
    
    public LoginResponseDTO googleLogin(Map<String, String> token) {
        GoogleUserInfoDTO userInfoDTO = userHelper.getUserInfo(token.get("accessToken"));
        User user = userHelper.registerUserIfNeed(userInfoDTO);
        boolean isMember = userHelper.checkIsMember(user);
        String accessToken = tokenProvider.createAccessToken(user.getId());

        return LoginResponseDTO.of(accessToken, isMember);
    }

    public void setPhysicalInfo(
            UserDetails userDetails,
            UserInfoRequestDTO userInfo) {
        User user = userDetails.getUser();
        user.updateInfo(userInfo);
        userRepository.save(user);
        PhysicalInfomation userPhysicalInfo = PhysicalInfomation.of(user, userInfo);
        try {
            physicalInfomationRepository.save(userPhysicalInfo);
        } catch(Exception e) {
            throw new DuplicateKeyException("이미 저장한 건강 정보입니다.");
        }
    }
}
