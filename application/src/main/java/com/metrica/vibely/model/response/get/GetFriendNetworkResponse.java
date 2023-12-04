package com.metrica.vibely.model.response.get;

import java.util.Set;
import java.util.UUID;

import com.metrica.vibely.data.model.dto.UserDTO;

public class GetFriendNetworkResponse {
	
//	<<--ATRIBUTES-->>
	private Set<UUID> followers;
	
// <<--CONSTRUCTOR-->>
	public GetFriendNetworkResponse() {
	}
	
// <<--METHODS-->>
	public GetFriendNetworkResponse generateResponse(UserDTO userDto) {
		this.setFollowers(userDto.getFollowers());
		return this;
	}
// <<--GETTERS & SETTERS-->>

	public Set<UUID> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<UUID> followers) {
		this.followers = followers;
	}
	
}
