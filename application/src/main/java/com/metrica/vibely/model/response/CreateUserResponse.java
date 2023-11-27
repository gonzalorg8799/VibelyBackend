package com.metrica.vibely.model.response;

import java.util.UUID;

import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.model.dto.UserDTO;

/**
 * 
 * @since 2023-11-15
 * @author 
 * @version 1.0
 */
public class CreateUserResponse {
	// <<-FIELDS->>
	private UUID userId;
    private String username;
    private String nickname;
    private String email;

    // <<-CONSTRUCTOR->>
    public CreateUserResponse() {
    }
    
    // <<--METHODS-->>
    public CreateUserResponse generateResponse(UserDTO userDTO) {
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
