package com.metrica.vibely.model.request;

import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.dto.ChatDTO;
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
	private String title;
	private String type;
	private Set<UUID> participants;
	
//	<<--CONSTRUCTOR-->>
	public CreateChatRequest() {
	}

//	<<--METHODS-->>
	public ChatDTO toChatDTO() {
		ChatDTO chatDto = new ChatDTO();
		
		chatDto.setTitle	   (this.getTitle());
		if(this.type.equals("DIRECT_MESSAGE")) { chatDto.setType(ChatType.DIRECT_MESSAGE); }
		if(this.type.equals("GROUP")) { chatDto.setType(ChatType.GROUP); }
		chatDto.setParticipants(this.getParticipants());
		return chatDto;
	}
//	<<--GETTERS & SETTERS-->>

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<UUID> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<UUID> participants) {
		this.participants = participants;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
