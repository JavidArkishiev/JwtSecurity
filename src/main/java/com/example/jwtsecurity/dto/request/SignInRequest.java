package com.example.jwtsecurity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class SignInRequest {
    private String email;
    private String password;
}
