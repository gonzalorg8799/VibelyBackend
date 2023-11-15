package com.metrica.vibely.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * @since 2023-11-14
 * @version 1.0
 */
public class AuthUserRequest {

    // <<-FIELDS->>
    @NotNull
    @NotBlank
    private String username;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*[@$!^%#*&])(?=.*[a-z])(?=.*\\d)(?=.*[A-Z])[A-Za-z\\d@$!%^*#&]{12,}$")
    private String password;

    // <<-CONSTRUCTOR->>
    public AuthUserRequest() {
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

}
