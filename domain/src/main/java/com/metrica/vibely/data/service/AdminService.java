package com.metrica.vibely.data.service;

import com.metrica.vibely.data.model.dto.AdminDTO;

import java.util.UUID;

/**
 * <h1>Administrator Service</h1>
 * 
 * @since 2023-11-14
 * @version 1.0
 * @author Alex
 */
public interface AdminService extends CrudService<AdminDTO, UUID> {
	
	/**
	 * Gets an Admininistrator by its username.
	 * 
	 * @param username the username to search
	 * @return the admin DTO
	 * @throws NoSuchElementException in case the admin does not exist
	 */
	AdminDTO getByUsername(String username);	
	
    /**
     * Deletes an admin given its username
     * 
     * @param username the username to delete
     * @throws NoSuchElementException
     */
    void deleteByUsername(String username);
	
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