package com.metrica.vibely.data.model.dto;

import java.util.UUID;

public class FollowDTO {
	// <<-FIELDS->>
	private UUID userId;
	private UUID userFollowId;
	
	// <<-CONSTRUCTOR->>
	public FollowDTO() {
		super();
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
