package com.metrica.vibely.data.util;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>Password Hasher Tests</h1>
 * 
 * @since 2023-11-18
 * @version 1.0
 * @author Alex
 */
public class PasswordHasherTest {
    
    // <<-CONSTANT->>
    private static final String PASSWORD = "password";
    
    /**
     * Checks that the same password generate two different hashes
     */
    @Test
    void differentHashesTest() {
        String hashedPassword1 = PasswordHasher.hash(PASSWORD);
        String hashedPassword2 = PasswordHasher.hash(PASSWORD);
        
        assertNotEquals(hashedPassword1, hashedPassword2);
    }
    
    /**
     * Even though a given password generates different hashes
     * we are able to check if a raw password matches with the a hashed password
     */
    @Test
    void matchesTest() {
        String hashedPassword1 = PasswordHasher.hash(PASSWORD);
        String hashedPassword2 = PasswordHasher.hash(PASSWORD);
        
        assertTrue(PasswordHasher.matches(PASSWORD, hashedPassword1));
        assertTrue(PasswordHasher.matches(PASSWORD, hashedPassword2));
    }
    
    /**
     * Checks that if we hash a password and then hash the result of the first hash
     * we must not get the rawPassword again
     */
    @RepeatedTest(5)
    void oneWayHashingTest() {
        String firstTime  = PasswordHasher.hash(PASSWORD);
        String secondTime = PasswordHasher.hash(firstTime);
        
        assertNotEquals(PASSWORD, secondTime);
    }

}
