package com.ferhad.unitech.service;

import com.ferhad.unitech.exception.DuplicatePinException;
import com.ferhad.unitech.model.User;
import com.ferhad.unitech.payload.request.SignupRequest;
import com.ferhad.unitech.repository.UserRepository;
import com.ferhad.unitech.security.jwt.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @MockBean
    private UserRepository  userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("User Registration")
    void successfulRegistration() {
        SignupRequest request = SignupRequest.builder()
                .pin("11R34PO")
                .password("SecurePassword1")
                .build();

        User user = User.builder()
                .pin(request.getPin())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Mockito.when(userRepository.save(user)).thenReturn(user);
        User registeredUser = authService.register(request);
        assertEquals("11R34PO", registeredUser.getPin());
    }

    @Test
    @DisplayName("User Registration with duplicate pin")
    void unsuccessfulRegistration_WhenDuplicatePinHappens() {
        SignupRequest request = SignupRequest.builder()
                .pin("11R34PO")
                .password("SecurePassword1")
                .build();

        User user = User.builder()
                .pin(request.getPin())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Mockito.when(userRepository.existsByPin(user.getPin())).thenReturn(true);
        assertThrows(DuplicatePinException.class,
                () -> {
                    authService.register(request);
                });
    }
}
