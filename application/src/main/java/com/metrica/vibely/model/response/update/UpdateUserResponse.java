package com.metrica.vibely.model.response.update;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.UserDTO;

public class UpdateUserResponse {
		// <<-FIELDS->>
		private UUID userId;
	    private String username;
	    private String nickname;
	    private String email;

	    // <<-CONSTRUCTOR->>
	    public UpdateUserResponse() {
	    }
	    
	    // <<--METHODS-->>
	    public UpdateUserResponse generateResponse(UserDTO userDTO) {
	    	
	    	this.setUserId  (userDTO.getUserId());
	        this.setUsername(userDTO.getUsername());
	        this.setNickname(userDTO.getNickname());
	        this.setEmail   (userDTO.getEmail());
	        
	        return this;
	    }
	    
	    // <<-GETTERS & SETTERS->>
	    public UUID getUserId() {
			return userId;
		}

		public void setUserId(UUID userId) {
			this.userId = userId;
		}
		
	    public String getUsername() {
	        return username;
	    }

		public void setUsername(String username) {
	        this.username = username;
	    }

	    public String getNickname() {
	        return nickname;
	    }

	    public void setNickname(String nickname) {
	        this.nickname = nickname;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }
}
