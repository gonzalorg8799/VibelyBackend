package com.metrica.vibely.model.request;

import com.metrica.vibely.data.model.dto.ChatDTO;
import com.metrica.vibely.data.model.enumerator.ChatStatus;
import com.metrica.vibely.data.model.enumerator.ChatType;

public class UpdateChatRequest {
//	<<-ATRIBUTES->>
	private String title;
	private ChatType type;
	private ChatStatus status;
	
//	<<-CONSTRUCTOR->>
	public UpdateChatRequest() {
	}

//	<<-METHODS->>
	public ChatDTO toDTO() {
		ChatDTO chatDto = new ChatDTO();
		
		chatDto.setTitle(this.title);
		chatDto.setType(this.type);
		chatDto.setStatus(this.status);
		
		return chatDto;
	}
	
//	<<-GETTERS & SETTERS->>
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	
}
