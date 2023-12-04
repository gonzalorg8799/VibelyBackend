package com.metrica.vibely.model.request;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.dto.PostDTO;

import jakarta.validation.constraints.NotNull;

public class UpdateLikedByPostRequest {

	// <<-FIELDS->>
	@NotNull
	private UUID userId;
	
	// <<-CONSTRUCTOR->>
    public UpdateLikedByPostRequest() {
    }
    
    // <<-METHODS->>
    public PostDTO toDTO() {
    	PostDTO postDTO = new PostDTO();
    	Set<UUID> likedBySet = new HashSet<>();
    	likedBySet.add(userId);
        
    	postDTO.setLikedBy(likedBySet);
        
        return postDTO;
    }

    // <<-GETTERS & SETTERS->>
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}
    
    
}
