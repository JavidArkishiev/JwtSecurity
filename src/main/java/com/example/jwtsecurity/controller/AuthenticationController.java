package com.example.jwtsecurity.controller;

import com.example.jwtsecurity.dto.request.ChangePasswordRequest;
import com.example.jwtsecurity.dto.request.RefreshTokenRequest;
import com.example.jwtsecurity.dto.request.SignInRequest;
import com.example.jwtsecurity.dto.request.SignUpRequest;
import com.example.jwtsecurity.dto.response.JwtAuthenticationResponse;
import com.example.jwtsecurity.entity.User;
import com.example.jwtsecurity.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signUp")
    public ResponseEntity<User> signUp(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }

    @PostMapping("/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest sign) {
        return ResponseEntity.ok(authenticationService.sigIn(sign));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authenticationService.refreshToken(request));
    }

    @PatchMapping("resetPassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request,
                                                 Principal principal) {
        authenticationService.change(principal, request);
        return ResponseEntity.ok("Success");

    }

}
