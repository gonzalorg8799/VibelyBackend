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
* <h1>Password Hasher</h1>
*
* @since 2023-01-XX
* @version 2.0
* @author juanagui, Q
*/
public class PasswordHasher {

    // <<-CONSTANTS->>
    private static final int SALT_SIZE_BYTES = 16;
    private static final int HASH_SIZE_BYTES = 32;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA256";

    // <<-CONSTRUCTOR->>
    private PasswordHasher() {
    }
    
    // <<-METHODS->>
    /**
     * 
     * @param password the password to be hashed
     * @return the hashed password
     */
    public static String hash(String password) {
        byte[] salt = new byte[SALT_SIZE_BYTES];
        new SecureRandom().nextBytes(salt);
        byte[] encoded = encode(password, salt);
        return Base64.getEncoder().encodeToString(encoded);
    }
 
    /**
     * 
     * @param password the password to be hashed
     * @param salt     the salt to perform the hash
     * @return a concatenation that follows the form
     *      <b>(salt + (hashed password + salt))</b>
     *      with a length of 48 bytes
     */
    private static byte[] encode(String password, byte[] salt) {
        byte[] concatenated = new byte[SALT_SIZE_BYTES + HASH_SIZE_BYTES];
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 310000, HASH_SIZE_BYTES * 8);
            byte[] generatedPassword = secretKeyFactory.generateSecret(keySpec).getEncoded();
            System.arraycopy(salt, 0, concatenated, 0, SALT_SIZE_BYTES);
            System.arraycopy(generatedPassword, 0, concatenated, SALT_SIZE_BYTES, HASH_SIZE_BYTES);
        } catch (NoSuchAlgorithmException nsaE) {
            throw new RuntimeException("The algorith specificated does not exist");
        } catch (InvalidKeySpecException iksE) {
            throw new RuntimeException("The key specification is not correct");
        }
        return concatenated;
    }
 
    /**
     * @param rawPassword    the password to check
     * @param hashedPassword the hashed password
     * @return true if the result of hash the new password with the salt of the
     *         hashed password is the same, false otherwise
     */
    public static Boolean matches(String rawPassword, String hashedPassword) {
        byte[] concatenated = Base64.getDecoder().decode(hashedPassword);
        byte[] salt = new byte[SALT_SIZE_BYTES];
        System.arraycopy(concatenated, 0, salt, 0, SALT_SIZE_BYTES);
        byte[] encoded = encode(rawPassword, salt);
        return MessageDigest.isEqual(concatenated, encoded);
    }

}