package com.metrica.vibely.data.util;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;

/**
 * <h1>API Key Generator</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Gonzalo, Alex
 */
public abstract class ApiKeyGenerator {

    // <<-CONSTANTS->>
    private static final int KEY_SIZE_BYTES = 32;
    private static final int LONG_SIZE_BYTES = Long.BYTES;

    // <<-METHODS->>
    /**
     * Generates a random API key using a secure random number generator.
     * 
     * @return a randomly generated API key as a Base64-encoded string.
     */
    public static String generate() {
        byte[] keyBytes = new byte[KEY_SIZE_BYTES];
        new SecureRandom().nextBytes(keyBytes);

        // Get current time and set expiration time (1hour)
        long currentTime = Instant.now().getEpochSecond();
        long expirationTime = currentTime + 3600;

        ByteBuffer buffer = ByteBuffer.allocate(KEY_SIZE_BYTES + LONG_SIZE_BYTES);
        buffer.put(keyBytes);
        buffer.putLong(expirationTime);

        byte[] concatenated = buffer.array();
        return Base64.getEncoder().encodeToString(concatenated);
    }
    
    /**
     * Retrieves the expiration time from the provided API key.
     *
     * @param apiKey The API key containing expiration and creation time.
     * @return the expiration time in seconds since the epoch.
     * @throws IllegalArgumentException if the provided API key is invalid.
     */
    public static long getExpirationTime(String apiKey) {
        byte[] concatenated = Base64.getDecoder().decode(apiKey);
        
        if (concatenated.length < (KEY_SIZE_BYTES + LONG_SIZE_BYTES)) {
            throw new IllegalArgumentException("Invalid API key format");
        }
        
        ByteBuffer buffer = ByteBuffer.wrap(concatenated, KEY_SIZE_BYTES, LONG_SIZE_BYTES);
        return buffer.getLong();
    }

}