package com.metrica.vibely.model.response.update;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.PostDTO;

public class UpdateLikedByPostResponse {

	// <<-FIELDS->>
	private UUID 	postId;
    private Integer likes;
    
    // <<-CONSTRUCTOR->>
    public UpdateLikedByPostResponse() {
    }
    
    // <<-METHODS->>
    public UpdateLikedByPostResponse generateResponse(PostDTO postDTO) {
    	UpdateLikedByPostResponse likedByPostResponse = new UpdateLikedByPostResponse();

    	likedByPostResponse.setPostId(postDTO.getPostId());
    	likedByPostResponse.setLikes (postDTO.getLikes());
       

        return likedByPostResponse;
    }
    
    // <<-GETTERS & SETTERS->>
    public UUID getPostId() {
        return this.postId;
    }

    public void setPostId(final UUID id) {
        this.postId = id;
    }

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}
    
    
}
