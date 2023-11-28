package com.metrica.vibely.model.response;

import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.dto.ChatDTO;

public class CreateChatResponse {
//	<<-FIELDS->>
	
	private UUID chatId;
	private String title;
	private Set<UUID> participants;
	
//	<<-CONSTRUCTOR->>
	public CreateChatResponse() {
	}
	
//	<<-METHODS->>
	public CreateChatResponse generateResponse(ChatDTO chatDto) {
		this.setChatId(chatDto.getChatId());
		this.setTitle(chatDto.getTitle());
		this.setParticipants(chatDto.getParticipants());
		return this;
	}

//	<<-GETTERS & SETTERS ->>
	public UUID getChatId() {
		return chatId;
	}

	public void setChatId(UUID chatId) {
		this.chatId = chatId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<UUID> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<UUID> set) {
		this.participants = set;
	}
	
}
