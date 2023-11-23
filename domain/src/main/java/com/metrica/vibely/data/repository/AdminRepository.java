package com.metrica.vibely.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.data.entity.Admin;

/**
 * <h1>Admin Repository</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Daniel
 */
@Repository
public interface AdminRepository extends JpaRepository<Admin, java.util.UUID> {
	
	/**
	 * @param username
	 * @return
	 * @throws NoSuchElementException
	 */
//	Optional<User> findByUsername(String username);
	
	/**
	 * @param username
	 * @throws NoSuchElementException
	 */
//	void deleteByUsername(String username);

}
