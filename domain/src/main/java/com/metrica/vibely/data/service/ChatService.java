package com.metrica.vibely.data.service;

import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.ChatDTO;

/**
 * <h1>Chat Service</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Daniel, Raul
 */
public interface ChatService extends CrudService<ChatDTO, UUID> {	
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
	
}
