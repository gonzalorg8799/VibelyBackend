package com.metrica.vibely.model.response.create;

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
	private UUID postId;
    private String content;
    
    // <<-CONSTRUCTOR->>
    public CreatePostResponse() {
    }
    
    // <<-METHODS->>
    public CreatePostResponse generateResponse(PostDTO postDTO) {
        CreatePostResponse postResponse = new CreatePostResponse();

        postResponse.setPostId (postDTO.getPostId());
        postResponse.setContent(postDTO.getContent());

        return postResponse;
    }

    // <<-GETTERS & SETTERS->>
    public UUID getPostId() {
        return this.postId;
    }

    public void setPostId(final UUID id) {
        this.postId = id;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

}