package com.metrica.vibely.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.data.entity.User;

/**
 * 
 * @since 2023-11-14
 * @author Raul
 * @version 1.0
 */

@Repository
public interface UserRepository extends JpaRepository<User, java.util.UUID> {
	
	/**
	 * @param username
	 * @return
	 * @throws NoSuchElementException
	 */
	Optional<User> findByUsername(String username);
	
	/**
	 * @param username
	 * @throws NoSuchElementException
	 */
	void deleteByUsername(String username);
}
