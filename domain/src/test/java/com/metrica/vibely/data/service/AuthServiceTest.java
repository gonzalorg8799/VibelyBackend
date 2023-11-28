package com.metrica.vibely.data.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.metrica.vibely.data.exception.InvalidCredentialsException;
import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.util.PasswordHasher;

/**
  * @since 2023-11-16
  * @author gonza
  *
  */
@SpringBootTest
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
        testUser.setPassword(PasswordHasher.hash(DEFAULT_PASSWORD));
        testUser.setNickname(DEFAULT_NICKNAME);
        testUser.setEmail   (DEFAULT_EMAIL);
        
        return testUser;
    }
    @Test
    void correctAuthenticationTest() {
        UserDTO testUser = generateTestUser();
        UserDTO testUser2 = generateTestUser();
        assertInstanceOf(String.class, authService.authenticate(testUser.getUsername(), testUser.getPassword()));
        assertNotNull	(authService.authenticate(testUser.getUsername(), testUser.getPassword()));
        assertNotEquals ("",authService.authenticate(testUser.getUsername(), testUser.getPassword()));
        assertNotEquals	(authService.authenticate(testUser.getUsername(), testUser.getPassword()), 
        				 authService.authenticate(testUser2.getUsername(), testUser2.getPassword()));
        
        assertEquals	(authService.authenticate(testUser.getUsername(), testUser.getPassword()),
        			 	 authService.authenticate(testUser.getUsername(), testUser.getPassword()));
        
        
    }
    @Test
    void failAuthenticationTest() {
    	UserDTO testUser = generateTestUser();
    	assertThrows 	(InvalidCredentialsException.class,() -> authService.authenticate("", testUser.getPassword()));
    	assertThrows 	(InvalidCredentialsException.class,() -> authService.authenticate(testUser.getUsername(),""));
    	assertThrows 	(InvalidCredentialsException.class,() -> authService.authenticate("",""));
    }
}
