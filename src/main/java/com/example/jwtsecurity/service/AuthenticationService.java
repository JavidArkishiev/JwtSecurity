package com.example.jwtsecurity.service;

import com.example.jwtsecurity.dto.request.ChangePasswordRequest;
import com.example.jwtsecurity.dto.request.RefreshTokenRequest;
import com.example.jwtsecurity.dto.request.SignInRequest;
import com.example.jwtsecurity.dto.request.SignUpRequest;
import com.example.jwtsecurity.dto.response.JwtAuthenticationResponse;
import com.example.jwtsecurity.entity.User;

import java.security.Principal;

public interface AuthenticationService {
    User signUp(SignUpRequest signUpRequest);

    JwtAuthenticationResponse sigIn(SignInRequest sign);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest request);

    void change(Principal principal, ChangePasswordRequest request);
}
