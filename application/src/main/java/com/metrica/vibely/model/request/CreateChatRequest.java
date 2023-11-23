package com.metrica.vibely.model.request;

import java.time.LocalDateTime;
import java.util.Set;

import com.metrica.vibely.data.entity.Message;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @since 2023-11-20
 * @version 1.0
 */
public class CreateChatRequest {
//	<<--FIELDS-->>
	@NotNull
	@NotBlank
	private LocalDateTime creationDate;
	
	@NotNull
	@NotBlank
	private ChatType type;
	
	@NotNull
	@NotBlank
	private ChatStatus status;
	@NotNull
	@NotBlank
	private String title;
	
	@NotNull
	@NotBlank
	private LocalDateTime lastActivity;
	private Set<User> participants;
	private Set<Message> messages;
	
//	<<--CONSTRUCTOR-->>
	public CreateChatRequest() {
		
	}

//	<<--METHODS-->>
	public static ChatDTO toChatDTO(CreateChatRequest chat) {
		ChatDTO chatDto=new ChatDTO();
		
		chatDto.setCreationDate(chat.getCreationDate());
		chatDto.setType		   (chat.getType());
		chatDto.setStatus	   (chat.getStatus());
		chatDto.setTitle	   (chat.getTitle());
		chatDto.setLastActivity(chat.getLastActivity());
		chatDto.setParticipants(chat.getParticipants());
		chatDto.setMessages	   (chat.getMessages());
		
		return chatDto;
	}
//	<<--GETTERS & SETTERS-->>

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public ChatType getType() {
		return type;
	}

	public void setType(ChatType type) {
		this.type = type;
	}

	public ChatStatus getStatus() {
		return status;
	}

	public void setStatus(ChatStatus status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getLastActivity() {
		return lastActivity;
	}

	public void setLastActivity(LocalDateTime lastActivity) {
		this.lastActivity = lastActivity;
	}

	public Set<User> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	
}
