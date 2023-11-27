package com.metrica.vibely.data.service;

import com.metrica.vibely.data.model.dto.AdminDTO;

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
    String authenticate(String username, String password);

}
