package com.metrica.vibely.model.request;

import java.time.LocalDateTime;

import com.metrica.vibely.data.entity.Chat;
import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.model.enumerator.MessageStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @since 2023-11-20
 * @version 1.0
 */
public class CreateMessageRequest {
	
//	<<--FIELDS-->>
	@NotNull
	@NotBlank
	private LocalDateTime creationTimestamp;
	
	@NotNull
	@NotBlank
	private MessageStatus status;
	
	@NotNull
	@NotBlank
	private String content;
	
	@NotNull
	@NotBlank
	private Chat chat;
	
	@NotNull
	@NotBlank
	private User sender;

//	<<--CONSTRUCTOR-->>
	public CreateMessageRequest() {
		
	}

//	<<--METHODS-->>
	public  MessageDTO toDto() {
		MessageDTO messageDto = new MessageDTO();
		
		messageDto.setCreationTimestamp(this.getCreationTimestamp());
		messageDto.setStatus		   (this.getStatus());
		messageDto.setContent		   (this.getContent());
		messageDto.setChat			   (this.getChat().getChatId());
		messageDto.setSender           (this.getSender().getUserId());
		
		return messageDto;
	}
	
//	<<--GETTERS & SETTERS-->>

	public LocalDateTime getCreationTimestamp() {
		return creationTimestamp;
	}

	public void setCreationTimestamp(LocalDateTime creationTimestamp) {
		this.creationTimestamp = creationTimestamp;
	}

	public MessageStatus getStatus() {
		return status;
	}

	public void setStatus(MessageStatus status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}
}
