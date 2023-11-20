package com.metrica.vibely.model.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrica.vibely.data.model.dto.AdminDTO;
import com.metrica.vibely.data.util.PasswordHasher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * @since 2023-11-20
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1")
public class CreateAdminRequest {

//	<<--FIELDS-->>
	@NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[@$!^%#*&])(?=.*[a-z])(?=.*\\d)(?=.*[A-Z])[A-Za-z\\d@$!%^*#&]{12,}$")
    private String password;

    @Pattern(regexp = "[a-zA-Z_-\\d]*")
    @NotBlank
    private String nickname;

    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @NotNull
    @NotBlank
    private String email;

    // <<-CONSTRUCTOR->>
    public CreateAdminRequest() {
    }
    
    // <<--METHODS-->>
    public static AdminDTO toAdminDTO(CreateUserRequest user) {
        AdminDTO adminDto = new AdminDTO();

        adminDto.setUsername(user.getUsername());
        adminDto.setPassword(user.getPassword());
        adminDto.setNickname(user.getNickname());
        adminDto.setEmail(user.getEmail());

        return adminDto;
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
		this.password = PasswordHasher.hash(password);
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
