package com.ferhad.unitech.security.services;

import com.ferhad.unitech.model.User;
import com.ferhad.unitech.repository.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByPin(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with pin: " + username));
        return UserDetailsImpl.build(user);
    }

}
