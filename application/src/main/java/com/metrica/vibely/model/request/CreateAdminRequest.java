package com.metrica.vibely.model.request;

import com.metrica.vibely.data.model.dto.AdminDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * <h1>Create Administrator Requested Body</h1>
 * 
 * @since 2023-11-20
 * @version 1.0
 * @author Alex
 */
public class CreateAdminRequest {

    // <<-FIELDS->>
    @NotNull
    @NotBlank
    @Size(min = 4)
    private String username;
    @NotNull
    @NotBlank
    @Size(min = 12)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@$!%*#?&])[A-Za-z0-9@$!%*#?&]{12,}$")
    private String password;
    @NotBlank
    @Size(min = 4)
    @Pattern(regexp = "[a-zA-Z0-9_-]*")
    private String nickname;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    private String email;

    // <<-CONSTRUCTOR->>
    public CreateAdminRequest() {
    }
    
    // <<-METHODS->>
    public AdminDTO toAdminDTO() {
        AdminDTO admin = new AdminDTO();

        admin.setUsername(this.username);
        admin.setPassword(this.password);
        admin.setNickname(this.nickname);
        admin.setEmail   (this.email);

        return admin;
    }

    // <<-GETTERS & SETTERS->>
    public String getUsername() {
        return this.username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

}