package com.metrica.vibely.model.request;


import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.util.PasswordHasher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * 
 * @since 2023-11-14
 * @author 
 * @version 1.0
 */
public class CreateUserRequest {

    // <<-FIELDS->>
    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z0-9@$!%*#?&]{12,}$")
    private String password;

    @Pattern(regexp = "[a-zA-Z0-9_-]*")
    @NotBlank
    private String nickname;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotNull
    @NotBlank
    private String email;

    // <<-CONSTRUCTOR->>
    public CreateUserRequest() {
    }
    
    // <<--METHODS-->>
    public UserDTO toUserDTO() {
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
