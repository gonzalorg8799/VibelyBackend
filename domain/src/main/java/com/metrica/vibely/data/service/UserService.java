package com.metrica.vibely.data.service;

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
	UserDTO updateNickname(String nickname, String apiKey);
	
	/**
	 * updates users' username
	 * @param desired username, apiKey
	 * @throws NoSuchElementException
	 */
	UserDTO updateUsername(String username, String apiKey);
	
	/**
	 * updates users' email
	 * @param new email, apiKey
	 * @throws NoSuchElementException
	 */
	UserDTO updateEmail(String email, String apiKey);
	
	/**
	 * updates users' password
	 * @param desired password, apiKey
	 * @throws NoSuchElementException
	 */
	UserDTO updatePassword(String password, String apiKey);
	
	/**
	 * deletes an user given its username
	 * @param username
	 * @throws NoSuchElementException
	 */
	void deleteByUsername(String username);
	
}
