package com.metrica.vibely.model.request;

import com.metrica.vibely.data.model.dto.PostDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * <h1>Update Post Request</h1>
 * 
 * @since 2023-11-28
 * @version 1.0
 * @author Adri
 */
public class UpdatePostRequest {
	
	// <<-FIELDS->>
	@NotNull
	@NotBlank
	private String content;
	
	// <<-CONSTRUCTOR->>
    public UpdatePostRequest() {
    }
    
    // <<-METHODS->>
    public PostDTO toDTO() {
    	PostDTO updateDTO = new PostDTO();
        
    	updateDTO.setContent(this.content);
        
        return updateDTO;
    }

    // <<-GETTERS & SETTERS->>
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
	
}
