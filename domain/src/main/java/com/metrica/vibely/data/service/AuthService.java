package com.metrica.vibely.data.service;

import java.util.UUID;

/**
 * 
 * 
 * @since 2023-11-14
 * @version 1.0
 */
public interface AuthService {
    
    /**
     * @param username
     * @param password
     * @throws
     * @return
     */
    String usernameAuth(String username, String password);

    /**
     * @param email
     * @param password
     * @throws
     * @return
     */
    String emailAuth(String email, String password);
    
    /**
     * 
     * @param id
     * @return
     */
    String getApikey(UUID id);

}
