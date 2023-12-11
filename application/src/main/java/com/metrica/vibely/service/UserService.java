package com.metrica.vibely.service;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.dto.UserDTO;

/**
 * <h1>User Service</h1>
 * 
 * @since 2023-11-14
 * @version 1.0
 * @author Raul,
 */
public interface UserService extends CrudService<UserDTO, UUID>{
    
    /**
     * gets user information by its username
     * @param username 
     * @return UserDTO
     * @throws NoSuchElementException
     */
    UserDTO getByUsername(String username);
    
    /**
     * gets user information by its email
     * @param email 
     * @return UserDTO
     * @throws NoSuchElementException
     */
    UserDTO getByEmail(String email);
    
    /**
     * deletes an user given its username
     * @throws NoSuchElementException
     */
    void deleteByUsername(String username);
	
	UserDTO followUser(UUID userId, UUID followedUserId);
	
	/**
	 * 
	 * @param userId
	 * @param follwedUserId
	 * @return
	 * @throws NoSuchElementException
	 */
	UserDTO unfollowUser(UUID userId, UUID followedUserId);

	/**
	 * 
	 * @param userId
	 * @return Set<UUID>
	 * @throws NoSuchElementException
	 */
	Set<UUID> getFriendNetwork(UUID id);

	
}
