package com.metrica.vibely.model.request;

import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.dto.ChatDTO;

public class AddRemoveChatRequest {
//	ATRIBUTES
	Set<UUID> participants;
	
// CONSTRUCTOR
	public AddRemoveChatRequest() {
	}

// METHODS
	public ChatDTO toDTO() {
		ChatDTO chatDto = new ChatDTO();
		
		chatDto.setParticipants(participants);
		
		return chatDto;
	}
	
// GETTERS AND SETTERS
	public Set<UUID> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<UUID> participants) {
		this.participants = participants;
	}

}	
