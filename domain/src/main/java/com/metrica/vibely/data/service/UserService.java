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
		
	/**
	 * updates users' nickname
	 * @param desired nickname, apiKey
	 * @throws NoSuchElementException
	 */
	UserDTO updateNickname(UUID userId, String nickname);
	
	/**
	 * updates users' username
	 * @param desired username, apiKey
	 * @throws NoSuchElementException
	 */
	UserDTO updateUsername(UUID userId, String username);
	
	/**
	 * updates users' email
	 * @param new email, apiKey
	 * @throws NoSuchElementException
	 */
	UserDTO updateEmail(UUID userId, String email);
	
	/**
	 * updates users' password
	 * @param desired password, apiKey
	 * @throws NoSuchElementException
	 */
	UserDTO updatePassword(UUID userId, String password);
	
	/**
	 * 
	 * @param userId 
	 * @param follwedUserId
	 * @param username
	 * @return
	 * @throws NoSuchElementException
	 */
	UserDTO followUser(UUID userId, UUID follwedUserId);
	
	/**
	 * 
	 * @param userId
	 * @param follwedUserId
	 * @return
	 * @throws NoSuchElementException
	 */
	UserDTO unfollowUser(UUID userId, UUID follwedUserId);
	
	/**
	 * deletes an user given its username
	 * @throws NoSuchElementException
	 */
	void deleteByUsername(String username);
	
}
