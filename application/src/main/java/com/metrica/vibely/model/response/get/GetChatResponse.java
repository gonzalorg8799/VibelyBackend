package com.metrica.vibely.model.response.get;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.enumerator.ChatState;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;

public class GetChatResponse {
//	<<--ATRIBUTEs-->>
	private UUID chatId;
    private LocalDateTime creationDate;
    
    private ChatType type;
    private ChatStatus status;
    private ChatState state;

    private String title;
    private LocalDateTime lastActivity;

    private Set<UUID> participants;
    private Set<UUID> messages;
    
//	<<--CONSTRUCTOR-->>
    public GetChatResponse() {
    }
    
//	<<--METHODS-->>
    public GetChatResponse generateResponse(ChatDTO chatDto) {
    	
    	this.setChatId(chatDto.getChatId());
    	this.setCreationDate(chatDto.getCreationDate());
    	this.setType(chatDto.getType());
    	this.setStatus(chatDto.getStatus());
    	this.setState(chatDto.getState());
    	this.setTitle(chatDto.getTitle());
    	this.setLastActivity(chatDto.getLastActivity());
    	this.setParticipants(chatDto.getParticipants());
    	this.setMessages(chatDto.getMessages());
    	
    	return this;
    }
    
//	<<--GETTERS & SETTERS-->>
	public UUID getChatId() {
		return chatId;
	}

	public void setChatId(UUID chatId) {
		this.chatId = chatId;
	}

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

	public ChatState getState() {
		return state;
	}

	public void setState(ChatState state) {
		this.state = state;
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

	public Set<UUID> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<UUID> participants) {
		this.participants = participants;
	}

	public Set<UUID> getMessages() {
		return messages;
	}

	public void setMessages(Set<UUID> messages) {
		this.messages = messages;
	}
    
}
