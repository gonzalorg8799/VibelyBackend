package com.metrica.vibely.data.util;

import java.security.SecureRandom;
import java.util.Base64;

public class ApiKeyGenerator {
	public static String generate() {
        // Genera una clave aleatoria utilizando SecureRandom 
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        // Codifica la clave en Base64 para obtener una representación de cadena
        String apiKey = Base64.getEncoder().encodeToString(keyBytes);
        return apiKey;
    }
}
