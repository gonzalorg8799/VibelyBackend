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
	 * updates nickname, username, email or password
	 * @param userDTO
	 * @return userDTO
	 * @throws NoSuchElementException
	 */
	UserDTO update(UserDTO userDTO);  
	
	/**
	 * deletes an user given its username
	 * @param username
	 * @throws NoSuchElementException
	 */
	void deleteByUsername(String username);
	
}
