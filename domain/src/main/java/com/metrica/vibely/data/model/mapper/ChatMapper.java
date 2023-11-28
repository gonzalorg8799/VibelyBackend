package com.metrica.vibely.data.model.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.ChatDTO;

/**
 * @since 2023-11-22
 * @author Adrian
 * @version 1.0
 *
 */
public class ChatMapper {

	 public static Chat toEntity(ChatDTO chatDTO, Set<User> participants, Set<Message> messages) {
	        Chat chat = new Chat();

	        // Mapping Basics
	        chat.setChatId		(chatDTO.getChatId());
	        chat.setCreationDate(chatDTO.getCreationDate());
	        chat.setType		(chatDTO.getType());
	        chat.setStatus		(chatDTO.getStatus());
	        chat.setTitle		(chatDTO.getTitle());
	        chat.setLastActivity(chatDTO.getLastActivity());

	        // Mapping Relations
	        chat.setParticipants(participants);
	        chat.setMessages	(messages);

	        return chat;
	    }

	    public static ChatDTO toDTO(Chat chat) {
	        ChatDTO chatDTO = new ChatDTO();

	        // Mapping Basics
	        chatDTO.setChatId(chat.getChatId());
	        chatDTO.setCreationDate(chat.getCreationDate());
	        chatDTO.setType(chat.getType());
	        chatDTO.setStatus(chat.getStatus());
	        chatDTO.setTitle(chat.getTitle());
	        chatDTO.setLastActivity(chat.getLastActivity());

	        // Mapping Relations
	        chatDTO.setParticipants(chat.getParticipants().stream()
						                .map(User::getUserId)
						                .collect(Collectors.toSet()));

	        chatDTO.setMessages	   (chat.getMessages().stream()
						                .map(Message::getMessageId)
						                .collect(Collectors.toSet()));

	        return chatDTO;
	    }
}
