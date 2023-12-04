package com.metrica.vibely.data.util;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

import com.metrica.vibely.data.exception.ExpiredApiKeyException;
import com.metrica.vibely.data.exception.InvalidFormatException;
import com.metrica.vibely.data.model.enumerator.HttpStatusEnum;

/**
 * <h1>API Key Generator</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Gonzalo, Alex
 */
public class ApiKeyManager {

    // <<-CONSTANTS->>
    private static final int KEY_SIZE_BYTES = 32;
    private static final int LONG_SIZE_BYTES = Long.BYTES;
    private static final int UUID_SIZE_BYTES = 36;

    // <<-CONSTRUCTOR->>
    private ApiKeyManager() {
    }
    
    // <<-METHODS->>
    /**
     * Generates a random API key using a secure random number generator.
     * 
     * @return a randomly generated API key as a Base64-encoded string.
     */
    public static String generate(UUID id) {
        byte[] keyBytes = new byte[KEY_SIZE_BYTES];
        new SecureRandom().nextBytes(keyBytes);

        // Get current time and set expiration time (1hour)
        long currentTime = Instant.now().getEpochSecond();
        long expirationTime = currentTime + 3600;

        ByteBuffer buffer = ByteBuffer.allocate(KEY_SIZE_BYTES + UUID_SIZE_BYTES + LONG_SIZE_BYTES);
        buffer.put(keyBytes);
        byte[] uuidBytes = id.toString().getBytes();
        buffer.put(uuidBytes);
        buffer.putLong(expirationTime);

        byte[] concatenated = buffer.array();
        
        return Base64.getEncoder().encodeToString(concatenated);
    }
    
    public static UUID getId(String apiKey) {
        byte[] concatenated = Base64.getDecoder().decode(apiKey);
        byte[] userIdBytes = new byte[UUID_SIZE_BYTES];
        System.arraycopy(concatenated, KEY_SIZE_BYTES, userIdBytes, 0, UUID_SIZE_BYTES);
        return UUID.fromString(new String(userIdBytes));
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
        ByteBuffer buffer = ByteBuffer.wrap(concatenated, KEY_SIZE_BYTES + UUID_SIZE_BYTES, LONG_SIZE_BYTES);
        return buffer.getLong();
    }
    
    private static boolean isValidFormatApikey(String apiKey) {
		byte[] concatenated = Base64.getDecoder().decode(apiKey);

		if (concatenated.length != (KEY_SIZE_BYTES + UUID_SIZE_BYTES + LONG_SIZE_BYTES)) {
            return false; 
		}
		
        return true;
	}
    
    private static boolean isValidExpirationTime(String apiKey) {
        long expirationTime = getExpirationTime(apiKey);
        long currentTime = Instant.now().getEpochSecond();
        return expirationTime > currentTime;
    }
	
	/**
	 * 
	 * @param apiKey
	 * @param savedApiKey
	 * @return
	 */
	public static HttpStatusEnum isValid(String apiKey) {
        if (apiKey == null) { return HttpStatusEnum.BAD_REQUEST; }
        
        // TODO Implement this instead of decode in each method
        // byte[] info = Base64.getDecoder().decode(apiKey);
        
		try {
			if (!isValidFormatApikey(apiKey))   throw new InvalidFormatException("Invalid API key format");
			if (!isValidExpirationTime(apiKey)) throw new ExpiredApiKeyException();
			return HttpStatusEnum.OK;
		} catch (InvalidFormatException e) {
	        System.err.println("InvalidFormatException");
			return HttpStatusEnum.BAD_REQUEST;
		} catch (ExpiredApiKeyException e) {
	        System.err.println("ExpiredApiKeyException");
			return HttpStatusEnum.INVALID_CREDENTIALS;
		}
	}

}