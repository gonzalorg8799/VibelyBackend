package com.metrica.vibely.model.request;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.FollowDTO;

public class CreateFollowRequest {
	
	
	// <<-FIELDS->>
	private UUID userId;
	private UUID userFollowId;
	
	
	 // <<-CONSTRUCTOR->>
	public CreateFollowRequest() {
		super();
	}
	
	// <<--METHODS-->>

	public FollowDTO toFollowDTO() {
		
		FollowDTO followDTO= new FollowDTO();
		
		followDTO.setUserId(userId);
		
		followDTO.setUserFollowId(userFollowId);
		
		return followDTO;
		
	}
	
	// <<-GETTERS & SETTERS->>
	public UUID getUserFollowId() {
		return userFollowId;
	}

	public void setUserFollowId(UUID userFollowId) {
		this.userFollowId = userFollowId;
	}

	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	
	
	
}
