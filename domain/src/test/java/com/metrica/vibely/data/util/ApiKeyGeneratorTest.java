package com.metrica.vibely.data.util;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * <h1>API Key Generator Test</h1>
 * 
 * @since 2023-11-29
 * @version 1.0
 * @author Alex
 */
class ApiKeyGeneratorTest {
    
    // <<-CONSTANT->>
    private static final UUID USER_ID = UUID.fromString("");

    @RepeatedTest(10)
    void generationTest() {
        String apiKey1 = ApiKeyManager.generate(USER_ID);
        String apiKey2 = ApiKeyManager.generate(USER_ID);
        assertNotEquals(apiKey1, apiKey2);
    }

    @Test
    void structureTest() {
        String apiKey = "apiKey";
        assertThrows(IllegalArgumentException.class, () -> ApiKeyManager.getExpirationTime(apiKey));
    }

    @Test
    void timeExpirationTrueTest() {
        String apiKey = ApiKeyManager.generate(USER_ID);

        long expirationTime = ApiKeyManager.getExpirationTime(apiKey);
        long currentTime = Instant.now().getEpochSecond() + 3601;
        boolean hasExpired = currentTime > expirationTime;

        assertTrue(hasExpired, "API key should have expired");
    }

    @Test
    void timeExpirationFalseTest() {
        String apiKey = ApiKeyManager.generate(USER_ID);

        long expirationTime = ApiKeyManager.getExpirationTime(apiKey);
        long currentTime = Instant.now().getEpochSecond();
        boolean hasExpired = currentTime > expirationTime;

        assertFalse(hasExpired, "API key should have expired");
    }

}