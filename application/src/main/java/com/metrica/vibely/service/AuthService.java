package com.metrica.vibely.service;

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
     * @param username
     * @param password
     * @throws
     * @return
     */
    String adminUsernameAuth(String username, String password);

    /**
     * @param email
     * @param password
     * @throws
     * @return
     */
    String adminEmailAuth(String email, String password);
    
    /**
     * 
     * @param id
     * @return
     */
    String getApikey(UUID id);
    
    String getAdminApikey(UUID id);

}
