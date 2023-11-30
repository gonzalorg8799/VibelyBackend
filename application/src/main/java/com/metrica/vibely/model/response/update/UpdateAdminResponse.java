package com.metrica.vibely.model.response.update;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.AdminDTO;

public class UpdateAdminResponse {
	// <<-FIELDS->>
		private UUID userId;
	    private String username;
	    private String nickname;
	    private String email;

	    // <<-CONSTRUCTOR->>
	    public UpdateAdminResponse() {
	    }
	    
	    // <<--METHODS-->>
	    public UpdateAdminResponse generateResponse(AdminDTO adminDTO) {
	    	this.setUserId  (adminDTO.getUserId());
	        this.setUsername(adminDTO.getUsername());
	        this.setNickname(adminDTO.getNickname());
	        this.setEmail   (adminDTO.getEmail());
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
