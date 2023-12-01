package com.metrica.vibely.controller;

import com.metrica.vibely.data.service.AuthService;
import com.metrica.vibely.model.request.AuthUserRequest;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <h1>Authentication Controller</h1>
 * 
 * @since 2023-11-14
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class AuthController {

    // <<-FIELD->>
    private AuthService authService;

    // <<-CONSTRUCTOR->>
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // <<-METHOD->>
    @PostMapping("/auth")
    public ResponseEntity<?> login(
            @RequestBody
            @Valid
            AuthUserRequest authRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        String apiKey = this.authService.authenticate(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok()
                .body(java.util.Map.of("apiKey", apiKey));
    }

}