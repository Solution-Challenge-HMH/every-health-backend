package com.everyhealth.backend.global.config.user;


import com.everyhealth.backend.domain.user.domain.User;
import com.everyhealth.backend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsService {
    private final UserRepository userRepository;

    @Transactional
    public UserDetails loadUserByUsername(Long id) throws UsernameNotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id + " -> 존재하지 않는 사용자"));
        return createUser(id, user);
    }

    private UserDetails createUser(Long id, User user) {
        return new UserDetails(user);
    }
}
