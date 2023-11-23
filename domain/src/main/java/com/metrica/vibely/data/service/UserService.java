package com.metrica.vibely.data.service;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.UserDTO;

/**
 * 
 * 
 * @since 2023-11-14
 * @version 1.0
 */
public interface UserService {
	
	/**
	 * gets user information by its username
	 * @param username 
	 * @return UserDTO
	 * @throws NoSuchElementException
	 */
	UserDTO getByUsername(String username);	
	
	/**
	 * creates a new user and adds it to the database
	 * @param userDTO
	 * @return userDTO
	 * @throws NoSuchElementException
	 */
	UserDTO create(UserDTO userDTO); 
		
	UserDTO update(UserDTO UserDTO);
	
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
	 * deletes an user given its username
	 * @throws NoSuchElementException
	 */
	void deleteByUsername(String username);
	
}
