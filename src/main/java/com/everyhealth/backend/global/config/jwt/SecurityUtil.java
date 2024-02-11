package com.everyhealth.backend.global.config.jwt;

import com.everyhealth.backend.global.config.user.UserDetails;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    // 로그인한 유저의 id 리턴
    public static Long getCurrentUserId() {
        final Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        try {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUserId();
        } catch (Exception e) {
            throw new BadCredentialsException("Unauthenticated user");
        }
    }
}
