package com.metrica.vibely.model.response.update;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.PostDTO;

public class UpdatePostResponse {

	// <<-FIELDS->>
	private UUID postId;
    private String content;
    
    // <<-CONSTRUCTOR->>
    public UpdatePostResponse() {
    }
    
    // <<-METHODS->>
    public UpdatePostResponse generateResponse(PostDTO postDTO) {
        UpdatePostResponse postResponse = new UpdatePostResponse();

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