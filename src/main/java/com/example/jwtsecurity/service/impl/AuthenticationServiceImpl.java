package com.example.jwtsecurity.service.impl;

import com.example.jwtsecurity.dto.request.RefreshTokenRequest;
import com.example.jwtsecurity.dto.request.SignInRequest;
import com.example.jwtsecurity.dto.request.SignUpRequest;
import com.example.jwtsecurity.dto.response.JwtAuthenticationResponse;
import com.example.jwtsecurity.entity.User;
import com.example.jwtsecurity.enums.Role;
import com.example.jwtsecurity.repository.UserRepository;
import com.example.jwtsecurity.service.AuthenticationService;
import com.example.jwtsecurity.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    @Override
    public User signUp(SignUpRequest signUpRequest) {
//        User user = new User();
//        user.setFirstName(signUpRequest.getFirstName());
//        user.setSecondName(signUpRequest.getLastName());
//        user.setEmail(signUpRequest.getEmail());
//        user.setRole(Role.USER);
//        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        //comete aldigim her shey ishleyir sadece builderi sinayirdim
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("this email already exist");
        }

        var user = User.builder().firstName(signUpRequest.getFirstName()).secondName(signUpRequest.getLastName())
                .email(signUpRequest.getEmail()).role(Role.USER)
                .password(passwordEncoder.encode(signUpRequest.getPassword())).build();
        userRepository.save(user);
        return user;
    }

    public JwtAuthenticationResponse sigIn(SignInRequest sign) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(sign.getEmail(),
                sign.getPassword()));
        var user = userRepository.findByEmail(sign.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
//        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
//        jwtAuthenticationResponse.setToken(jwt);
//        jwtAuthenticationResponse.setRefreshToken(refreshToken);
//        return jwtAuthenticationResponse;
////        comete aldigim her shey ishleyir sadece builderi sinayirdim

        return JwtAuthenticationResponse.builder().token(jwt).refreshToken(refreshToken).build();

    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest request) {
        String userEmail = jwtService.extractUsername(request.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(request.getToken(), user)) {
            var jwt = jwtService.generateToken(user);

            return JwtAuthenticationResponse.builder().token(jwt).refreshToken(request.getToken()).build();
        }
        return null;

    }

}
