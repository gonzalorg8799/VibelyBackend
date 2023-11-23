package com.metrica.vibely.data.service;

import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;

/**
 * <h1>Chat Service</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Daniel,
 */
public interface ChatService {

	/**
	 * gets chat by member username
	 * @param username 
	 * @return ChatDTO
	 * @throws NoSuchElementException
	 */
	ChatDTO getByMember(String username);	
	
	/**
	 * creates a new user and adds it to the database
	 * @param ChatDTO
	 * @return ChatDTO
	 * @throws NoSuchElementException
	 */
	ChatDTO create(ChatDTO ChatDto); 
		
	/**
	 * updates chat title
	 * @param desired title
	 * @throws NoSuchElementException
	 */
	ChatDTO updateTitle(UUID chatId, String title);
	
	/**
	 * Adds users to the chat
	 * @param users to add
	 * @throws NoSuchElementException
	 */
	ChatDTO addMembers(UUID chatId, Set<User> membersToAdd);
	
	/**
	 * Removes users from the chat
	 * @param users to remove
	 * @throws NoSuchElementException
	 */
	ChatDTO removeMembers(UUID chatId, Set<User> membersToRemove);
	
	/**
	 * updates chat type
	 * @param Chat Id, type to update to
	 * @throws NoSuchElementException
	 */
	ChatDTO updateType(UUID chatId, ChatType type);
	
	/**
	 * updates chat status
	 * @param Chat Id, status to update to
	 * @throws NoSuchElementException
	 */
	ChatDTO updateChatStatus(UUID chatId, ChatStatus status);
	
	/**
	 * 
	 * gives members of chat
	 * @param chatid
	 * @return Set of users from chat
	 * @throws NoSuchElementException
	 */
	Set<User> getMembers(UUID chatId);
	
	/**
	 * 
	 * gives messages of chat
	 * @param chatid
	 * @return Set of messages from chat
	 * @throws NoSuchElementException
	 */
	Set<Message> getMessages(UUID chatId);
	
	/**
	 * deletes a chat given its Id
	 * @throws NoSuchElementException
	 */
	void deleteById(UUID chatId);
}
