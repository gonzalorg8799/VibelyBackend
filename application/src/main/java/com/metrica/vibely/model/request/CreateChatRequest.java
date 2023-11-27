package com.metrica.vibely.model.request;

import java.util.Set;
import java.util.stream.Collectors;

import com.metrica.vibely.data.entity.User;
import com.metrica.vibely.data.model.dto.ChatDTO;

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
	private Set<User> participants;
	
//	<<--CONSTRUCTOR-->>
	public CreateChatRequest() {
		
	}

//	<<--METHODS-->>
	public ChatDTO toChatDTO() {
		ChatDTO chatDto = new ChatDTO();
		
		chatDto.setTitle	   (this.getTitle());
		chatDto.setParticipants(this.getParticipants().stream()
													  .map(p -> p.getUserId())
													  .collect(Collectors.toSet()));
		return chatDto;
	}
//	<<--GETTERS & SETTERS-->>

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<User> getParticipants() {
		return participants;
	}

	public void setParticipants(Set<User> participants) {
		this.participants = participants;
	}

	
}
