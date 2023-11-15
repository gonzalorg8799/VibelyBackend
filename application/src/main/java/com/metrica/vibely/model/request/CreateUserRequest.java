package com.metrica.vibely.model.request;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.metrica.vibely.data.model.dto.UserDTO;
import com.metrica.vibely.data.util.PasswordHashing;

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
    @Pattern(regexp = "[a-zA-Z_-\\d]*")
    private String password;

    @Pattern(regexp = "[a-zA-Z_-\\d]*")
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
    public static UserDTO toUserDTO(CreateUserRequest user) {
        UserDTO userDto = new UserDTO();

        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setNickname(user.getNickname());
        userDto.setEmail(user.getEmail());

        return userDto;
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
        try {
			this.password = PasswordHashing.hash(password);
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
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
