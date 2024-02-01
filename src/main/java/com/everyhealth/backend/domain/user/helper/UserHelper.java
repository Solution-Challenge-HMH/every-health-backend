package com.everyhealth.backend.domain.user.helper;

import com.everyhealth.backend.domain.user.domain.User;
import com.everyhealth.backend.domain.user.dto.GoogleUserInfoDTO;
import com.everyhealth.backend.domain.user.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserHelper {

    private final UserRepository userRepository;

    public GoogleUserInfoDTO getUserInfo(String accessToken) {
        String GOOGLE_USERINFO_REQUEST_URL= "https://www.googleapis.com/oauth2/v2/userinfo";
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(headers);
        headers.add("Authorization","Bearer "+accessToken);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                GOOGLE_USERINFO_REQUEST_URL,
                HttpMethod.GET,
                request,
                String.class);

        String responseBody = response.getBody();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBody);
            String email = jsonNode.get("email").asText();
            return new GoogleUserInfoDTO(email);
        } catch (Exception e) {
            //throw TokenValidate.EXCEPTION;
            return null;
        }
    }

    public User registerUserIfNeed(GoogleUserInfoDTO userInfo) {
        // DB에 중복된 이메일 있는지 확인
        String email = userInfo.getEmail();
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isEmpty()) {
            // DB에 정보 등록
            User newUser = User.builder().email(email)
                    .build();
            userRepository.save(newUser);
        }

        return userRepository.findByEmail(email).get();
    }

    public Boolean checkIsMember(User user) {
        return user.getNickname() != null;
    }
}
