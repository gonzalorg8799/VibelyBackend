package com.metrica.vibely.model.response;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.PostDTO;

/**
 * 
 * @since 2023-11-27
 * @author Adri
 * @version 1.0
 */
public class CreatePostResponse {

	// <<-FIELDS->>
	private UUID id;
    private String content;
    
    // <<-CONSTRUCTOR->>
    public CreatePostResponse() {
    }
    
    // <<--METHODS-->>
    public CreatePostResponse generateResponse(PostDTO postDTO) {
    	CreatePostResponse postResponse = new CreatePostResponse();

    	postResponse.setId	   (postDTO.getPostId());
    	postResponse.setContent(postDTO.getContent());
    	
        return postResponse;
    }
    
    // <<--METHODS-->>
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    // <<-GETTERS & SETTERS->>
    
}
