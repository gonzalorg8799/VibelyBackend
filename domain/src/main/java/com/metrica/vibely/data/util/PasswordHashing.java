package com.metrica.vibely.data.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
 
/**
* <h1>Password Hashing</h1>
*
* @since 2023-01-XX
* @version 2.0
* @author juanagui, Q
*/
public class PasswordHashing {
 
    // <<-CONSTANTS->>
    public static final int SALT_SIZE_BYTES = 16;
    public static final int HASH_SIZE_BYTES = 32;
 
    
    // <<-METHODS->>
    public static String hash(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] salt = new byte[SALT_SIZE_BYTES];
        new SecureRandom().nextBytes(salt);
        byte[] encoded = encode(password, salt);
        return Base64.getEncoder().encodeToString(encoded);
    }
 
    private static byte[] encode(String password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 310000, HASH_SIZE_BYTES * 8);
        byte[] generatedPassword = secretKeyFactory.generateSecret(keySpec).getEncoded();
        byte[] concatenated = new byte[SALT_SIZE_BYTES + HASH_SIZE_BYTES];
        System.arraycopy(salt, 0, concatenated, 0, SALT_SIZE_BYTES);
        System.arraycopy(generatedPassword, 0, concatenated, SALT_SIZE_BYTES, HASH_SIZE_BYTES);
        return concatenated;
    }
 
    public static Boolean matches(String rawPassword, String hashedPassword) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] concatenated = Base64.getDecoder().decode(hashedPassword);
        byte[] salt = new byte[SALT_SIZE_BYTES];
        System.arraycopy(concatenated, 0, salt, 0, SALT_SIZE_BYTES);
        byte[] encoded = encode(rawPassword, salt);
        return MessageDigest.isEqual(concatenated, encoded);
    }
 
}
