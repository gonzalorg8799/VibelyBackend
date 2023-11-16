package com.metrica.vibely.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.util.PasswordHashing;

/**
  * @since 2023-11-16
  * @author gonza
  *
  */
public class AuthServiceTest {

    // <<-CONSTANTS->>
    private static final String DEFAULT_USERNAME = "jdoe";
    private static final String DEFAULT_PASSWORD = "jdoe";
    private static final String DEFAULT_NICKNAME = "John Doe";
    private static final String DEFAULT_EMAIL    = "johndoe@email.com";

    // <<-FIELD->>
    private AuthService authService;
    
    // <<-CONSTRUCTOR->>
    @Autowired
    public AuthServiceTest(AuthService authService) {
        this.authService = authService;
    }
    
    // <<-METHODS->>
    private UserDTO generateTestUser() {
        UserDTO testUser = new UserDTO();
        
        testUser.setUsername(DEFAULT_USERNAME);
        testUser.setPassword(PasswordHashing.hash(DEFAULT_PASSWORD));
        testUser.setNickname(DEFAULT_NICKNAME);
        testUser.setEmail   (DEFAULT_EMAIL);
        
        return testUser;
    }
    @Test
    void correctAuthenticationTest() {
        UserDTO testUser = generateTestUser();
        UserDTO testUser2 = generateTestUser();
        assertNotNull(authService.authenticate(testUser.getUsername(), testUser.getPassword()));
        assertFalse(authService.authenticate(testUser.getUsername(), testUser.getPassword())==authService.authenticate(testUser2.getUsername(), testUser2.getPassword()));
        assertFalse(""==authService.authenticate(testUser.getUsername(), testUser.getPassword()));
        
    }
    @Test
    void failAuthenticationTest() {
    	
    }
}
