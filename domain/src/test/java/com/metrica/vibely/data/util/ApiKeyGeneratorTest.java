package com.metrica.vibely.data.util;

import java.time.Instant;

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

    @RepeatedTest(10)
    void generationTest() {
        String apiKey1 = ApiKeyGenerator.generate();
        String apiKey2 = ApiKeyGenerator.generate();
        assertNotEquals(apiKey1, apiKey2);
    }

    @Test
    void structureTest() {
        String apiKey = "apiKey";
        assertThrows(IllegalArgumentException.class, () -> ApiKeyGenerator.getExpirationTime(apiKey));
    }

    @Test
    void timeExpirationTrueTest() {
        String apiKey = ApiKeyGenerator.generate();

        long expirationTime = ApiKeyGenerator.getExpirationTime(apiKey);
        long currentTime = Instant.now().getEpochSecond() + 3601;
        boolean hasExpired = currentTime > expirationTime;

        assertTrue(hasExpired, "API key should have expired");
    }

    @Test
    void timeExpirationFalseTest() {
        String apiKey = ApiKeyGenerator.generate();

        long expirationTime = ApiKeyGenerator.getExpirationTime(apiKey);
        long currentTime = Instant.now().getEpochSecond();
        boolean hasExpired = currentTime > expirationTime;

        assertFalse(hasExpired, "API key should have expired");
    }

}