package com.metrica.vibely.data.repository;

import com.metrica.vibely.data.entity.Admin;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * <h1>Administrator Repository</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Daniel
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, java.util.UUID> {
	
	/**
	 * Find a user by its username.
	 * 
	 * @param username the unique username
	 * @return the user if exist
	 */
	Optional<Admin> findByUsername(String username);
    
    /**
     * Find a user by its username.
     * 
     * @param username the unique username
     * @return the user if exist
     */
    Optional<Admin> findByEmail(String email);
	
	/**
	 * @param username
	 * @throws NoSuchElementException
	 */
//	void deleteByUsername(String username);
    @Query("SELECT a.apikey FROM Admin a WHERE a.userId=:id")
	Optional<String> findApikeyByUserId(UUID id);

}
