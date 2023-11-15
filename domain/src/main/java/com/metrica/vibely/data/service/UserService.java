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
	 * 
	 * @param username 
	 * @return UserDTO
	 * @throws NoSuchElementException
	 */
	UserDTO getByUsername(String username);	
	
	/**
	 * 
	 * @param userDTO
	 * @return userDTO
	 * @throws NoSuchElementException
	 */
	UserDTO create(UserDTO userDTO); 
	
	/**
	 * 
	 * @param userDTO
	 * @return userDTO
	 * @throws NoSuchElementException
	 */
	UserDTO update(UserDTO userDTO);  
	
	/**
	 * 
	 * @param username
	 * @throws NoSuchElementException
	 */
	void deleteByUsername(String username);
	
}
