package com.ferhad.unitech.service.impl;

import com.ferhad.unitech.exception.DuplicatePinException;
import com.ferhad.unitech.model.ERole;
import com.ferhad.unitech.model.Role;
import com.ferhad.unitech.model.User;
import com.ferhad.unitech.payload.request.LoginRequest;
import com.ferhad.unitech.payload.request.SignupRequest;
import com.ferhad.unitech.payload.response.JwtResponse;
import com.ferhad.unitech.repository.UserRepository;
import com.ferhad.unitech.security.jwt.JwtUtils;
import com.ferhad.unitech.security.services.UserDetailsImpl;
import com.ferhad.unitech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getPin(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }

    @Override
    public void register(SignupRequest request) {
        if (userRepository.existsByPin(request.getPin())) {
            throw new DuplicatePinException();
        }

        User user = User.builder()
                .pin(request.getPin())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Role defaultRole = Role.builder()
                .name(ERole.ROLE_USER)
                .build();
        Set<Role> roles = new HashSet<>(List.of(defaultRole));
        user.setRoles(roles);
        userRepository.save(user);
    }

}
