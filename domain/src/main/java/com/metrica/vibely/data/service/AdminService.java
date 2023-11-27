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
public interface AdminService {
	
    /**
     * Gets an Admininistrator by its id.
     * 
     * @param id the id to search
     * @return the admin DTO
     * @throws NoSuchElementException in case the admin does not exist
     */
    AdminDTO getById(UUID id);
    
	/**
	 * Gets an Admininistrator by its username.
	 * 
	 * @param username the username to search
	 * @return the admin DTO
	 * @throws NoSuchElementException in case the admin does not exist
	 */
	AdminDTO getByUsername(String username);
	
	/**
	 * Creates a new admin and adds it to the database
	 * 
	 * @param adminDTO the admin to create
	 * @return adminDTO the admin with the id from the database
	 */
	AdminDTO create(AdminDTO adminDTO);
    
	/**
     * Creates a new admin and adds it to the database
     * 
     * @param adminDTO the admin to create
     * @return adminDTO the admin with the id from the database
     * @throws NoSuchElementException in case the admin does not exist
     */
    AdminDTO update(AdminDTO adminDTO);
	
    /**
     * Deletes an admin given its ID
     * 
     * @param id the id to delete
     * @throws NoSuchElementException in case the admin does not exist
     */
	void deleteById(UUID id);
    
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