package com.metrica.vibely.model.response;

import com.metrica.vibely.data.model.dto.PostDTO;

/**
 * 
 * @since 2023-11-27
 * @author Adri
 * @version 1.0
 */
public class CreatePostResponse {

	// <<-FIELDS->>
    private String content;
    
    // <<-CONSTRUCTOR->>
    public CreatePostResponse() {
    }
    
    // <<--METHODS-->>
    public static CreatePostResponse toPostResponse(PostDTO postDTO) {
    	CreatePostResponse postResponse = new CreatePostResponse();

    	postResponse.setContent(postDTO.getContent());
    	
        return postResponse;
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    // <<-GETTERS & SETTERS->>
    
}
