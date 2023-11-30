package com.metrica.vibely.data.util;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

import com.metrica.vibely.data.exception.ExpiredApiKeyException;

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

        ByteBuffer buffer = ByteBuffer.allocate(KEY_SIZE_BYTES + LONG_SIZE_BYTES);
        buffer.put(keyBytes);
        buffer.putLong(expirationTime);
        buffer.put(id.toString().getBytes());

        byte[] concatenated = buffer.array();
        return Base64.getEncoder().encodeToString(concatenated);
    }
    
    
    public static UUID getId(String apiKey) {
        byte[] concatenated = Base64.getDecoder().decode(apiKey);  
        ByteBuffer buffer = ByteBuffer.wrap(concatenated, KEY_SIZE_BYTES, LONG_SIZE_BYTES + Integer.BYTES);
        buffer.getLong();
        int idLength = buffer.getInt();

        byte[] idBytes = new byte[idLength];
        buffer.get(idBytes);
        String idString = new String(idBytes);
        
        return UUID.fromString(idString);
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
        
        ByteBuffer buffer = ByteBuffer.wrap(concatenated, KEY_SIZE_BYTES, LONG_SIZE_BYTES);
        return buffer.getLong();
    }
    
    private static boolean isValidFormatApikey(String apiKey) {
    	if(apiKey==null) throw new IllegalArgumentException();
		byte[] concatenated = Base64.getDecoder().decode(apiKey);

		if (concatenated.length < (KEY_SIZE_BYTES + LONG_SIZE_BYTES)) 
			throw new IllegalArgumentException("Invalid API key format"); 
		else return true;
	}
    
	private static boolean isValidExpirationTime(String apiKey) {
			try {
				long expirationTime = getExpirationTime(apiKey);
				long currentTime = Instant.now().getEpochSecond();
				if(expirationTime < currentTime) throw new ExpiredApiKeyException();
				else return true;
			}
			catch(IllegalArgumentException e) {
				return false;
			}
	}
	private static boolean isValidApiKeyForUser(String apiKey, String savedaApiKey) {
		if(apiKey.equals(savedaApiKey))return true;
		else return false;
	}

	public static int isValid(String apiKey, String savedApiKey) {
		try {
			if(!isValidFormatApikey(apiKey))               throw new IllegalArgumentException();
			if(!isValidExpirationTime(apiKey)) 			   throw new ExpiredApiKeyException();
			if(!isValidApiKeyForUser(apiKey, savedApiKey)) throw new ExpiredApiKeyException();
			return 0;
			
		}
		catch(IllegalArgumentException e) {
			return 1;
		}
		catch(ExpiredApiKeyException e) {
			return 2;
		}

	}

}