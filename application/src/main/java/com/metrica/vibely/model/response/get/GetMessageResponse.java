package com.metrica.vibely.model.response.get;

import java.time.LocalDateTime;
import java.util.UUID;

import com.metrica.vibely.data.model.dto.MessageDTO;
import com.metrica.vibely.data.model.enumerator.MessageState;
import com.metrica.vibely.data.model.enumerator.MessageStatus;

public class GetMessageResponse {
	
//	<<--ATRIBUTES-->>
	private UUID messageId;
	private LocalDateTime creationTimestamp;
	private MessageStatus status;
	private MessageState state;
	private String content;

	private UUID chat;
	private UUID sender;
	 
//	<<--CONSTRUCTOR-->>
	public GetMessageResponse() {
	}

//	<<--METHODS-->>
	public GetMessageResponse generateResponse(MessageDTO messageDto) {
		
		this.setMessageId(messageDto.getMessageId());
		this.setCreationTimestamp(messageDto.getCreationTimestamp());
		this.setStatus(messageDto.getStatus());
		this.setState(messageDto.getState());
		this.setContent(messageDto.getContent());
		this.setChat(messageDto.getChat());
		this.setSender(messageDto.getSender());
		
		return this;
	}
	
//	<<--GETTERS & SETTERS-->> 
	public UUID getMessageId() {
		return messageId;
	}

	public void setMessageId(UUID messageId) {
		this.messageId = messageId;
	}

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

	public MessageState getState() {
		return state;
	}

	public void setState(MessageState state) {
		this.state = state;
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
