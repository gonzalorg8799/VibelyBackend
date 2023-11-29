package com.metrica.vibely.model.request;

import java.time.LocalDateTime;
import java.util.UUID;

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
	private UUID chat;
	
	@NotNull
	@NotBlank
	private UUID sender;

//	<<--CONSTRUCTOR-->>
	public CreateMessageRequest() {
	}

//	<<--METHODS-->>
	public  MessageDTO toDto() {
		MessageDTO messageDto = new MessageDTO();

		messageDto.setContent		   (this.getContent());
		messageDto.setChat			   (this.getChat());
		messageDto.setSender           (this.getSender());
		
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

	public UUID getChat() {
		return chat;
	}

	public void setChat(UUID chat) {
		this.chat = chat;
	}

	public UUID getSender() {
		return sender;
	}

	public void setSender(UUID sender) {
		this.sender = sender;
	}
}
