package com.example.jwtsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/kop")
@RequiredArgsConstructor
@EnableMethodSecurity
public class CustomerController {
   @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("admin")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hi i am admin");
    }

    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @GetMapping("user")
    public ResponseEntity<String> sayHello1(){
        return ResponseEntity.ok("Hi i am user");
    }
}
