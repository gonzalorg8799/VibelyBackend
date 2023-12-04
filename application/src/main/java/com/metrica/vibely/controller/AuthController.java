package com.metrica.vibely.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.data.service.AuthService;
import com.metrica.vibely.model.request.AuthEmailRequest;
import com.metrica.vibely.model.request.AuthUserRequest;

import jakarta.validation.Valid;


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
    @PostMapping("/auth/username")
    public ResponseEntity<?> login(
            @RequestBody
            @Valid
            AuthUserRequest authRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) { return ResponseEntity.badRequest().build(); }

        String apiKey = this.authService.usernameAuth(authRequest.getUsername(), authRequest.getPassword());
        return ResponseEntity.ok()
                .body(java.util.Map.of("apiKey", apiKey));
    } 
    
    @PostMapping("/auth/email")
    public ResponseEntity<?> login(
    		@RequestBody
    		@Valid
    		AuthEmailRequest authRequest,
    		BindingResult bindingResult
    ) {
    	if(bindingResult.hasErrors()) { return ResponseEntity.badRequest().build(); }
    	
    	String apiKey = this.authService.emailAuth(authRequest.getEmail(), authRequest.getPassword());
    	return ResponseEntity.ok().body(Map.of("apiKey", apiKey));
    }

}