package com.metrica.vibely.model.response.create;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.MessageDTO;

public class CreateMessageResponse {
//	<<--ATRIBUTES-->>
	private UUID messageId;
	private UUID sender;
	private UUID chatId;
	private String content;
	
//	<<--CONSTRUCTOR-->>
	public CreateMessageResponse() {
	}

//	<<--METHODS-->>
	public CreateMessageResponse generateResponse(MessageDTO messageDto) {		
		
		this.setMessageId(messageDto.getMessageId());
		this.setSender(messageDto.getSender());
		this.setChatId(messageDto.getChat());
		this.setContent(messageDto.getContent());
		
		return this;
	}
	
//	<<--GETTERS & SETTERS-->>
	public UUID getMessageId() {
		return messageId;
	}

	public void setMessageId(UUID messageId) {
		this.messageId = messageId;
	}

	public UUID getSender() {
		return sender;
	}

	public void setSender(UUID sender) {
		this.sender = sender;
	}

	public UUID getChatId() {
		return chatId;
	}

	public void setChatId(UUID chatId) {
		this.chatId = chatId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
