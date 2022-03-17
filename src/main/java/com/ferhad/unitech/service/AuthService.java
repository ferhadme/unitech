package com.ferhad.unitech.service;

import com.ferhad.unitech.model.User;
import com.ferhad.unitech.payload.request.LoginRequest;
import com.ferhad.unitech.payload.request.SignupRequest;
import com.ferhad.unitech.payload.response.JwtResponse;

public interface AuthService {
    JwtResponse login(LoginRequest request);
    User register(SignupRequest request);
}
