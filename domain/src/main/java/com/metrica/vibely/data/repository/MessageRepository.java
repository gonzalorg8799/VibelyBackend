package com.metrica.vibely.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metrica.vibely.data.entity.Message;

/**
 * <h1>Message Repository</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Daniel
 */

@Repository
public interface MessageRepository extends JpaRepository<Message, java.util.UUID>{
	
	/**
	 * @params username, content
	 * @return Message
	 * @throws NoSuchElementException
	 */
//	Optional<Message> findByContent(String username, String content);

}
