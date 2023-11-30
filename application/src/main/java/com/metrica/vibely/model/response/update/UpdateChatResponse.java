package com.metrica.vibely.model.response.update;

import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;

public class UpdateChatResponse {
	// ATRIBUTES
	private String title;
	private ChatStatus status;
	private ChatType type;
	
	public UpdateChatResponse() {
		super();
	}
	
	public UpdateChatResponse generateResponse(ChatDTO chatDto) {
		
		this.setTitle(chatDto.getTitle());
		this.setStatus(chatDto.getStatus());
		this.setType(chatDto.getType());
		
		return this;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ChatStatus getStatus() {
		return status;
	}

	public void setStatus(ChatStatus status) {
		this.status = status;
	}

	public ChatType getType() {
		return type;
	}

	public void setType(ChatType type) {
		this.type = type;
	}
	
	
}
