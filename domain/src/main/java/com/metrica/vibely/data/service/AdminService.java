package com.metrica.vibely.data.service;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.model.dto.UserDTO;

/**
 * 
 * 
 * @since 2023-11-14
 * @version 1.0
 */
public interface AdminService extends CrudService<AdminDTO, UUID> {
	
	/**
	 * gets admin information by its username
	 * @param username 
	 * @return adminDTO
	 * @throws NoSuchElementException
	 */
	AdminDTO getByUsername(String username);	
		
	/**
	 * updates admins' nickname
	 * @param desired nickname, apiKey
	 * @throws NoSuchElementException
	 */
	AdminDTO updateNickname(UUID adminId, String nickname);
	
	/**
	 * updates admins' username
	 * @param desired username, apiKey
	 * @throws NoSuchElementException
	 */
	AdminDTO updateUsername(UUID adminId, String username);
	
	/**
	 * updates admins' email
	 * @param new email, apiKey
	 * @throws NoSuchElementException
	 */
	AdminDTO updateEmail(UUID adminId, String email);
	
	/**
	 * updates admins' password
	 * @param desired password, apiKey
	 * @throws NoSuchElementException
	 */
	AdminDTO updatePassword(UUID adminId, String password);
	
	/**
	 * 
	 * @param adminId 
	 * @param follwedUserId
	 * @param username
	 * @return
	 * @throws NoSuchElementException
	 */
	AdminDTO followUser(UUID adminId, UUID followedUserId);
	
	/**
	 * 
	 * @param adminId
	 * @param follwedUserId
	 * @return
	 * @throws NoSuchElementException
	 */
	AdminDTO unfollowUser(UUID adminId, UUID followedUserId);
	
}
