package com.metrica.vibely.model.request;

import com.metrica.vibely.data.model.dto.UserDTO;

import jakarta.validation.constraints.Pattern;

/**
 * <h1>Update User Request</h1>
 * 
 * @since 2023-11-27
 * @version 1.0
 * @author Adri
 */
public class UpdateUserRequest {
	
	// <<-FIELDS->>
    private String username;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z0-9@$!%*#?&]{12,}$")
    private String password;
    private String nickname;
    private String email;

    // <<-CONSTRUCTOR->>
    public UpdateUserRequest() {
    }
    
    // <<-METHODS->>
    public UserDTO toDTO() {
    	UserDTO userDTO = new UserDTO();
        
    	userDTO.setUsername(this.username);
    	userDTO.setPassword(this.password);
    	userDTO.setNickname(this.nickname);
        userDTO.setEmail   (this.email);
        
        return userDTO;
    }

    // <<-GETTERS & SETTERS->>
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
