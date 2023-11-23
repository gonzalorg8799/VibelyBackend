package com.metrica.vibely.service;

/**
 * <h1>Discord Service</h1>
 * 
 * @since 2023-11-21
 * @version 1.0
 * @author Alex
 */
public interface DiscordService {

    /**
     * 
     * @param username
     * @param password
     * @return
     */
    String authenticate(String username, String password);
    /**
     * 
     * @param username
     * @return
     */
    String getUserInfo(String username);
    
}
