package com.metrica.vibely.data.service;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.model.dto.UserDTO;

/**
 * <h1>Message Service</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Daniel,
 */
public interface MessageService {
	
	/**
	 * gets message information by its content
	 * @param content 
	 * @return MessageDTO
	 * @throws NoSuchElementException
	 */
	MessageDTO getById(UUID messageId);

	/**
	 * gets message information by its content
	 * @param content 
	 * @return MessageDTO
	 * @throws NoSuchElementException
	 */
	MessageDTO getByContent(String content);	
	
	/**
	 * creates a new message and adds it to the database
	 * @param MessageDTO
	 * @return MessageDTO
	 * @throws NoSuchElementException
	 */
	MessageDTO create(MessageDTO MessageDTO); 
		
	/**
	 * gets Message sender
	 * @param messageId
	 * @return sender
	 * @throws NoSuchElementException
	 */
	UserDTO getSender(UUID messageId);
	
	/**
	 * gets Message chat
	 * @param messageId
	 * @return chat
	 * @throws NoSuchElementException
	 */
	ChatDTO getChat(UUID messageId);
	
	/**
	 * deletes a message given its Id
	 * @throws NoSuchElementException
	 */
	void deleteById(UUID messageId);
	

}
